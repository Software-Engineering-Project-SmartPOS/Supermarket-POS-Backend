package com.SupermarketPOS.Backend.service.employee_management;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeInput;
import com.SupermarketPOS.Backend.dto.employee_management.EmployeeValidationReport;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.model.common.JobRole;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.employee_management.SalaryType;
import com.SupermarketPOS.Backend.model.common.Title;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
import com.SupermarketPOS.Backend.service.common_services.AddressService;
import com.SupermarketPOS.Backend.service.common_services.BranchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {
//    @Autowired
    private  EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private AddressService addressService;
    @Mock
    private SalaryTypeService salaryTypeService;
    @Mock
    private BranchService branchService;
    @Mock
    private PasswordEncoder passwordEncoder;

    private AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        // Define the behavior of the mock
        when(addressService.SaveAddress(any(Address.class))).thenReturn(new Address());
        when(salaryTypeService.FindById(any(Integer.class))).thenReturn(new SalaryType());
        when(branchService.findBranchById(any(Integer.class))).thenReturn(new Branch());
        when(passwordEncoder.encode(any(String.class))).thenReturn(new String());
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());
        // initialise the employee service
        employeeService =new EmployeeService(
                employeeRepository,
                salaryTypeService,
                addressService,
                passwordEncoder,
                branchService
        );

        // Mock the SalaryTypeService to return a mocked SalaryType
//        SalaryType mockSalaryType = new SalaryType();
//        when(salaryTypeService.FindById(Mockito.anyInt())).thenReturn(mockSalaryType);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
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
                "Colombo",
                "1212",
                "1234567890", // salaryTypeId
                JobRole.ADMIN,
                1,
                "admin1",
                1
        );

        // Call the AddNewEmployee method
        Employee newEmployee = employeeService.AddNewEmployee(employeeInput);

        System.out.println(newEmployee);


        // assert that not null is returned

        assertNotNull(newEmployee);

        // Verify that the SaveAddress, FindById, and save methods were called with the expected arguments
        Mockito.verify(addressService).SaveAddress(Mockito.any(Address.class));
        Mockito.verify(salaryTypeService).FindById(Mockito.anyInt());
        Mockito.verify(branchService).findBranchById(Mockito.anyInt());
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
        Mockito.verify(employeeRepository).save(Mockito.any(Employee.class));


    }


    @Test
    public void testAddNewEmployeeThrowException() {
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
                "Colombo",
                "1212",
                "1234567890", // salaryTypeId
                JobRole.ADMIN,
                1,
                "admin1",
                1
        );

        when(employeeService.Validate(employeeInput)).thenReturn( new EmployeeValidationReport(
                true,true,true
        ));

        // Call the AddNewEmployee method
        Employee newEmployee = employeeService.AddNewEmployee(employeeInput);

        System.out.println(newEmployee);


        //Assert that error is occured
        assertThatThrownBy(() -> employeeService.AddNewEmployee(employeeInput))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User is already there");
        // Verify that the SaveAddress, FindById, and save methods were called with the expected arguments
        Mockito.verify(addressService).SaveAddress(Mockito.any(Address.class));
        Mockito.verify(salaryTypeService).FindById(Mockito.anyInt());
        Mockito.verify(branchService).findBranchById(Mockito.anyInt());
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
        Mockito.verify(employeeRepository).save(Mockito.any(Employee.class));


    }
}
