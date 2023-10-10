package com.SupermarketPOS.Backend.service.inventory_management;
import com.SupermarketPOS.Backend.dto.inventory_management.BrandInput;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.Brand;
import com.SupermarketPOS.Backend.repository.inventory_management.BrandRepository;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final EmployeeService employeeService;
    private final DateTimeService dateTimeService;

    //inject
    public BrandService(BrandRepository brandRepository, EmployeeService employeeService, DateTimeService dateTimeService){
        this.brandRepository = brandRepository;
        this.employeeService = employeeService;
        this.dateTimeService = dateTimeService;
    }

    public Boolean isBandAvailable(String brandName){
        return brandRepository.isAvailablebyName(brandName);
    }

    public Brand AddBrand(BrandInput brandInput , Principal principal){
        Employee updatingUser =  employeeService.getByEmail(principal.getName());

        // check the availability of the brand name
        boolean isBrandAlreadyAvailable = isBandAvailable(brandInput.name()); // check by name is this brand is already added
        if( isBrandAlreadyAvailable ){
            throw new UsernameNotFoundException("Brand is already available");
        }
        else {
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            Brand newBrand =new Brand(
                    brandInput.name(),
                    brandInput.description(),
                    createdAt,
                    updatingUser.getId()
            );
            return brandRepository.save(newBrand);
        }
    }

    public List<Brand> AllBrands(){
        return brandRepository.findAll();
    }

    public Brand FindBrandById(Integer id){
        return brandRepository.findById(id).get();
    }
    public  Brand FindByName(String name){
        return brandRepository.findByName(name);
    }

    public Brand Update(BrandInput brandInput,Principal principal){
        Employee updatedBy = employeeService.getByEmail(principal.getName()); // get the user updating the brand
        Optional<Brand> brandOptional = brandRepository.findById(brandInput.id()); // find the brand to update
        if (brandOptional.isEmpty()){
            throw new UsernameNotFoundException("Brand not found");
        }
        //get and update the brand
        Brand brand = brandOptional.get();
        if(brandInput.name() != null){
            brand.setName(brandInput.name());
        }
        if (brandInput.description() != null){
            brand.setDescription(brandInput.description());
        }
        brand.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        brand.setUpdateUserId(updatedBy.getId());
        return brandRepository.save(brand);
    }
}
