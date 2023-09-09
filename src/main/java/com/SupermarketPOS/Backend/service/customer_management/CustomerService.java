package com.SupermarketPOS.Backend.service.customer_management;

import com.SupermarketPOS.Backend.dto.customer_management.CustomerInput;
import com.SupermarketPOS.Backend.dto.customer_management.CustomerValidationReport;
import com.SupermarketPOS.Backend.model.customer_management.Customer;
import com.SupermarketPOS.Backend.repository.customer_management.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(CustomerInput customerInput){
        CustomerValidationReport customerInputValidationReport = Validate(customerInput);

        if(customerInputValidationReport.isEmailOkay() && customerInputValidationReport.isTelephoneOkay()){// if valid (customer in not already added/ email and the telephone is unique)
                    Customer newCustomer = new Customer(
                    customerInput.name(),
                    customerInput.telephone(),
                    customerInput.email(),
                    LocalDateTime.now()
            );
            return customerRepository.save(newCustomer);
        }
        else
            return null;
    }

    public CustomerValidationReport Validate(CustomerInput customerInputDetail){
        Boolean isTelephoneOkay = !customerRepository.isTelephoneTaken(customerInputDetail.telephone());
        Boolean isEmailOkay = !customerRepository.isEmailTaken(customerInputDetail.email());

        return new CustomerValidationReport(
                isTelephoneOkay,
                isEmailOkay
        );

    }
}
