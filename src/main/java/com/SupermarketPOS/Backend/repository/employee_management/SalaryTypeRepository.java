package com.SupermarketPOS.Backend.repository.employee_management;

import com.SupermarketPOS.Backend.model.employee_management.SalaryType;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalaryTypeRepository extends JpaRepository<SalaryType,Integer> {
        @Query("SELECT st FROM SalaryType st WHERE st.basicSalary = :basicSalary and st.halfDaySalary = :halfDaySalary and st.overTimeSalary = :overTimeSalary and st.bonus = :bonus")
        SalaryType findByDetails(
                @Param("basicSalary") Double basicSalary,
                @Param("halfDaySalary") Double halfDaySalary,
                @Param("overTimeSalary") Double overTimeSalary,
                @Param("bonus") Double bonus
        );
}

