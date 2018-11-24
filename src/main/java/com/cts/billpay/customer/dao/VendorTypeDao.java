package com.cts.billpay.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.billpay.customer.entities.VendorType;

public interface VendorTypeDao extends JpaRepository<VendorType, String> {
	

}
