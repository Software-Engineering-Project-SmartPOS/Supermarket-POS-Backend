package com.SupermarketPOS.Backend.repository.employee_management;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//    Employee findByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);



        @Query("SELECT e FROM Employee e WHERE e.firstName = ?1 and e.middleName = ?2 and e.lastName = ?3")
    Employee findByNames(
            String firstName,
            String middleName,
            String lastName
    );

    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.firstName = ?1 and e.middleName = ?2 and e.lastName = ?3")
    Boolean isEmployeeNameTaken(
            String firstName,
            String middleName,
            String lastName
    );


    @Query("SELECT COUNT(e)>0 FROM Employee e WHERE e.number = ?1 ")
    Boolean isNumberTaken(String number);

    @Query("SELECT COUNT(e)>1 FROM Employee e WHERE e.email = ?1 ")
    Boolean isEmailTaken(String email);



}
