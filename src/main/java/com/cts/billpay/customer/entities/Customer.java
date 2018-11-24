package com.cts.billpay.customer.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customerTable")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customerId")
	private long customerId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cardNumber")
	private CardDetails cardDetails;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "customervendor", joinColumns = { @JoinColumn(name = "customerId") }, inverseJoinColumns = {
			@JoinColumn(name = "vendorType") })
	private List<VendorType> vendorType;


	@Column
	private String customerName;
	@Column
	private String customerAddress;
	@Column
	private String customerContactNumber;
	@Column
	private String customerCountry;
	@Column
	private String customerState;
	@Column
	private String customerMailId;
	@Column
	private String customerIdentificationdocument;
	@Column
	private String customerDocumentDetailNumber;
	@Column
	private Date customerRegistrationDate;
	@Column
	private String customerBalance;

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

	public String getCustomerMailId() {
		return customerMailId;
	}

	public void setCustomerMailId(String customerMailId) {
		this.customerMailId = customerMailId;
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

	public String getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(String customerBalance) {
		this.customerBalance = customerBalance;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public CardDetails getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}

	public List<VendorType> getVendorType() {
		return vendorType;
	}

	public void setVendorType(List<VendorType> vendorType) {
		this.vendorType = vendorType;
	}

}