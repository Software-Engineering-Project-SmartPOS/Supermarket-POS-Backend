package com.SupermarketPOS.Backend.service;

import com.SupermarketPOS.Backend.dto.AddressInput;
import com.SupermarketPOS.Backend.dto.EmployeeInput;
import com.SupermarketPOS.Backend.dto.EmployeeValidationReport;
import com.SupermarketPOS.Backend.dto.SalaryTypeInput;
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
        SalaryType availableSalaryType = salaryTypeService.getSalaryTypeBySalaryInput(new_salary_type_input);


//        SalaryType availableSalaryType = SalaryTypeService.getSalaryTypeBySalaryInput(new_salary_type_input);
//        SalaryType new_salary_type = new SalaryType(
//                new_salary_type_input.basic_salary(),
//                new_salary_type_input.half_day_salary(),
//                new_salary_type_input.overtime_salary(),
//                new_salary_type_input.bonus()
//        );
//        SalaryType availableSalaryType = salaryTypeService.isSalaryTypeAvailable(new_salary_type_input);

        JobRole new_job_role = JobRole.CASHIER  ;

        Employee new_employee = new Employee(
                employeeInput.title(),
                employeeInput.first_name(),
                employeeInput.middle_name(),
                employeeInput.last_name(),
                employeeInput.email(),
                availableAddress,
                employeeInput.number(),
                new_job_role,
                availableSalaryType,
                true

        );


        return employeeRepository.save(new_employee);
    }
}
