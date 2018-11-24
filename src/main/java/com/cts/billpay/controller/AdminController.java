package com.cts.billpay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.billpay.admin.entities.Admin;
import com.cts.billpay.admin.service.AdminService;

@RestController
@CrossOrigin
@RequestMapping(value = "/admins")
public class AdminController {

	@Autowired
	private AdminService ps;
	List<Admin> admins = null;
	Optional<Admin> admin = null;


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> validate(@RequestBody Admin admin) {

		Optional<Admin> adminObject = ps.findById(admin.getAdminId());
		if (adminObject.isPresent()) {

			if (adminObject.get().getStatus()){

				if (adminObject.get().getAdminPassword().equals(admin.getAdminPassword()))
					return new ResponseEntity<String>("Authenticated", HttpStatus.OK);
				else
					return new ResponseEntity<String>("Wrong password", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Account not active", HttpStatus.OK);
		}
		else return new ResponseEntity<String>("Not registered admin", HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptin(Exception e) {
		return new ResponseEntity<String>("Provided URL not valid. make sure its should be /admins/ " + e,
				HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/error")
	public ResponseEntity<String> handleExceptin1() {
		return new ResponseEntity<String>("Provided URL not valid. make sure its should be /admins/ ",
				HttpStatus.NOT_FOUND);
	}

}
