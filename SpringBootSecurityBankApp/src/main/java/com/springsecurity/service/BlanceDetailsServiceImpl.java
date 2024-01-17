package com.springsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.dto.BlanceDetailsDto;
import com.springsecurity.model.Blance_Details;
import com.springsecurity.model.Customer;
import com.springsecurity.repository.BlanceDetailsRepository;
import com.springsecurity.repository.CustomerRepository;

@Service
public class BlanceDetailsServiceImpl implements BlanceDetailsService {

	private final BlanceDetailsRepository blanceDetailsRepository;
	private final CustomerRepository customerRepository;
	private final CustomerService customerService;

	@Autowired
	public BlanceDetailsServiceImpl(BlanceDetailsRepository blanceDetailsRepository, CustomerService customerService,
			CustomerRepository customerRepository) {
		this.blanceDetailsRepository = blanceDetailsRepository;
		this.customerService = customerService;
		this.customerRepository = customerRepository;
	}

	@Override
	public Blance_Details saveBlance(BlanceDetailsDto blanceDetailsDto, Customer customer) {
		if (customer == null) {
			return null;
		}
		List<Blance_Details> blanceDetails = getBlanceDetails(customer);
		int size = blanceDetails.size();
		System.out.println(size);
		Blance_Details blanceDetail=null;
		if (size != 0) {
			 blanceDetail = blanceDetails.get(size - 1);
		} else {
			blanceDetailsRepository.save(new Blance_Details(0l, "", "", customer));
			blanceDetail = blanceDetails.get(size-1);
		}
		if (blanceDetailsDto.getDebit().equalsIgnoreCase("debit")) {
			blanceDetailsDto.setCredit("");
			blanceDetailsDto.setDebit("debit");
			blanceDetailsDto.setBlance(-blanceDetailsDto.getBlance() + blanceDetail.getBlance());
		} else if (blanceDetailsDto.getDebit().equalsIgnoreCase("credit")) {
			blanceDetailsDto.setCredit("credit");
			blanceDetailsDto.setDebit("");
			blanceDetailsDto.setBlance(blanceDetailsDto.getBlance() + blanceDetail.getBlance());
		} else
			return null;
		return blanceDetailsRepository.save(new Blance_Details(blanceDetailsDto.getBlance(),
				blanceDetailsDto.getCredit(), blanceDetailsDto.getDebit(), customer));
	}

	@Override
	public List<Blance_Details> getBlanceDetails(Customer customer) {
		return blanceDetailsRepository.findByForainKey(customer.getId());
	}
}
