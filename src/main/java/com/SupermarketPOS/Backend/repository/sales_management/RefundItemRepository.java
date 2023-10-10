package com.SupermarketPOS.Backend.repository.sales_management;

import com.SupermarketPOS.Backend.model.sales_management.RefundItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundItemRepository extends JpaRepository<RefundItem,Integer> {

}
