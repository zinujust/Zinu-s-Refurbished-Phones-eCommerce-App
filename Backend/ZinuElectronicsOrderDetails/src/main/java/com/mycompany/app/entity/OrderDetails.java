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
public class OrderDetails {

	public Integer id;
	public String userId;
	public double total;
	public Integer paymentId;
}
