package com.mycompany.app.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

	private Integer id;
	private String name;
	private String description;
	private String sku;
	private double price;
	private Integer discountPercent;
	private Integer modelId;
	private Integer brandId;
	private Integer inventoryId;
}
