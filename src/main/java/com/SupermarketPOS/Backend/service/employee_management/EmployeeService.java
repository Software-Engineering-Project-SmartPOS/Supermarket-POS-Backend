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

        AddressInput new_employee_address_input = employeeInput.address();// get the employee address from the employeeInput
        Address  availableAddress  = addressService.getAddressByAddressInput(new_employee_address_input); //get the address if address is saved previously get the saved address, if not save as a new address and get the address

        SalaryTypeInput new_salary_type_input = employeeInput.salary_type(); // get the salaryIput from the employeeInput
        SalaryType availableSalaryType = salaryTypeService.getSalaryTypeBySalaryInput(new_salary_type_input); // get the salary type : if saved before give the saved salary type if not create a new salary type

//        System.out.println(employeeInput.job_role());
        Employee new_employee = new Employee(
                employeeInput.title(),
                employeeInput.first_name(),
                employeeInput.middle_name(),
                employeeInput.last_name(),
                employeeInput.email(),
                availableAddress,
                employeeInput.number(),
                employeeInput.job_role(),
                availableSalaryType,
                true
        );
        return employeeRepository.save(new_employee);
    }

    public EmployeeValidationReport Validate(EmployeeInput employeeInput){
        //  validation criteria
        // no employee have the same 3 names (first, middle, last)
        //phone should be unique
        // email should be unique

        boolean is_name_okay =  !employeeRepository.isEmployeeNameTaken(
                employeeInput.first_name(),
                employeeInput.middle_name(),
                employeeInput.last_name()
        ); // check the availability of the name

        Boolean is_number_okay = !employeeRepository.isNumberTaken(
                employeeInput.number()
        );
        Boolean is_email_okay = !employeeRepository.isEmailTaken(
                employeeInput.email()
        );
        return new EmployeeValidationReport(
                is_name_okay,
                is_number_okay,
                is_email_okay
        );
    }
}
