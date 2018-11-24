package com.cts.billpay.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.billpay.customer.dao.CardDetailsDao;
import com.cts.billpay.customer.entities.CardDetails;

@Service
public class CardDetailsServiceImpl implements CardDetailsService {

	@Autowired
	private CardDetailsDao cardDetailsdao;
	

	
	@Override
	public List<CardDetails> findAll() {
		
		// TODO Auto-generated method stub
		return  cardDetailsdao.findAll();
	}

	@Override
	public Optional<CardDetails> findById(String cardNumber) {
		// TODO Auto-generated method stub
		
		return cardDetailsdao.findById(cardNumber);

	}


	@Override
	public CardDetails save(CardDetails cardDetails) {
		// TODO Auto-generated method stub
		System.out.println("saved cardDetails");
		return cardDetailsdao.save(cardDetails);
		
	}

}
