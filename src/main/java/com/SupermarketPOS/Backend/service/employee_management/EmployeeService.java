package com.SupermarketPOS.Backend.service.employee_management;

import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeValidationReport;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
import com.SupermarketPOS.Backend.service.common_services.AddressService;

import com.SupermarketPOS.Backend.service.common_services.BranchService;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SalaryTypeService salaryTypeService;
    private  final AddressService addressService;
    private final PasswordEncoder passwordEncoder;
    private final BranchService branchService;

    public EmployeeService(EmployeeRepository employeeRepository, SalaryTypeService salaryTypeService, AddressService addressService, PasswordEncoder passwordEncoder, BranchService branchService) {
//    public EmployeeService(EmployeeRepository employeeRepository, SalaryTypeService salaryTypeService, AddressService addressService, BranchService branchService) {
        this.employeeRepository = employeeRepository;
        this.salaryTypeService = salaryTypeService;
        this.addressService = addressService ;
        this.passwordEncoder = passwordEncoder;
        this.branchService = branchService;
    }


    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Branch getBranchByEmployeeId(Integer id){
        return employeeRepository.findById(id).get().getBranch();
    }

    public Employee getByEmail(String email){
        return employeeRepository.findByEmail(email).get();
    }

    @Transactional
    public Employee AddNewEmployee(EmployeeInput employeeInput){
        EmployeeValidationReport validationReport = Validate(employeeInput);
        System.out.println(validationReport);
        if(validationReport.isEmailOkay() && validationReport.isNameOkay() && validationReport.isNumberOkay()){
            Address newEmployeeAddress;
            if (employeeInput.city() != null || employeeInput.street() !=null || employeeInput.houseNumber() != null || employeeInput.district()!= null ||employeeInput.postalCode()!=null){

                newEmployeeAddress = addressService.SaveAddress(
                        new Address(
                                employeeInput.houseNumber(),
                                employeeInput.street(),
                                employeeInput.city(),
                                employeeInput.district(),
                                employeeInput.postalCode()
                        )
                );
            }
            else newEmployeeAddress = null;


                 SalaryType availableSalaryType = salaryTypeService.FindById(employeeInput.salaryTypeId());

                Branch branch = branchService.findBranchById(employeeInput.branchId());

                Employee newEmployee = new Employee(
                        employeeInput.title(),
                        employeeInput.firstName(),
                        employeeInput.middleName(),
                        employeeInput.lastName(),
                        employeeInput.email(),
                        newEmployeeAddress,
                        employeeInput.phoneNumber(),
                        employeeInput.jobRole(),
                        availableSalaryType,
                        true,
                        passwordEncoder.encode(employeeInput.password()),
                        branch,
                        new Timestamp(System.currentTimeMillis())
                );
                System.out.println(employeeInput);
                return employeeRepository.save(newEmployee);
        }
        throw new RuntimeException("User is already there");

    }

    public EmployeeValidationReport Validate(EmployeeInput employeeInput){
        //  validation criteria
        // no employee have the same 3 names (first, middle, last)
        //phone should be unique
        // email should be unique

        boolean isNameOkay =  !employeeRepository.isEmployeeNameTaken(
                employeeInput.firstName(),
                employeeInput.middleName(),
                employeeInput.lastName()
        ); // check the availability of the name

        Boolean isNumberOkay = !employeeRepository.isNumberTaken(
                employeeInput.phoneNumber()
        );
        Boolean isEmailOkay = !employeeRepository.isEmailTaken(
                employeeInput.email()
        );
        return new EmployeeValidationReport(
                isNameOkay,
                isNumberOkay,
                isEmailOkay
        );
    }

    public Employee findById(Integer id) {
        return  employeeRepository.findById(id).get();
    }

    //delete employee using id
    public Employee DeleteEmployee(Integer id){
        Employee employee = employeeRepository.findById(id).get();
        employee.setActive(false);
        return employeeRepository.save(employee);
    }
}
