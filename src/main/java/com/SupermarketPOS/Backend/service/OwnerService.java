package com.SupermarketPOS.Backend.service;

import com.SupermarketPOS.Backend.dto.OwnerInput;
import com.SupermarketPOS.Backend.model.Owner;
import com.SupermarketPOS.Backend.repository.OwnerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public OwnerService(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Owner addOwner(OwnerInput ownerInput){
        Owner newOwner = new Owner(
                ownerInput.title(),
                ownerInput.firstName(),
                ownerInput.middleName(),
                ownerInput.lastName(),
                ownerInput.number(),
                passwordEncoder.encode(ownerInput.password()),
                ownerInput.email()
        );
        return ownerRepository.save(newOwner);
    }
}
