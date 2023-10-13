package com.SupermarketPOS.Backend.service.employee_management;

import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.service.employee_management.SalaryTypeService;

import com.SupermarketPOS.Backend.model.common.Title;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;

import com.SupermarketPOS.Backend.service.common_services.AddressService;
import com.SupermarketPOS.Backend.service.common_services.BranchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;



    @MockBean
    private AddressService addressService;

    @MockBean
    private SalaryTypeService salaryTypeService;

    @MockBean
    private BranchService branchService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testAddNewEmployee() {
        // Create a sample EmployeeInput object
        EmployeeInput employeeInput = new EmployeeInput(
                null,
                Title.MR,
                "John",
                "Doe",
                "Smith",
                "john@example.com",
                null,
                "123",
                "main rd",
                "colombo",
                "Colomboo",
                "1212",
                "1234567890", // salaryTypeId
                JobRole.ADMIN,
                1,
                "admin1",
                1
        );

        // Mock the AddressService to return a mocked Address when SaveAddress is called
        Address mockAddress = new Address();
        when(addressService.SaveAddress(any(Address .class))).thenReturn(mockAddress);

        // Mock the SalaryTypeService to return a mocked SalaryType
        SalaryType mockSalaryType = new SalaryType();
        when(salaryTypeService.FindById(anyInt())).thenReturn(mockSalaryType);

        // Mock the BranchService to return a mocked Branch
        Branch mockBranch = new Branch();
        when(branchService.findBranchById(anyInt())).thenReturn(mockBranch);

        // Mock password encoding
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Call the AddNewEmployee method
        Employee newEmployee = employeeService.AddNewEmployee(employeeInput);

        // Assert that the newEmployee object is not null
        assertNotNull(newEmployee);
        // Add more assertions as needed to verify the behavior and state of the new employee object


        // Verify that the Save Address, FindById, and save methods were called with the expected arguments
        verify(addressService).SaveAddress(any(Address.class));
        verify(salaryTypeService).FindById(anyInt());
        verify(branchService).findBranchById(anyInt());
        verify(passwordEncoder).encode(anyString());
        verify(employeeRepository).save(any(Employee.class));
    }
}
