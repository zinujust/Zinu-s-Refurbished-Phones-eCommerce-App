package com.mycompany.app.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAddress {
	
	private Integer id;
	private String user_Id;
	private String address_Line_1;
	private String address_Line_2;
	private String City;
	private String postal_Code;
	private String Country;
	private String phoneNum;
	
}
