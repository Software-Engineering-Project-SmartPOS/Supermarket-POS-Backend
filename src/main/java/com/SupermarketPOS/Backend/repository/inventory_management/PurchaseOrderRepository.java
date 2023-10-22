package com.SupermarketPOS.Backend.repository.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrder;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

    List<PurchaseOrder> findAllByBranchId(Integer id);

    Optional<PurchaseOrder> findByIdAndBranchId(Integer id, Integer id1);

    List<PurchaseOrder> findAllBySupplierIdAndBranchId(Integer supplierId, Integer id);

    List<PurchaseOrder> findAllByOrderStatusAndBranchId(PurchaseOrderStatus status, Integer id);


}
