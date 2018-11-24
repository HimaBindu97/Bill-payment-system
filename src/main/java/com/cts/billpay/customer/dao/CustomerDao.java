package com.cts.billpay.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.billpay.customer.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
	

}
