package com.SupermarketPOS.Backend.service;

import com.SupermarketPOS.Backend.dto.AddressInput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.repository.employee_management.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    public AddressService (AddressRepository addressRepository){
        this.addressRepository =addressRepository;
    }

    //add new address (input must be a addressInput)
    public Address AddTheAddress(AddressInput addressInput){
        Address address = new Address(
                addressInput.getStreet_name(),
                addressInput.getHouse_number(),
                addressInput.getZipcode()
        );
        // save the Address and return
        return addressRepository.save(address);
    };

    // function to get all the addresses from the database
    public List<Address> allTheAddresses(){
        return addressRepository.findAll();
    }

    //function to get the address by the id
    public Address getByTheId(Integer id){
        Address theAddress = addressRepository.getById(id);
        if (theAddress == null){
//            return null;
            throw new IllegalArgumentException("No address for that id");
        }
        return theAddress;
    }
}
