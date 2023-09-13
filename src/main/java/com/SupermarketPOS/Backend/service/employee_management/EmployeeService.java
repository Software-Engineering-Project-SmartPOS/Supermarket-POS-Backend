package com.SupermarketPOS.Backend.service.employee_management;

import com.SupermarketPOS.Backend.dto.employee_management.AddressInput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeValidationReport;
import com.SupermarketPOS.Backend.dto.employee_management.SalaryTypeInput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AddressService addressService;
    private final SalaryTypeService salaryTypeService;

    public EmployeeService(EmployeeRepository employeeRepository, AddressService addressService ,SalaryTypeService salaryTypeService){
        this.employeeRepository = employeeRepository;
        this.addressService = addressService;
        this.salaryTypeService =salaryTypeService;

    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee AddNewEmployee(EmployeeInput employeeInput){
        EmployeeValidationReport validationReport = Validate(employeeInput);

        if(validationReport.isEmailOkay() && validationReport.isNameOkay() && validationReport.isNumberOkay()){
                AddressInput newEmployeeAddressInput = employeeInput.address();// get the employee address from the employeeInput
                Address  availableAddress  = addressService.getAddressByAddressInput(newEmployeeAddressInput); //get the address if address is saved previously get the saved address, if not save as a new address and get the address

                SalaryTypeInput newSalaryTypeInput = employeeInput.salaryType(); // get the salaryIput from the employeeInput
                SalaryType availableSalaryType = salaryTypeService.getSalaryTypeBySalaryInput(newSalaryTypeInput); // get the salary type : if saved before give the saved salary type if not create a new salary type

                //System.out.println(employeeInput.job_role());
                Employee newEmployee = new Employee(
                        employeeInput.title(),
                        employeeInput.firstName(),
                        employeeInput.middleName(),
                        employeeInput.lastName(),
                        employeeInput.email(),
                        availableAddress,
                        employeeInput.number(),
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
                employeeInput.number()
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
