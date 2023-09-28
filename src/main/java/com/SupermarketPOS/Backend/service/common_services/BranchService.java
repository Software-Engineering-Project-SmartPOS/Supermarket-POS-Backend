package com.SupermarketPOS.Backend.service.common_services;

import com.SupermarketPOS.Backend.dto.common.BranchInput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.repository.common.BranchRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    private  final BranchRepository branchRepository;
    private final  AddressService addressService;


    public BranchService(BranchRepository branchRepository, AddressService addressService) {
        this.branchRepository = branchRepository;
        this.addressService = addressService;
    }

    public Branch addNewBranch(BranchInput branchInput){
        // create and return a new address
        Address branchAddress = addressService.SaveAddres(
                new Address(
                        branchInput.houseNumber(),
                        branchInput.street(),
                        branchInput.city(),
                        branchInput.district(),
                        branchInput.postalCode()
                )
        );
        Branch newBranch = new Branch(
                branchInput.name(),
                branchInput.telephone(),
                branchAddress
        );
        return branchRepository.save(newBranch);
    }

    public Branch findBranchById(int id){
        return branchRepository.findById(id).get();
    }

}
