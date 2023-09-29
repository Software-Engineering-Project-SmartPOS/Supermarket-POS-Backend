package com.SupermarketPOS.Backend.service.common_services;

import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.repository.common.AddressRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    public AddressService (AddressRepository addressRepository){
        this.addressRepository =addressRepository;
    }

    //add new address (input must be a addressInput)
    // public Address AddTheAddress(AddressInput addressInput){
    //     Address address = new Address(
    //         addressInput.getHouseNumber(),
    //         addressInput.getStreetName(),
    //         addressInput.get         
    //     );
    //     // save the Address and return
    //     return addressRepository.save(address);
    // };
    // function to get all the addresses from the database

    // public Address getAddressByAddressInput(AddressInput addressInput){
    //     try{
    //         // if address available return the address
    //         Address savedAddress = addressRepository.getByDetails(
    //                                         addressInput.getStreetName(),
    //                                         addressInput.getHouseNumber(),
    //                                         addressInput.getZipcode());
    //         if(savedAddress != null){
    //             return savedAddress;
    //         }
    //         else throw new RuntimeException("no previously saved address is found in the database");
    //     }
    //     catch (Exception e){
    //         // if an exception occured address is not added previously, so add and return the new address
    //         Address newAddress = new Address(
    //                 addressInput.getStreetName(),
    //                 addressInput.getHouseNumber(),
    //                 addressInput.getZipcode()
    //         );
    //         return addressRepository.save(newAddress);
    //     }
    // }




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
            System.out.println(theAddress);
        return theAddress;
        }
        catch (Exception e){
            return null;
        }
    }

    public Address SaveAddres(Address address){
        return addressRepository.save(address);
    }


    
}
