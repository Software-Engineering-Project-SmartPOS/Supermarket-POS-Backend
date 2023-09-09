package com.SupermarketPOS.Backend.service.employee_management;

import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeValidationReport;
import com.SupermarketPOS.Backend.dto.employee_management.SalaryTypeInput;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.repository.employee_management.SalaryTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class SalaryTypeService {
    private final SalaryTypeRepository salaryTypeRepository;

    public SalaryTypeService(SalaryTypeRepository salaryTypeRepository){
        this.salaryTypeRepository=salaryTypeRepository;
    }


    public SalaryType getSalaryTypeBySalaryInput(SalaryTypeInput salaryTypeInput){
        //search for previously added salaryType
        try {
            SalaryType savedSalaryType = salaryTypeRepository.findByDetails(
                    salaryTypeInput.basicSalary(),
                    salaryTypeInput.halfDaySalary(),
                    salaryTypeInput.overTimeSalary(),
                    salaryTypeInput.bonus()
            );
            if(savedSalaryType != null){
                return savedSalaryType;
            }
            else throw new RuntimeException("No previous saved salary type is found in the database");
        }
        catch (Exception e){
            // no previously saved salarytype
            SalaryType newSalaryType = new SalaryType(
                    salaryTypeInput.basicSalary(),
                    salaryTypeInput.halfDaySalary(),
                    salaryTypeInput.overTimeSalary(),
                    salaryTypeInput.bonus()
            );
            return salaryTypeRepository.save(newSalaryType);
        }
    }


}
