package com.cts.billpay.customer.service;

import java.util.List;
import java.util.Optional;

import com.cts.billpay.customer.entities.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public Customer save(Customer customer);

	public Optional<Customer> findById(Long customerId);

}
