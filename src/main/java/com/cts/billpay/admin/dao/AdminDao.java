package com.cts.billpay.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.billpay.admin.entities.Admin;;
@Repository
public interface AdminDao extends JpaRepository<Admin, String> {

	

	
}
