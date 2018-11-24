package com.cts.billpay.vendor.service;

import java.util.List;
import java.util.Optional;

import com.cts.billpay.vendor.entities.VendorType1;

public interface VendorType1Service {
	
	
	
	public List<VendorType1> findAll();
	public  Optional<VendorType1> findById(String vendorType);
	
	public VendorType1 save(VendorType1 vendorType);
	
	

}
