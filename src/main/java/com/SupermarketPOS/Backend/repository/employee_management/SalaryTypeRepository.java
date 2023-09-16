package com.SupermarketPOS.Backend.repository.employee_management;

import com.SupermarketPOS.Backend.model.employee_management.SalaryType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;



public interface SalaryTypeRepository extends JpaRepository<SalaryType,Integer> {
        @Query("SELECT st FROM SalaryType st WHERE st.basicSalary = :basicSalary and st.halfDaySalary = :halfDaySalary and st.overTimeSalary = :overTimeSalary and st.bonus = :bonus")
        SalaryType findByDetails(
                @Param("basicSalary") Float basicSalary,
                @Param("halfDaySalary") Float halfDaySalary,
                @Param("overTimeSalary") Float overTimeSalary,
                @Param("bonus") Float bonus
        );

        @Query("SELECT st FROM SalaryType st WHERE st.id = ?1")
        SalaryType getById(Integer id);


//        Optional<SalaryType> findById(Integer id);

}

