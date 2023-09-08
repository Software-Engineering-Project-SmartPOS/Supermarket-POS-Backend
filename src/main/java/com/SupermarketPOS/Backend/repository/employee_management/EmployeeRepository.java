package com.SupermarketPOS.Backend.repository.employee_management;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.first_name = ?1 and e.middle_name = ?2 and e.last_name = ?3")
    Employee findByNames(
            String first_name,
            String middle_name,
            String last_name
    );

    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.first_name = ?1 and e.middle_name = ?2 and e.last_name = ?3")
    Boolean isEmployeeNameTaken(
            String first_name,
            String middle_name,
            String last_name
    );

    @Query("SELECT COUNT(e)>0 FROM Employee e WHERE e.number = ?1 ")
    Boolean isNumberTaken(String number);

    @Query("SELECT COUNT(e)>1 FROM Employee e WHERE e.email = ?1 ")
    Boolean isEmailTaken(String email);



}
