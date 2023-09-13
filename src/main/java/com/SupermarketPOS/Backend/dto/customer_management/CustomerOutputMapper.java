package com.SupermarketPOS.Backend.dto.customer_management;

import com.SupermarketPOS.Backend.model.customer_management.Customer;
import graphql.GraphQLException;
import org.springframework.stereotype.Service;

import java.util.function.Function;



@Service
public class CustomerOutputMapper implements Function<Customer, CustomerOutput> {

    @Override
    public CustomerOutput apply(Customer customer) {
        if(customer == null){
            throw new GraphQLException("customer is nullllllllllllllll");
        }
        //this will give an exception if the customer is null
        return new CustomerOutput(
                customer.getId(),
                customer.getName(),
                customer.getTelephone(),
                customer.getEmail(),
                customer.getCustomerAddress(),
                customer.getCustomerType(),
                customer.getLoyaltyId(),
                customer.getCreatedAt(),
                customer.getFirstVisited(),
                customer.getLastVisited()

        );
    }
}
