package com.SupermarketPOS.Backend.service.customer_management;

import com.SupermarketPOS.Backend.dto.customer_management.CustomerAddressInput;
import com.SupermarketPOS.Backend.dto.customer_management.CustomerInput;
import com.SupermarketPOS.Backend.dto.customer_management.CustomerValidationReport;
import com.SupermarketPOS.Backend.model.customer_management.Customer;
import com.SupermarketPOS.Backend.model.customer_management.CustomerAddress;
import com.SupermarketPOS.Backend.repository.customer_management.CustomerAddressRepository;
import com.SupermarketPOS.Backend.repository.customer_management.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;

    public CustomerService(CustomerRepository customerRepository , CustomerAddressRepository customerAddressRepository){
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
    }

    @Transactional
    public Customer addCustomer(CustomerInput customerInput){
        CustomerValidationReport customerInputValidationReport = Validate(customerInput);

        if(customerInputValidationReport.isEmailOkay() && customerInputValidationReport.isTelephoneOkay()){// if valid (customer in not already added/ email and the telephone is unique)
            CustomerAddress customerAddress = new CustomerAddress(
                    customerInput.customerAddress().address(),
                    customerInput.customerAddress().city(),
                    customerInput.customerAddress().district(),
                    customerInput.customerAddress().postalCode()
            );
            Customer newCustomer = new Customer(
                    customerInput.name(),
                    customerInput.telephone(),
                    customerInput.email(),
                    customerAddress,
                    customerInput.customerType(),
                    new Timestamp(System.currentTimeMillis())
            );
            customerAddressRepository.save(customerAddress);

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
    public Customer GetCustomerById( Integer id){
        return customerRepository.findById( Integer.toString(id));
    }
}
