package com.productinventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productinventory.bean.OrderItem;
import com.productinventory.persistence.ProductInventoryDao;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {
	@Autowired
	private ProductInventoryDao productInventoryDao;

	@Override
	public OrderItem getInventoryItemByProductCode(String productCode) {
		return productInventoryDao.findByProductCode(productCode);
	}

	@Override
	public boolean updateInventoryItemQuantityByProductCode(String productCode, int availableQuantity) {
		if(availableQuantity < 0) return false;
		
		return productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity) > 0;
	}
}