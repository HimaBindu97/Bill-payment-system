package com.cts.billpay.billdetails.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.billpay.billdetails.dao.BillDetailsDao;
import com.cts.billpay.billdetails.entity.BillDetails;

@Service
public class BillDetailsServiceImpl implements BillDetailsService {

	@Autowired
	private BillDetailsDao billDetailsdao;
	

	
	@Override
	public List<BillDetails> findAll() {
		
		// TODO Auto-generated method stub
		return  billDetailsdao.findAll();
	}

	

	@Override
	public BillDetails save(BillDetails BillDetails) {
		// TODO Auto-generated method stub
		System.out.println("saved BillDetails");
		return billDetailsdao.save(BillDetails);
		
	}



	@Override
	public Optional<BillDetails> findById(Long billId) {
		// TODO Auto-generated method stub
		return billDetailsdao.findById(billId);
	}

}
