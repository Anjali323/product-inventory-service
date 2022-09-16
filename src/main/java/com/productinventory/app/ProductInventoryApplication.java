package com.productinventory.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.productinventory")
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.productinventory.persistence")
@EntityScan(basePackages = "com.productinventory.bean")
public class ProductInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductInventoryApplication.class, args);
	}
}