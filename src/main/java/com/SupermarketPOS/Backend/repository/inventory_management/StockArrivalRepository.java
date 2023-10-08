package com.SupermarketPOS.Backend.repository.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.StockArrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockArrivalRepository extends JpaRepository<StockArrival,Integer> {
    List<StockArrival> findAllByBranchId(Integer id);

    List<StockArrival> findAllByBranchIdAndIsTransferredToStore(Integer id, Boolean transferred);
}
