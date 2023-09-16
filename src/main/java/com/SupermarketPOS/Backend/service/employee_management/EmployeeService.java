package com.SupermarketPOS.Backend.service.employee_management;

import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeValidationReport;
import com.SupermarketPOS.Backend.dto.employee_management.SalaryTypeInput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.repository.AddressRepository;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
import com.SupermarketPOS.Backend.service.common_services.AddressService;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SalaryTypeService salaryTypeService;
    private  final AddressService addressService;

    public EmployeeService(EmployeeRepository employeeRepository, SalaryTypeService salaryTypeService, AddressService addressService) {
        this.employeeRepository = employeeRepository;
        this.salaryTypeService = salaryTypeService;
        this.addressService = addressService ;
    }


    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Branch getBranchByEmployeeId(Integer id){
        return employeeRepository.findById(id).get().getBranch();
    }


    @Transactional
    public Employee AddNewEmployee(EmployeeInput employeeInput){
        EmployeeValidationReport validationReport = Validate(employeeInput);

        if(validationReport.isEmailOkay() && validationReport.isNameOkay() && validationReport.isNumberOkay()){
                Address newEmployeeAddress = addressService.SaveAddres(
                        new Address(
                                employeeInput.houseNumber(),
                                employeeInput.street(),
                                employeeInput.city(),
                                employeeInput.district(),
                                employeeInput.postalCode()
                        )
                );

                 SalaryType availableSalaryType = salaryTypeService.FindById(employeeInput.salaryTypeId());
//            SalaryType availableSalaryType = salaryTypeService.AddNewSalaryType(
//                    new SalaryTypeInput(
//                            100.0f,  // Convert to Float
//                            100.0f,  // Convert to Float
//                            100.0f,  // Convert to Float
//                            100.0f   // Convert to Float
//                    )
//            );


            // SalaryTypeInput newSalaryTypeInput = employeeInput.salaryType(); // get the salaryIput from the employeeInput
                // SalaryType availableSalaryType = salaryTypeService.getSalaryTypeBySalaryInput(newSalaryTypeInput); // get the salary type : if saved before give the saved salary type if not create a new salary type

                //System.out.println(employeeInput.job_role());
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
                        "aaaa"
                );
                return employeeRepository.save(newEmployee);
        }
        return null;
        
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
}
