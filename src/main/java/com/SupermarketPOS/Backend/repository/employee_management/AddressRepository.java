package com.SupermarketPOS.Backend.repository.employee_management;

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
    @Query("SELECT a FROM Address a WHERE a.streetName = :streetName AND a.houseNumber = :houseNumber AND a.zipcode = :zipcode")
    Address getByDetails(
            @Param("streetName") String streetName,
            @Param("houseNumber") String houseNumber,
            @Param("zipcode") String zipcode
    );


//    @Query("SELECT a FROM Address a WHERE a.id =  ?1")
    Optional<Address> findById(Integer id);
}
