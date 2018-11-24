package com.cts.billpay.customer.entities;

import java.util.Date;

public class CustomerUpdate {
	
	private long customerId;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	private String customerName;

	private String customerAddress;

	private String customerContactNumber;

	private String customerMailId;

	private String customerCountry;

	private String customerState;

	private String customerIdentificationdocument;

	private String customerDocumentDetailNumber;

	private Date customerRegistrationDate;

	private String customerCardNumber;

	private String customerBalance;
	
	private String[] vendorType;

	public String[] getVendorType() {
		return vendorType;
	}

	public void setVendorType(String[] vendorType) {
		this.vendorType = vendorType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	public String getCustomerMailId() {
		return customerMailId;
	}

	public void setCustomerMailId(String customerMailId) {
		this.customerMailId = customerMailId;
	}

	public String getCustomerCountry() {
		return customerCountry;
	}

	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerIdentificationdocument() {
		return customerIdentificationdocument;
	}

	public void setCustomerIdentificationdocument(String customerIdentificationdocument) {
		this.customerIdentificationdocument = customerIdentificationdocument;
	}

	public String getCustomerDocumentDetailNumber() {
		return customerDocumentDetailNumber;
	}

	public void setCustomerDocumentDetailNumber(String customerDocumentDetailNumber) {
		this.customerDocumentDetailNumber = customerDocumentDetailNumber;
	}

	public Date getCustomerRegistrationDate() {
		return customerRegistrationDate;
	}

	public void setCustomerRegistrationDate(Date customerRegistrationDate) {
		this.customerRegistrationDate = customerRegistrationDate;
	}

	
	public String getCustomerCardNumber() {
		return customerCardNumber;
	}

	public void setCustomerCardNumber(String customerCardNumber) {
		this.customerCardNumber = customerCardNumber;
	}

	public String getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(String customerBalance) {
		this.customerBalance = customerBalance;
	}

}
