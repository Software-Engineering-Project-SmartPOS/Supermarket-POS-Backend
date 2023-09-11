package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.BrandInput;
import com.SupermarketPOS.Backend.dto.inventory_management.BrandOutput;
import com.SupermarketPOS.Backend.model.inventory_management.Brand;
import com.SupermarketPOS.Backend.repository.inventory_management.BrandRepository;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final DateTimeService dateTimeService;


    //inject
    public BrandService(BrandRepository brandRepository , DateTimeService dateTimeService){
        this.brandRepository = brandRepository;
        this.dateTimeService = dateTimeService;
    }

    public Boolean isBandAvailable(String brandName){
        return brandRepository.isAvailablebyName(brandName);
    }

    public BrandOutput AddBrand(BrandInput brandInput){
        boolean isBrandAlreadyAvailable = isBandAvailable(brandInput.name()); // check by name is this brand is already added

        if(isBrandAlreadyAvailable){
            return null;
        }
        else {
            Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
            Brand newBrand =new Brand(
                    brandInput.name(),
                    brandInput.description(),
                    updatedAt
            );
            Brand newBrandSaved = brandRepository.save(newBrand);

            BrandOutput brandOutput = new BrandOutput(
                    newBrandSaved.getId(),
                    newBrandSaved.getName(),
                    newBrandSaved.getDescription(),
                    dateTimeService.convertTimeStampIntoString(newBrandSaved.getUpdateAt())
            );
            return brandOutput;
        }

    }

}
