package com.SupermarketPOS.Backend.repository.common;

import com.SupermarketPOS.Backend.model.common.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {
}
