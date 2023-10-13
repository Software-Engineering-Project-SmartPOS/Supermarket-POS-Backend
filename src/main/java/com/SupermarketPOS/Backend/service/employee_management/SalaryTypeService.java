package com.SupermarketPOS.Backend.service.employee_management;
import com.SupermarketPOS.Backend.dto.employee_management.SalaryTypeInput;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.repository.employee_management.SalaryTypeRepository;
import java.util.List;


import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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

    public SalaryType AddNewSalaryType (SalaryTypeInput salaryTypeInput){
        SalaryType salaryType = new SalaryType(
            salaryTypeInput.basicSalary(),
            salaryTypeInput.halfDaySalary(),
            salaryTypeInput.overTimeSalary(),
            salaryTypeInput.bonus()
        );
        return salaryTypeRepository.save(salaryType);
    }

    public SalaryType FindById(Integer id){
        return salaryTypeRepository.getById(id);
    }

    //get list of all salarytypes
    public List<SalaryType> getAllSalaryTypes(){
        return salaryTypeRepository.findAll();
    }

    public SalaryType UpdateTheSalaryType(SalaryTypeInput salaryTypeDetails) {
        SalaryType salaryType = salaryTypeRepository.findById(salaryTypeDetails.id()).orElseThrow(()-> new EntityNotFoundException("Salary Type not found"));

        if(salaryTypeDetails.basicSalary() != null){
            salaryType.setBasicSalary(salaryTypeDetails.basicSalary());
        }
        if (salaryTypeDetails.halfDaySalary() != null){
            salaryType.setHalfDaySalary(salaryTypeDetails.halfDaySalary());
        }

        if (salaryTypeDetails.overTimeSalary() != null){
            salaryType.setOverTimeSalary(salaryTypeDetails.overTimeSalary());
        }
        if(salaryTypeDetails.bonus() != null){
            salaryType.setBonus(salaryTypeDetails.bonus());
        }
        return salaryTypeRepository.save(salaryType);
    }

    public String DeleteSalaryType(Integer id) {
        try {
            salaryTypeRepository.deleteById(id);
            return "Salary Type deleted";
        }
        catch (Exception e){
            return "Could not delete salaryType";
        }
    }
}
