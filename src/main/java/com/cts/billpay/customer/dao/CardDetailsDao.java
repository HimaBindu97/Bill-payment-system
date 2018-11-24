package com.cts.billpay.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.billpay.customer.entities.CardDetails;

public interface CardDetailsDao extends JpaRepository<CardDetails, String> {
	

}
