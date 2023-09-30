package com.SupermarketPOS.Backend.repository.common;

import com.SupermarketPOS.Backend.model.common.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {
    @Query("SELECT br FROM Branch br WHERE br.id = ?1")
    Branch getById(Integer id);
}
