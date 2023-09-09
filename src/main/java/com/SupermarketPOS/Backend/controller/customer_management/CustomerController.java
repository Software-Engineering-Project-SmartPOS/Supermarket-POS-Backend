package com.SupermarketPOS.Backend.controller.customer_management;

import com.SupermarketPOS.Backend.dto.customer_management.CustomerInput;
import com.SupermarketPOS.Backend.dto.customer_management.CustomerValidationReport;
import com.SupermarketPOS.Backend.model.customer_management.Customer;
import com.SupermarketPOS.Backend.service.customer_management.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController( CustomerService customerService){
        this.customerService = customerService;
    }


    @MutationMapping
    public Customer addCustomer(@Argument CustomerInput customerInput){
        return customerService.addCustomer(customerInput);

    }

    @QueryMapping
    public CustomerValidationReport validateNewCustomerDetail(@Argument CustomerInput newCustomerDetail){
        return customerService.Validate(newCustomerDetail);
    }

}
