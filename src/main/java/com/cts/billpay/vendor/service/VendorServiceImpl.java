package com.cts.billpay.vendor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.billpay.vendor.dao.VendorDao;
import com.cts.billpay.vendor.entities.Vendor;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorDao dao;
	


	
	@Override
	public List<Vendor> findAll() {
		
		// TODO Auto-generated method stub
		return  dao.findAll();
	}

	@Override
	public Optional<Vendor> findById(Long vendorId) {
		return dao.findById(vendorId);

	}


	@Override
	public Vendor save(Vendor vendor) {
		System.out.println("saved vendor");
		return dao.save(vendor);
	}

	@Override
	public List<Vendor> getVendorNames(String vType) {
		return dao.getVendorNames(vType);
	}

}
