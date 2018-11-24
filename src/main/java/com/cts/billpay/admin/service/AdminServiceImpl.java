package com.cts.billpay.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.billpay.admin.dao.AdminDao;
import com.cts.billpay.admin.entities.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao dao;
	

	
	
	@Override
	public Optional<Admin> findById(String adminId) {
		// TODO Auto-generated method stub
		
		return dao.findById(adminId);

	}


	


	

}
