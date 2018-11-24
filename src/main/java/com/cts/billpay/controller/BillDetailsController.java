package com.cts.billpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.billpay.billdetails.entity.BillDetails;

@RestController
@CrossOrigin
@RequestMapping(value = "/billDetails")
public class BillDetailsController {

	@Autowired
	private com.cts.billpay.billdetails.service.BillDetailsService bs;
	
	

	@PostMapping("/savebill")
	public ResponseEntity<?> saveBillDetails(@RequestBody BillDetails bd) {

		BillDetails b1=bs.save(bd);


		if (b1== null) {
			return new ResponseEntity<String>("BillDetails not saved", HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<BillDetails>(b1, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptin(Exception e) {
		return new ResponseEntity<String>("Provided URL not valid. make sure its should be /billDetailss/" + e,
				HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/error")
	public ResponseEntity<String> handleExceptin1() {
		return new ResponseEntity<String>("Provided URL not valid. make sure its should be /billDetailss/ ",
				HttpStatus.NOT_FOUND);
	}
}
