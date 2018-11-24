package com.cts.billpay.vendor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.billpay.vendor.dao.VendorTypeDao1;
import com.cts.billpay.vendor.entities.VendorType1;

@Service
public class VendorType1ServiceImpl implements VendorType1Service {

	@Autowired
	private VendorTypeDao1 vDao;

	@Override
	public List<VendorType1> findAll() {
		// TODO Auto-generated method stub
		return vDao.findAll();
	}

	@Override
	public Optional<VendorType1> findById(String vendorType) {
		// TODO Auto-generated method stub
		return vDao.findById(vendorType);
	}

	@Override
	public VendorType1 save(VendorType1 vendorType) {
		// TODO Auto-generated method stub
		return vDao.save(vendorType);
	}

	
	}

	
	



	



