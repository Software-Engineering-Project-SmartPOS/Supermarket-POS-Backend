package com.SupermarketPOS.Backend.repository.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.StockLevel;
import com.SupermarketPOS.Backend.model.inventory_management.StockLevelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockLevelRepository extends JpaRepository<StockLevel,Integer> {

    List<StockLevel> findAllByBranchId(Integer id);

//    List<StockLevel> findAllByBranchIdAndStatus(Integer id, StockLevelStatus status);

    List<StockLevel> findAllByBranchIdAndItemIdAndStatus(Integer id, Integer id1, StockLevelStatus status);

    List<StockLevel> findAllByBranchIdAndStatus(Integer branchId,  StockLevelStatus status);

    StockLevel findByBranchIdAndId(Integer id, Integer id1);

}
