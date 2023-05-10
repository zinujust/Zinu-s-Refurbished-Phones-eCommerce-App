package com.mycompany.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.app.entity.UserAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer>{

}
