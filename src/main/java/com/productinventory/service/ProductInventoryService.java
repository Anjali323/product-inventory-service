package com.productinventory.service;

import com.productinventory.bean.OrderItem;

public interface ProductInventoryService {
	OrderItem getInventoryItemByProductCode(String productCode);
	
	boolean updateInventoryItemQuantityByProductCode(String productCode, int availableQuantity);
}
