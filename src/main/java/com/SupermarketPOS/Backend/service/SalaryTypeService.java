package com.SupermarketPOS.Backend.service;

import com.SupermarketPOS.Backend.dto.SalaryTypeInput;
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
                    salaryTypeInput.basic_salary(),
                    salaryTypeInput.half_day_salary(),
                    salaryTypeInput.overtime_salary(),
                    salaryTypeInput.bonus()
            );
            if(savedSalaryType != null){
                return savedSalaryType;
            }
            else
                throw new RuntimeException("No previous saved salary type is found in the database");
        }
        catch (Exception e){
            // no previously saved salarytype
            SalaryType new_salary_type = new SalaryType(
                    salaryTypeInput.basic_salary(),
                    salaryTypeInput.half_day_salary(),
                    salaryTypeInput.overtime_salary(),
                    salaryTypeInput.bonus()
            );
            return salaryTypeRepository.save(new_salary_type);
        }


    }
}
