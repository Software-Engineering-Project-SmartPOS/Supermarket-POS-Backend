package com.SupermarketPOS.Backend.repository.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Integer> {
    List<PurchaseOrderItem> findAllByPurchaseOrderId(Integer id);
}
