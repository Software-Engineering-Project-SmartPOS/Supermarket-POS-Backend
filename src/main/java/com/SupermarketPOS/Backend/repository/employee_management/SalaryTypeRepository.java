package com.SupermarketPOS.Backend.repository.employee_management;

import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalaryTypeRepository extends JpaRepository<SalaryType,Integer> {
        @Query("SELECT st FROM SalaryType st WHERE st.basic_salary = :basic_salary and st.half_day_salary = :half_day_salary and st.overtime_salary = :overtime_salary and st.bonus = :bonus")
        SalaryType findByDetails(
                @Param("basic_salary") Double basic_salary,
                @Param("half_day_salary") Double half_day_salary,
                @Param("overtime_salary") Double overtime_salary,
                @Param("bonus") Double bonus
        );


}


//@Query("SELECT a FROM Address a WHERE a.street_name = :streetName AND a.house_number = :houseNumber AND a.zipcode = :zipcode")
//    Address getByDetails(
//@Param("streetName") String streetName,
//@Param("houseNumber") String houseNumber,
//@Param("zipcode") String zipcode
//        );