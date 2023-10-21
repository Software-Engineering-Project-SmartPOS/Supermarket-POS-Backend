package com.SupermarketPOS.Backend.service.common_services;

import com.SupermarketPOS.Backend.dto.common.BranchInput;
import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.repository.common.BranchRepository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
        Address branchAddress = addressService.SaveAddress(
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
        return branchRepository.getById(id);

    }

    public List<Branch> GetAllBranches() {
        return branchRepository.findAll();
    }

    public Branch UpdateTheBranch(BranchInput branchDetails) {
        Branch branch= branchRepository.findById(branchDetails.id())
                .orElseThrow( ()->new EntityNotFoundException("couldn't find the branch")
        );
        if (branchDetails.name() != null){
            branch.setName(branchDetails.name());
        }
        if (branchDetails.telephone() != null){
            branch.setTelephone(branchDetails.telephone());
        }

        if (branchDetails.houseNumber() != null){
            branch.getBranchAddress().setHouseNumber(branchDetails.houseNumber());
        }
        if (branchDetails.street() != null){
            branch.getBranchAddress().setStreet(branchDetails.street());
        }
        if (branchDetails.city() != null){
            branch.getBranchAddress().setCity(branchDetails.city());
        }
        if (branchDetails.district() != null){
            branch.getBranchAddress().setDistrict(branchDetails.district());
        }
        if (branchDetails.postalCode() != null){
            branch.getBranchAddress().setPostalCode(branchDetails.postalCode());
        }

        return branchRepository.save(branch);

    }

    public String DeleteBranchById(Integer id) {
        try {
            branchRepository.deleteById(id);
            return "branch deleted";
        }
        catch (Exception e){
            return "could not delete branch";
        }
    }
}
