package com.SupermarketPOS.Backend.controller.customer_management;

import com.SupermarketPOS.Backend.dto.customer_management.CustomerInput;
import com.SupermarketPOS.Backend.dto.customer_management.CustomerValidationReport;
import com.SupermarketPOS.Backend.model.customer_management.Customer;
import com.SupermarketPOS.Backend.dto.customer_management.CustomerOutput;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import com.SupermarketPOS.Backend.service.customer_management.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
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
            return CustomerToCustomerOutput(savedCustomer);
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
            return CustomerToCustomerOutput(customer.get());
        }
    }
    public CustomerOutput CustomerToCustomerOutput(Customer customer){
        return new CustomerOutput(
                customer.getId(),
                customer.getName(),
                customer.getTelephone(),
                customer.getEmail(),
                customer.getCustomerAddress(),
                customer.getCustomerType(),
                customer.getLoyaltyId(),
                dateTimeService.convertTimeStampIntoString(customer.getCreatedAt()),
                dateTimeService.convertTimeStampIntoString(customer.getFirstVisited()),
                dateTimeService.convertTimeStampIntoString(customer.getLastVisited())
        );
    }



    @QueryMapping
    public List<CustomerOutput> allCustomer(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerOutput> customerOutputs = new ArrayList<>();

        for (Customer customer : customers){
            customerOutputs.add(CustomerToCustomerOutput(customer));
        }
        return  customerOutputs;
    }

}
