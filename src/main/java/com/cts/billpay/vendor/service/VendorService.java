package com.cts.billpay.vendor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.cts.billpay.vendor.entities.Vendor;

public interface VendorService {
	
	
	 
	public List<Vendor> findAll();
	public  Optional<Vendor> findById(Long vendorId);
	
	public Vendor save(Vendor vendor);
	
	public List<Vendor> getVendorNames(String vType);
	
	

}
