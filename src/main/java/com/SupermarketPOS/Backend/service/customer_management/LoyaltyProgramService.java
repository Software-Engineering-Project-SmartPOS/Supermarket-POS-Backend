package com.SupermarketPOS.Backend.service.customer_management;

import com.SupermarketPOS.Backend.dto.customer_management.LoyaltyProgramInput;
import com.SupermarketPOS.Backend.model.customer_management.LoyaltyProgram;
import com.SupermarketPOS.Backend.repository.customer_management.LoyaltyProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoyaltyProgramService {
    private final LoyaltyProgramRepository loyaltyProgramRepository;

    public LoyaltyProgramService(LoyaltyProgramRepository loyaltyProgramRepository) {
        this.loyaltyProgramRepository = loyaltyProgramRepository;
    }




    public LoyaltyProgram AddNewLoyaltyProgram(LoyaltyProgramInput loyaltyProgramInput){
        LoyaltyProgram newProgram = new LoyaltyProgram(
                loyaltyProgramInput.loyaltyProgramName(),
                loyaltyProgramInput.description(),
                loyaltyProgramInput.pointsThreshold(),
                loyaltyProgramInput.discountPercentage()

        );
        return loyaltyProgramRepository.save(newProgram);
    }
    public LoyaltyProgram UpdateLoyaltyProgram(LoyaltyProgramInput updateDetail){
        Optional<LoyaltyProgram> existingProgram = findLoyaltyProgramById(updateDetail.id());
        if (existingProgram.isPresent()){
            LoyaltyProgram lp = existingProgram.get();
            lp.setLoyaltyProgramName(updateDetail.loyaltyProgramName());
            lp.setDescription(updateDetail.description());
            lp.setPointsThreshold(updateDetail.pointsThreshold());
            lp.setDiscountPercentage(updateDetail.discountPercentage());

            return loyaltyProgramRepository.save(lp);
        }
        else {
            return null;
        }

    }

    public List<LoyaltyProgram> getAllLoyaltyPrograms(){
        return loyaltyProgramRepository.findAll();
    }

    public Optional<LoyaltyProgram> findLoyaltyProgramById(Integer id){
        return loyaltyProgramRepository.findById(id);
    }
}
