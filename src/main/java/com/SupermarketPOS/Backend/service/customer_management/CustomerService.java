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

    public Customer addCustomer(CustomerInput customer_input){
        CustomerValidationReport customer_input_validation_report = Validate(customer_input);

        if(customer_input_validation_report.is_email_okay() && customer_input_validation_report.is_telephone_okay()){// if valid (customer in not already added/ email and the telephone is unique)
                    Customer new_customer = new Customer(
                    customer_input.name(),
                    customer_input.telephone(),
                    customer_input.email(),
                    LocalDateTime.now()
            );
            return customerRepository.save(new_customer);
        }
        else
            return null;
    }

    public CustomerValidationReport Validate(CustomerInput customer_input_detail){
        Boolean is_telephone_okay = !customerRepository.isTelephoneTaken(customer_input_detail.telephone());
        Boolean is_email_okay = !customerRepository.isEmailTaken(customer_input_detail.email());

        return new CustomerValidationReport(
                is_telephone_okay,
                is_email_okay
        );

    }
}
