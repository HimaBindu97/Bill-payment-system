package com.cts.billpay.vendor.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vendortable")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long vendorId;
	@Column
	private String vendorName;
	@Column
	private String vendorCompanyRegistrationNo;
	@Column
	private String vendorAddress;
	@Column
	private String vendorCountry;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="vendorandvendorType",inverseJoinColumns= {@JoinColumn(name="vendorType")},joinColumns= {@JoinColumn(name="vendorId")})
	private VendorType1 vendorType;


	@Column
	private String vendorState;
	public long getVendorId() {
		return vendorId;
	}

	public VendorType1 getVendorType() {
		return vendorType;
	}

	public void setVendorType(VendorType1 vendorType) {
		this.vendorType = vendorType;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	

	@Column
	private String vendorEmail;
	@Column
	private String vendorContact;
	@Column
	private String vendorWebsite;
	@Column
	private Date vendorCertificateIssuedDate;
	@Column
	private Date vendorCertificateValidityDate;
	@Column
	private int vendorEmployeesCount;
	@Column
	private int vendorCustomerCount;
	@Column
	private int vendorYearOfEstablishment;
	


	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorCompanyRegistrationNo() {
		return vendorCompanyRegistrationNo;
	}

	public void setVendorCompanyRegistrationNo(String vendorCompanyRegistrationNo) {
		this.vendorCompanyRegistrationNo = vendorCompanyRegistrationNo;
	}

	

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public String getVendorCountry() {
		return vendorCountry;
	}

	public void setVendorCountry(String vendorCountry) {
		this.vendorCountry = vendorCountry;
	}

	public String getVendorState() {
		return vendorState;
	}

	public void setVendorState(String vendorState) {
		this.vendorState = vendorState;
	}

	public String getVendorEmail() {
		return vendorEmail;
	}

	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	public String getVendorContact() {
		return vendorContact;
	}

	public void setVendorContact(String vendorContact) {
		this.vendorContact = vendorContact;
	}

	public String getVendorWebsite() {
		return vendorWebsite;
	}

	public void setVendorWebsite(String vendorWebsite) {
		this.vendorWebsite = vendorWebsite;
	}

	public Date getVendorCertificateIssuedDate() {
		return vendorCertificateIssuedDate;
	}

	public void setVendorCertificateIssuedDate(Date vendorCertificateIssuedDate) {
		this.vendorCertificateIssuedDate = vendorCertificateIssuedDate;
	}

	public Date getVendorCertificateValidityDate() {
		return vendorCertificateValidityDate;
	}

	public void setVendorCertificateValidityDate(Date vendorCertificateValidityDate) {
		this.vendorCertificateValidityDate = vendorCertificateValidityDate;
	}

	public int getVendorEmployeesCount() {
		return vendorEmployeesCount;
	}

	public void setVendorEmployeesCount(int vendorEmployeesCount) {
		this.vendorEmployeesCount = vendorEmployeesCount;
	}

	public int getVendorCustomerCount() {
		return vendorCustomerCount;
	}

	public void setVendorCustomerCount(int vendorCustomerCount) {
		this.vendorCustomerCount = vendorCustomerCount;
	}

	public int getVendorYearOfEstablishment() {
		return vendorYearOfEstablishment;
	}

	public void setVendorYearOfEstablishment(int vendorYearOfEstablishment) {
		this.vendorYearOfEstablishment = vendorYearOfEstablishment;
	}
}
