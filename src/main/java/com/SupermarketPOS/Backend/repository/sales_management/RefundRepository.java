package com.SupermarketPOS.Backend.repository.sales_management;

import com.SupermarketPOS.Backend.model.sales_management.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface RefundRepository extends JpaRepository<Refund, CriteriaBuilder.In> {

}
