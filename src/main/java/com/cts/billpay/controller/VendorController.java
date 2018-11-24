package com.cts.billpay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.billpay.vendor.entities.Vendor;
import com.cts.billpay.vendor.entities.VendorReg;
import com.cts.billpay.vendor.entities.VendorType1;
import com.cts.billpay.vendor.entities.VendorUpdate;
import com.cts.billpay.vendor.service.VendorService;
import com.cts.billpay.vendor.service.VendorType1Service;

@RestController
@CrossOrigin
@RequestMapping(value = "/Vendors")
public class VendorController {

	@Autowired
	private VendorService vs;
	
	@Autowired
	private VendorType1Service vts;
	List<Vendor> Vendors = null;
	Optional<Vendor> Vendor=null;
	
	
	
	

	@GetMapping(value = "/list/")
	public ResponseEntity<?> listAll() {
		Vendors = vs.findAll();
		if (Vendors.isEmpty()) {
			return new ResponseEntity<String>("No Records available in DB", HttpStatus.OK);
		}

		return new ResponseEntity<List<Vendor>>(Vendors, HttpStatus.OK);
	}

	@GetMapping("/{VendorId}")
	public ResponseEntity<?> findById(@PathVariable("VendorId") Long VendorId) throws Exception {
		
		
		
		Vendor=vs.findById( VendorId);
		
		if(!Vendor.isPresent()) {
			//System.out.println("---- null");
			return new ResponseEntity<String>("Vendor id "+VendorId+"  not found",HttpStatus.OK);
		}
		
		return new ResponseEntity<Optional<Vendor>> (Vendor, HttpStatus.OK);
	}

	@PostMapping(value = "/register/")
	public ResponseEntity<?> saveVendor(@RequestBody VendorReg vr) {
		
		Vendor vendor = new Vendor();
		
		VendorType1 vendorType=vts.findById(vr.getVendorType()).get();
		
		vendor.setVendorName(vr.getVendorName());
		vendor.setVendorCompanyRegistrationNo(vr.getVendorCompanyRegistrationNo());
		vendor.setVendorAddress(vr.getVendorAddress());
		vendor.setVendorCountry(vr.getVendorCountry());
		vendor.setVendorState(vr.getVendorState());
		vendor.setVendorEmail(vr.getVendorEmail());
		vendor.setVendorContact(vr.getVendorContact());
		vendor.setVendorWebsite(vr.getVendorWebsite());
		vendor.setVendorCertificateIssuedDate(vr.getVendorCertificateIssuedDate());
		vendor.setVendorCertificateValidityDate(vr.getVendorCertificateValidityDate());
		vendor.setVendorEmployeesCount(vr.getVendorEmployeesCount());
		vendor.setVendorCustomerCount(vr.getVendorCustomerCount());
		vendor.setVendorYearOfEstablishment(vr.getVendorYearOfEstablishment());
		
		vendor.setVendorType(vendorType);
		
		List<Vendor> list=vs.findAll();
		
		if(!list.isEmpty()){
			vendor.setVendorId(list.get(list.size()-1).getVendorId()+1);
		}
		else vendor.setVendorId(1);
		
		Vendor savedVendor=vs.save(vendor);
		
		if(savedVendor==null) {
			return new ResponseEntity<String>("Vendor not saved", HttpStatus.EXPECTATION_FAILED);

		}
		return new ResponseEntity<Vendor>(savedVendor, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptin(Exception e) {
		return new ResponseEntity<String>("Provided URL not valid. make sure its should be /Vendors/ "+e,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/error")
	public ResponseEntity<String> handleExceptin1() {
		return new ResponseEntity<String>("Provided URL not valid. make sure its should be /Vendors/ ",HttpStatus.NOT_FOUND);
	}
	
	
	
	@GetMapping("/{vType}/names")
	public ResponseEntity<?> getCustomerVendors(@PathVariable("vType") String vType) throws Exception {

		List<Vendor> list = vs.getVendorNames(vType);	
		List<String> names=new ArrayList<String>();
		for(int i=0;i<list.size();i++)
		{
			names.add(list.get(i).getVendorName());
		}
		
		if (!list.isEmpty()) {
			return new ResponseEntity<List<String>>(names, HttpStatus.OK);
		}

		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	

	@GetMapping(value = "/update/")
	public ResponseEntity<?> updateVendor(@RequestBody VendorUpdate vu) {
	
		Vendor up= (vs.findById(vu.getVendorId())).get();
		
		up.setVendorName(vu.getVendorName());
		up.setVendorCompanyRegistrationNo(vu.getVendorCompanyRegistrationNo());
		up.setVendorAddress(vu.getVendorAddress());
		up.setVendorCountry(vu.getVendorCountry());
		up.setVendorState(vu.getVendorState());
		up.setVendorEmail(vu.getVendorEmail());
		up.setVendorContact(vu.getVendorContact());
		up.setVendorWebsite(vu.getVendorWebsite());
		up.setVendorCertificateIssuedDate(vu.getVendorCertificateIssuedDate());
		up.setVendorCertificateValidityDate(vu.getVendorCertificateValidityDate());
		up.setVendorEmployeesCount(vu.getVendorEmployeesCount());
		up.setVendorCustomerCount(vu.getVendorCustomerCount());
		up.setVendorYearOfEstablishment(vu.getVendorYearOfEstablishment());
		VendorType1 vendorType2=vts.findById(vu.getVendorType()).get();
		up.setVendorType(vendorType2);
		Vendor up1=vs.save(up);
		if ( up1==null) {
			return new ResponseEntity<String>("No Records available in DB", HttpStatus.OK);
		}

		return new ResponseEntity<Vendor>(up1, HttpStatus.OK);
	}

}
