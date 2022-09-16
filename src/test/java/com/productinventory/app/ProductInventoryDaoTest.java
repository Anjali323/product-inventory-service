package com.productinventory.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.productinventory.bean.OrderItem;
import com.productinventory.persistence.ProductInventoryDao;

@SpringBootTest
class ProductInventoryDaoTest {
	@Autowired
	private ProductInventoryDao productInventoryDao;
	
	private List<OrderItem> items;
	
	@BeforeEach
	void setUp() throws Exception {
		OrderItem item1 = new OrderItem(1, "A001", 100);
		OrderItem item2 = new OrderItem(2, "A002", 200);
		OrderItem item3 = new OrderItem(3, "A003", 300);
		OrderItem item4 = new OrderItem(4, "A004", 400);
		OrderItem item5 = new OrderItem(5, "A005", 500);
		
		items = new ArrayList<>();
		items.addAll(Arrays.asList(item1, item2, item3, item4, item5));
	}

	@AfterEach
	void tearDown() throws Exception {
		items.clear();
	}

	@Test
	void findByProductCodeOne() {
		String productCode = "A003";
		OrderItem item = items.stream().filter(i -> i.getProductCode().equals(productCode)).collect(Collectors.toList()).get(0);
		
		assertEquals(item, productInventoryDao.findByProductCode(productCode));
	}
	
	@Test
	void findByProductCodeTwo() {
		String productCode = "A006";
		
		assertEquals(null, productInventoryDao.findByProductCode(productCode));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeOne() {
		String productCode = "A005";
		OrderItem item = productInventoryDao.findByProductCode(productCode);
		int availableQuantity = item.getAvailableQuantity() + 5;
		
		assertTrue(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity) == 1);
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeTwo() {
		String productCode = "A006";
		int availableQuantity = 605;
		
		assertFalse(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity) == 1);
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeThree() {
		String productCode = "A005";
		int availableQuantity = -10;
		
		assertFalse(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity) == 1);
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeFour() {
		String productCode = "A006";
		int availableQuantity = -10;
		
		assertFalse(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity) == 1);
	}
}