package com.SupermarketPOS.Backend.service.customer_management;

import com.SupermarketPOS.Backend.dto.customer_management.*;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.customer_management.Customer;
import com.SupermarketPOS.Backend.repository.AddressRepository;
import com.SupermarketPOS.Backend.repository.customer_management.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CustomerOutputMapper customerOutputMapper;

    public CustomerService(CustomerRepository customerRepository,AddressRepository addressRepository, CustomerOutputMapper customerOutputMapper) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.customerOutputMapper = customerOutputMapper;
    }


    @Transactional
    public Optional<CustomerOutput> addCustomer(CustomerInput customerInput){
        //check whether there are customers for the given customerInput /get the validation report
        CustomerValidationReport customerInputValidationReport = Validate(customerInput);

        //if not previously saved customer create new customer
        if(customerInputValidationReport.isEmailOkay() && customerInputValidationReport.isTelephoneOkay()){// if valid (customer in not already added/ email and the telephone is unique)
            //create new address & save
            Address customerAddress = new Address(
                    customerInput.houseNumber(),
                    customerInput.street(),
                    customerInput.city(),
                    customerInput.district(),
                    customerInput.postalCode()
            );
            addressRepository.save(customerAddress);

            // new customer is created and saved
            Customer newCustomer = new Customer(
                    customerInput.name(),
                    customerInput.telephone(),
                    customerInput.email(),
                    customerAddress,
                    customerInput.customerType(),
                    1,
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis()),
                    customerInput.password()

            );
            customerRepository.save(newCustomer);
            return GetCustomerById(newCustomer.getId());
        }
        else
            throw new RuntimeException("customer is allready added");
//                throw  new GraphQLException("customer is already there");
    }

    public Optional<CustomerOutput> UpdateCustomer(CustomerInput customerUpdateDetails){

        //only the name, address and the customer type can be changed. No the email and the telephone number can be changed
        Optional<Customer> customer = customerRepository.findById(customerUpdateDetails.id());
        if(customer.get() == null){
            throw new RuntimeException("no customer on that id");
        }
        //updating the customer details
        Customer c = customer.get();
        c.UpdateNameAndCustomerType(customerUpdateDetails.name(),customerUpdateDetails.customerType());
        //update the customer address details
        c.getCustomerAddress().UpdateAddress(
                customerUpdateDetails.houseNumber(),
                customerUpdateDetails.street(),
                customerUpdateDetails.city(),
                customerUpdateDetails.district(),
                customerUpdateDetails.postalCode()
        );
        addressRepository.save(c.getCustomerAddress());
        customerRepository.save(c);

        return Optional.of(customerOutputMapper.apply(c));
    }


    public CustomerValidationReport Validate(CustomerInput customerInputDetail){
        Boolean isTelephoneOkay = !customerRepository.isTelephoneTaken(customerInputDetail.telephone());
        Boolean isEmailOkay = !customerRepository.isEmailTaken(customerInputDetail.email());

        return new CustomerValidationReport(
                isTelephoneOkay,
                isEmailOkay
        );

    }

    public Optional<CustomerOutput> GetCustomerById(Integer id){
        return customerRepository.findById(id)
                .map(customerOutputMapper);
    }

    public List<CustomerOutput> getAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(customerOutputMapper)
                .collect(Collectors.toList());

    }
}
