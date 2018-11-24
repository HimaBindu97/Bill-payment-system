package com.cts.billpay.customer.service;

import java.util.List;
import java.util.Optional;

import com.cts.billpay.customer.entities.CardDetails;

public interface CardDetailsService {

	public List<CardDetails> findAll();

	public CardDetails save(CardDetails cardDetails);

	public Optional<CardDetails> findById(String cardNumber);

}
