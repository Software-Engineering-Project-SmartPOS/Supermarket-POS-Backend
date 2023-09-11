package com.SupermarketPOS.Backend.controller.customer_management;

import com.SupermarketPOS.Backend.dto.customer_management.CustomerInput;
import com.SupermarketPOS.Backend.dto.customer_management.CustomerValidationReport;
import com.SupermarketPOS.Backend.model.customer_management.Customer;
import com.SupermarketPOS.Backend.model.customer_management.CustomerOutput;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import com.SupermarketPOS.Backend.service.customer_management.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class CustomerController {
    private final CustomerService customerService;
    private final DateTimeService dateTimeService;

    public CustomerController( CustomerService customerService , DateTimeService dateTimeService){
        this.customerService = customerService;
        this.dateTimeService =dateTimeService;
    }


    @MutationMapping
    public CustomerOutput addCustomer(@Argument CustomerInput customerInput){
        Customer savedCustomer= customerService.addCustomer(customerInput);
        // change the type
        if (savedCustomer !=null){
            return new CustomerOutput(
                    savedCustomer.getId(),
                    savedCustomer.getName(),
                    savedCustomer.getTelephone(),
                    savedCustomer.getEmail(),
                    savedCustomer.getCustomerAddress(),
                    savedCustomer.getCustomerType(),
                    savedCustomer.getLoyaltyId(),
                    dateTimeService.convertTimeStampIntoString(savedCustomer.getCreatedAt())

            );
        }
        else
            return null;

    }

    @QueryMapping
    public CustomerValidationReport validateNewCustomerDetail(@Argument CustomerInput newCustomerDetail){
        return customerService.Validate(newCustomerDetail);
    }
    //  customerById(customerId :Integer):Customer
    @QueryMapping
    public CustomerOutput customerById(@Argument Integer customerId){
        Optional<Customer> customer = customerService.GetCustomerById(customerId);
        if(!customer.isPresent()){
            return null;
        }
        else {
            return new CustomerOutput(
                    customer.get().getId(),
                    customer.get().getName(),
                    customer.get().getTelephone(),
                    customer.get().getEmail(),
                    customer.get().getCustomerAddress(),
                    customer.get().getCustomerType(),
                    customer.get().getLoyaltyId(),
                    dateTimeService.convertTimeStampIntoString(customer.get().getCreatedAt())


            );
        }

    }

}
//avedCustomer.getId(),
//                    savedCustomer.getName(),
//                    savedCustomer.getTelephone(),
//                    savedCustomer.getEmail(),
//                    savedCustomer.getCustomerAddress(),
//                    savedCustomer.getCustomerType(),
//                    savedCustomer.getLoyaltyId(),
//                    dateTimeService.convertLocalDateTimeIntoString(savedCustomer.getCreatedAt())