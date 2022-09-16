package com.productinventory.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.productinventory.bean.OrderItem;

@Repository
public interface ProductInventoryDao extends JpaRepository<OrderItem, Long> {
	OrderItem findByProductCode(String productCode);
	
	@Transactional
	@Modifying
	@Query(value = "update item set availableQuantity = :availableQuantity where productCode = :productCode and :availableQuantity >= 0", nativeQuery = true)
	int updateInventoryItemQuantityByProductCode(@Param("productCode") String productCode, @Param("availableQuantity") int availableQuantity);
}
