package com.SupermarketPOS.Backend.service.employee_management;

import com.SupermarketPOS.Backend.dto.employee_management.AddressInput;
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
        try{
        Address theAddress = addressRepository.getById(id);
        if (theAddress == null){
//            return null;
            throw new IllegalArgumentException("No address for that id");
        }
        return theAddress;
        }
        catch (Exception e){
            return null;
        }
    }

    public Address getAddressByAddressInput(AddressInput addressInput){
        try{
            // if address available return the address
            Address savedAddress = addressRepository.getByDetails(
                                            addressInput.getStreet_name(),
                                            addressInput.getHouse_number(),
                                            addressInput.getZipcode());
            if(savedAddress != null){
                return savedAddress;
            }
            else throw new RuntimeException("no previously saved address is found in the database");
        }
        catch (Exception e){
            // if an exception occured address is not added previously, so add and return the new address
            Address new_address = new Address(
                    addressInput.getStreet_name(),
                    addressInput.getHouse_number(),
                    addressInput.getZipcode()
            );
            return addressRepository.save(new_address);
        }
    }
}
