package com.cts.billpay.vendor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendor_type")
public class VendorType1 {

	public VendorType1() {
		// TODO Auto-generated constructor stub
	}
	@Id
	private String vendorType;
	@Column
	private String amount;

	public String getVendorType() {
		return vendorType;
	
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	
}
