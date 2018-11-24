package com.cts.billpay.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.billpay.customer.dao.VendorTypeDao;
import com.cts.billpay.customer.entities.VendorType;

@Service
public class VendorTypeServiceImpl implements VendorTypeService {

	@Autowired
	private VendorTypeDao vendorTypedao;
	

	
	@Override
	public List<VendorType> findAll() {
		
		// TODO Auto-generated method stub
		return  vendorTypedao.findAll();
	}

	@Override
	public Optional<VendorType> findById(String vendorTypeId) {
		// TODO Auto-generated method stub
		
		return vendorTypedao.findById(vendorTypeId);

	}


	@Override
	public VendorType save(VendorType vendorType) {
		// TODO Auto-generated method stub
		System.out.println("saved vendorType");
		return vendorTypedao.save(vendorType);
		
	}

}
