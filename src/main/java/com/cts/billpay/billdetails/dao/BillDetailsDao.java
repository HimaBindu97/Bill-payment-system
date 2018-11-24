package com.cts.billpay.billdetails.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.billpay.billdetails.entity.BillDetails;;
@Repository
public interface BillDetailsDao extends JpaRepository<BillDetails, Long> {

	

	
}
