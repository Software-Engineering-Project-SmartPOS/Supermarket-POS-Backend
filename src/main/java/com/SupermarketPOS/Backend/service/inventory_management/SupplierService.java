package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.SupplierInput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.Supplier;
import com.SupermarketPOS.Backend.repository.common.AddressRepository;
import com.SupermarketPOS.Backend.repository.inventory_management.SupplierRepository;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final AddressRepository addressRepository;
    private final SupplierRepository supplierRepository;
    private final EmployeeService employeeService;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, EmployeeService employeeService,AddressRepository addressRepository) {
        this.supplierRepository = supplierRepository;
        this.employeeService = employeeService;
        this.addressRepository = addressRepository;
    }

    public Supplier CreateSupplier(SupplierInput supplierInput, Principal principal) {
        Employee createdBy = employeeService.getByEmail(principal.getName());
        Address supplierAddress = new Address(
                supplierInput.houseNumber(),
                supplierInput.street(),
                supplierInput.city(),
                supplierInput.district(),
                supplierInput.postalCode()
        );
        Address savedAddress = addressRepository.save(supplierAddress);
        Supplier newSupplier = new Supplier(
                supplierInput.name(),
                supplierInput.landPhone(),
                supplierInput.mobilePhone(),
                supplierInput.email(),
                savedAddress,
                new Timestamp(System.currentTimeMillis()),
                createdBy,
                supplierInput.active()
        );

        return supplierRepository.save(newSupplier);
    }

    public List<Supplier> GetAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier GetSupplierById(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElse(null);
    }
    public Supplier GetSupplierByEmail(String name) {
        Optional<Supplier> optionalSupplier = supplierRepository.findByEmail(name);
        return optionalSupplier.orElse(null);
    }

    public Supplier UpdateSupplier(SupplierInput supplierInput, Principal principal) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierInput.id());
        if (optionalSupplier.isPresent()) {
            Employee updatedBy = employeeService.getByEmail(principal.getName());
            Supplier supplierObj = optionalSupplier.get();

            // Update supplier details based on SupplierInput

            if(supplierInput.name() != null){supplierObj.setName(supplierInput.name());}

            if (supplierInput.landPhone() != null){
                supplierObj.setLandPhone(supplierInput.landPhone());
            }
            if (supplierInput.mobilePhone() != null){
                supplierObj.setMobilePhone(supplierInput.mobilePhone());
            }
            if (supplierInput.email() != null){
                supplierObj.setEmail(supplierInput.email());
            }

            // Update supplier address
            Address supplierAddress = supplierObj.getAddress();
            if ( supplierInput.houseNumber()!= null){
                supplierAddress.setHouseNumber(supplierInput.houseNumber());
            }
            if ( supplierInput.street() != null){
                supplierAddress.setStreet(supplierInput.street());
            }
            if ( supplierInput.city()!= null){
                supplierAddress.setCity(supplierInput.city());
            }
            if ( supplierInput.district() != null){
                supplierAddress.setDistrict(supplierInput.district());
            }
            if ( supplierInput.postalCode()!= null){
                supplierAddress.setPostalCode(supplierInput.postalCode());
            }

            supplierObj.setAddress(supplierAddress);
            supplierObj.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            supplierObj.setUpdatedEmployee(updatedBy);
            supplierObj.setActive(supplierInput.active());


            return supplierRepository.save(supplierObj);
        }
        return null;
    }

    public void deleteSupplier(Integer id) {
        supplierRepository.deleteById(id);
    }
}
