package com.productinventory.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productinventory.bean.OrderItem;
import com.productinventory.service.ProductInventoryService;

@RestController
@RequestMapping("/code")
public class ProductInventoryResource {
	@Autowired
	private ProductInventoryService productInventoryService;
	
	@GetMapping("/{productCode}")
	public OrderItem getInventoryItemByProductCode(@PathVariable("productCode") String productCode) {
		return productInventoryService.getInventoryItemByProductCode(productCode);
	}
	
	@GetMapping("/{productCode}/{availableQuantity}")
	public boolean updateInventoryItemQuantityByProductCode(@PathVariable("productCode") String productCode, @PathVariable("availableQuantity") int availableQuantity) {
		return productInventoryService.updateInventoryItemQuantityByProductCode(productCode, availableQuantity);
	}
}
