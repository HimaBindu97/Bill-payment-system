package com.cts.billpay.billdetails.service;

import java.util.List;
import java.util.Optional;

import com.cts.billpay.billdetails.entity.BillDetails;

public interface BillDetailsService {

	public List<BillDetails> findAll();

	public BillDetails save(BillDetails billDetails);

	public Optional<BillDetails> findById(Long billId);

}
