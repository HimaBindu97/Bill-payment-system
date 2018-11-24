package com.cts.billpay.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.billpay.customer.dao.CustomerDao;
import com.cts.billpay.customer.entities.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao dao;
	

	
	@Override
	public List<Customer> findAll() {
		
		// TODO Auto-generated method stub
		return  dao.findAll();
	}

	@Override
	public Optional<Customer> findById(Long customerId) {
		// TODO Auto-generated method stub
		
		return dao.findById(customerId);

	}


	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("saved customer");
		return dao.save(customer);
		
	}

}
