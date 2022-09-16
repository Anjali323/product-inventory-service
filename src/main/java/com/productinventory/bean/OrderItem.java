package com.productinventory.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderItem {
	@Id
    private long id;
    private String productCode;
    private int availableQuantity;
}