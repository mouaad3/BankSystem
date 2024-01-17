package com.springsecurity.service;

import java.util.List;

import com.springsecurity.dto.BlanceDetailsDto;
import com.springsecurity.model.Blance_Details;
import com.springsecurity.model.Customer;

public interface BlanceDetailsService {
	public Blance_Details saveBlance(BlanceDetailsDto blanceDetailsDto,Customer customer);
	public List<Blance_Details> getBlanceDetails(Customer customer);
}
