package com.SupermarketPOS.Backend.controller.customer_management;

import com.SupermarketPOS.Backend.dto.customer_management.CustomerInput;
import com.SupermarketPOS.Backend.dto.customer_management.CustomerValidationReport;
import com.SupermarketPOS.Backend.dto.customer_management.CustomerOutput;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import com.SupermarketPOS.Backend.service.customer_management.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController( CustomerService customerService ){
        this.customerService = customerService;
    }

    @MutationMapping
    public Optional<CustomerOutput> addCustomer(@Argument CustomerInput customerInput ){
        //check the customer list using the customerInput and get the customer if available
        Optional<CustomerOutput> savedCustomerOutput = customerService.addCustomer(customerInput);
        return savedCustomerOutput;
    }

    @MutationMapping
    public Optional<CustomerOutput> updateCustomer(@Argument CustomerInput customerUpdateInput){
        return customerService.UpdateCustomer(customerUpdateInput);
    }

    @MutationMapping
    public String InActivateCustomer(@Argument Integer customerId){
        return customerService.InActivate(customerId);
    }




    //validate the customer details
    @QueryMapping
    public CustomerValidationReport validateNewCustomerDetail(@Argument CustomerInput newCustomerDetail){
        return customerService.Validate(newCustomerDetail);
    }

    //  customerById(customerId :Integer):Customer
    @QueryMapping
    public Optional<CustomerOutput> customerById(@Argument Integer customerId){
        return customerService.GetCustomerById(customerId);
    }


    @QueryMapping
    public List<CustomerOutput> allCustomer(){
        return  customerService.getAllCustomers();
    }
}
