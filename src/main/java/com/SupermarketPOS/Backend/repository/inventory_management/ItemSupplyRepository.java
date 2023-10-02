package com.SupermarketPOS.Backend.repository.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.ItemSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemSupplyRepository extends JpaRepository<ItemSupply, Integer> {
    Optional<ItemSupply> findByIdAndBranchId(Integer id, Integer branchId);

    List<ItemSupply> findAllByBranchId(Integer id);

    List<ItemSupply> findAllByItemId(Integer itemId);

    List<ItemSupply> findAllByItemIdAndActive(Integer itemId, boolean active);

    List<ItemSupply> findAllBySupplierIdAndActive(Integer supplierId, boolean active);

    List<ItemSupply> findAllByBranchIdAndActive(Integer branchId, Boolean active);

    Boolean existsBySupplierIdAndItemIdAndBranchIdAndActiveAndUnitCost(Integer supplierId, Integer itemId, Integer branchId, boolean b, Float unitCost);
}
