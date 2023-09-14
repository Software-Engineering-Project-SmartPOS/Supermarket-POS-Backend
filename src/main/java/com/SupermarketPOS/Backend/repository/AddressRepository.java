package com.SupermarketPOS.Backend.repository;
import com.SupermarketPOS.Backend.model.common.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
//    get the address by the given id
    Address getById(Integer id);

//    @Query("SELECT a FROM ADDRESS a WHERE a.street_name = :street_name and a.house_number = :house_number and a.zipcode = :zipcode ")
//    Address getByDetails(
//            String street_name,
//            String house_number,
//            String zipcode);
    

/**
 * The function `getByDetails` in the Java code retrieves an `Address` object based on the provided
 * house number, street, and postal code.
 * 
 * @param houseNumber The house number of the address.
 * @param street The street parameter is a String that represents the name of the street.
 * @param postalCode The postalCode parameter is used to filter the Address entities based on their
 * postal code.
 * @return The method is returning an object of type Address.
 */
    @Query("SELECT a FROM Address a WHERE a.houseNumber = :houseNumber AND a.street = :street AND a.postalCode = :postalCode")
    public Address getByDetails(
        @Param("houseNumber") String houseNumber, 
        @Param("street") String street, 
        @Param("postalCode") String postalCode);



   @Query("SELECT a FROM Address a WHERE a.id =  ?1")
    Optional<Address> findById(Integer id);
}
