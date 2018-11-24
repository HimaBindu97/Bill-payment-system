package com.cts.billpay.admin.service;

import java.util.List;
import java.util.Optional;

import com.cts.billpay.admin.entities.Admin;

public interface AdminService {
	
	
	
	public  Optional<Admin> findById(String adminId);	

}
