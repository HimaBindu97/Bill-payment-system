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

import com.cts.billpay.customer.entities.CardDetails;
import com.cts.billpay.customer.entities.Customer;
import com.cts.billpay.customer.entities.CustomerReg;
import com.cts.billpay.customer.entities.CustomerSaveCard;
import com.cts.billpay.customer.entities.CustomerUpdate;
import com.cts.billpay.customer.entities.VendorType;
import com.cts.billpay.customer.service.CardDetailsService;
import com.cts.billpay.customer.service.CustomerService;
import com.cts.billpay.customer.service.VendorTypeService;

@RestController
@CrossOrigin
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService cs;
	@Autowired
	private CardDetailsService cds;
	@Autowired
	private VendorTypeService vts;
	List<Customer> customers = null;
	Optional<Customer> customer = null;

	@GetMapping(value = "/list")
	public ResponseEntity<?> listAll() {
		customers = cs.findAll();
		if (customers.isEmpty()) {
			return new ResponseEntity<String>("No Records available in DB", HttpStatus.OK);
		}

		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<?> findById(@PathVariable("customerId") Long customerId) throws Exception {

		customer = cs.findById(customerId);

		if (!customer.isPresent()) {
			// System.out.println("---- null");
			return new ResponseEntity<String>("Customer id " + customerId + "  not found", HttpStatus.OK);
		}

		return new ResponseEntity<Optional<Customer>>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}/vendors")
	public ResponseEntity<?> getCustomerVendors(@PathVariable("customerId") Long customerId) throws Exception {

		customer = cs.findById(customerId);	
		
		if (!customer.isPresent()) {
			return new ResponseEntity<String>("Customer id " + customerId + "  not found", HttpStatus.OK);
		}
		
		List<VendorType> list=customer.get().getVendorType();
		
		if (list==null) {
			return new ResponseEntity<String>(" vendor types " + customerId + "  not found", HttpStatus.OK);
		}
		
	

		

		return new ResponseEntity<List<VendorType>>(list, HttpStatus.OK);
	}
	

	@PostMapping("/register")
	public ResponseEntity<?> saveCustomer(@RequestBody CustomerReg cr) {

		Customer customer = new Customer();
		
		CardDetails cardDetails = new CardDetails();

		customer.setCustomerCountry(cr.getCustomerCountry());
		customer.setCustomerContactNumber(cr.getCustomerContactNumber());
		customer.setCustomerAddress(cr.getCustomerAddress());
		customer.setCustomerBalance(cr.getCustomerBalance());
		customer.setCustomerDocumentDetailNumber(cr.getCustomerDocumentDetailNumber());
		customer.setCustomerIdentificationdocument(cr.getCustomerIdentificationdocument());
		customer.setCustomerName(cr.getCustomerName());
		customer.setCustomerMailId(cr.getCustomerMailId());
		customer.setCustomerRegistrationDate(cr.getCustomerRegistrationDate());
		customer.setCustomerState(cr.getCustomerState());

		cardDetails.setCardNumber(cr.getCustomerCardNumber());

		List<VendorType> list = new ArrayList<VendorType>();

		for (int i = 0; i < cr.getVendorType().length; i++) {
			Optional<VendorType> vtOpt=vts.findById(cr.getVendorType()[i]);
			
			if(vtOpt.isPresent()) {
				VendorType vt=vtOpt.get();
				list.add(vt);
			}
		}

		customer.setVendorType(list);

		customer.setCardDetails(cardDetails);

		List<Customer> cList = cs.findAll();

		long id;
		if (!cList.isEmpty()) {
			id = cList.get(cList.size() - 1).getCustomerId();
			id++;
		} else {
			id = 1;
		}

		customer.setCustomerId(id);

		CardDetails cd1 = null;
		Customer c1 = null;

		if (!cds.findById(cr.getCustomerCardNumber()).isPresent()) {

			c1 = cs.save(customer);
		}

		if (c1 == null) {
			return new ResponseEntity<String>("Customer not saved", HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Customer>(c1, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptin(Exception e) {
		return new ResponseEntity<String>("Provided URL not valid. make sure its should be /customers/" + e,
				HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/error")
	public ResponseEntity<String> handleExceptin1() {
		return new ResponseEntity<String>("Provided URL not valid. make sure its should be /customers/ ",
				HttpStatus.NOT_FOUND);
	}
	
	

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody CustomerUpdate cu) {

		Customer customer2 = (cs.findById(cu.getCustomerId())).get();
		
		CardDetails cardDetails = new CardDetails();
		
		customer2.setCustomerCountry(cu.getCustomerCountry());
		customer2.setCustomerContactNumber(cu.getCustomerContactNumber());
		customer2.setCustomerAddress(cu.getCustomerAddress());
		customer2.setCustomerBalance(cu.getCustomerBalance());
		customer2.setCustomerDocumentDetailNumber(cu.getCustomerDocumentDetailNumber());
		customer2.setCustomerIdentificationdocument(cu.getCustomerIdentificationdocument());
		customer2.setCustomerName(cu.getCustomerName());
		customer2.setCustomerMailId(cu.getCustomerMailId());
		customer2.setCustomerRegistrationDate(cu.getCustomerRegistrationDate());
		customer2.setCustomerState(cu.getCustomerState());

		//customer2.getCardDetails().setCardNumber(cu.getCustomerCardNumber());
		
		cardDetails.setCardNumber(cu.getCustomerCardNumber());

		List<VendorType> list = new ArrayList<VendorType>();

		for (int i = 0; i < cu.getVendorType().length; i++) {
			Optional<VendorType> vtOpt=vts.findById(cu.getVendorType()[i]);
			
			if(vtOpt.isPresent()) {
				VendorType vt=vtOpt.get();
				list.add(vt);
			}
		}

		customer2.setVendorType(list);

		customer2.setCardDetails(cardDetails);
		
		Customer c1 = null;
		
		c1 = cs.save(customer2);

		if (c1 == null) {
			return new ResponseEntity<String>("Customer not saved", HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Customer>(c1, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/saveCard")
	public ResponseEntity<?> update(@RequestBody CustomerSaveCard csc) 
	{

		Customer customer2 = (cs.findById(csc.getCustomerId())).get();
		
		
		customer2.getCardDetails().setCardNumber(csc.getCardNumber());
		customer2.getCardDetails().setCardType(csc.getCardType());
		customer2.getCardDetails().setCardValidity(csc.getCardValidity());
		customer2.getCardDetails().setCvv(csc.getCvv());
		
	
		Customer c1 = null;
		
		c1 = cs.save(customer2);

		if (c1 == null) {
			return new ResponseEntity<String>("Card Details not saved", HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Customer>(c1, HttpStatus.OK);
	}




}
