package com.SupermarketPOS.Backend.repository.employee_management;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository underTest;

    @Autowired
    private TestEntityManager entityManager;

//    @BeforeEach
//    void tearUp(){
//        underTest.save()
//    }

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    public void testFindByEmail() {
        // Create a sample employee entity and persist it
        Employee employee = new Employee();
        employee.setEmail("test@example.com");
        entityManager.persist(employee);

        // Call the findByEmail method
        Optional<Employee> foundEmployee = underTest.findByEmail("test@example.com");

        // Assert that the employee was found
        assertTrue(foundEmployee.isPresent());
        assertEquals("test@example.com", foundEmployee.get().getEmail());
    }

    @Test
    public void testFindByEmail_NotFound() {
        // Call the findByEmail method with an email that doesn't exist
        Optional<Employee> foundEmployee = underTest.findByEmail("nonexistent@example.com");

        // Assert that the employee was not found (Optional should be empty)
        assertFalse(foundEmployee.isPresent());
    }






}