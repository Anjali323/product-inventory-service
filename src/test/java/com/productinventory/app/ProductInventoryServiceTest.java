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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.productinventory.bean.OrderItem;
import com.productinventory.persistence.ProductInventoryDao;
import com.productinventory.service.ProductInventoryServiceImpl;

@SpringBootTest
class ProductInventoryServiceTest {
	@Autowired
	@InjectMocks
	private ProductInventoryServiceImpl productInventoryService;

	@Mock
	private ProductInventoryDao productInventoryDao;

	private AutoCloseable autoCloseable;

	private List<OrderItem> items;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);

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
		autoCloseable.close();
	}

	@Test
	void getInventoryItemByProductCodeOne() {
		String productCode = "A001";
		OrderItem item = items.stream().filter(i -> i.getProductCode().equals(productCode)).collect(Collectors.toList()).get(0);
		Mockito.when(productInventoryDao.findByProductCode(productCode)).thenReturn(item);
		
		assertEquals(item, productInventoryService.getInventoryItemByProductCode(productCode));
	}
	
	@Test
	void getInventoryItemByProductCodeTwo() {
		String productCode = "A006";
		OrderItem item = null;
		Mockito.when(productInventoryDao.findByProductCode(productCode)).thenReturn(item);
		
		assertEquals(item, productInventoryService.getInventoryItemByProductCode(productCode));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeOne() {
		String productCode = "A001";
		int availableQuantity = 105;
		Mockito.when(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity)).thenReturn(1);
	
		assertTrue(productInventoryService.updateInventoryItemQuantityByProductCode(productCode, availableQuantity));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeTwo() {
		String productCode = "A006";
		int availableQuantity = 106;
		Mockito.when(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity)).thenReturn(0);
	
		assertFalse(productInventoryService.updateInventoryItemQuantityByProductCode(productCode, availableQuantity));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeThree() {
		String productCode = "A005";
		int availableQuantity = -10;
		Mockito.when(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity)).thenReturn(0);
	
		assertFalse(productInventoryService.updateInventoryItemQuantityByProductCode(productCode, availableQuantity));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeFour() {
		String productCode = "A006";
		int availableQuantity = -10;
		Mockito.when(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity)).thenReturn(0);
	
		assertFalse(productInventoryService.updateInventoryItemQuantityByProductCode(productCode, availableQuantity));
	}
}