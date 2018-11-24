package com.cts.billpay.customer.service;

import java.util.List;
import java.util.Optional;

import com.cts.billpay.customer.entities.VendorType;

public interface VendorTypeService {

	public List<VendorType> findAll();

	public VendorType save(VendorType vendorType);

	public Optional<VendorType> findById(String vendorTypeId);

}
