package com.cts.billpay.billdetails.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill_details")
public class BillDetails {

	public BillDetails() {
		// TODO Auto-generated constructor stub

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long billId;
	@Column
	private String customerId;
	@Column
	private String vendorType;
	@Column
	private String vendorName;
    @Column
	private String amountPaid;
    
   


	public Long getBillId() {
		return billId;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	
	
}
