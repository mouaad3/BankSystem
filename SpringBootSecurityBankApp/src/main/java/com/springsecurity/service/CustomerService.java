 package com.springsecurity.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springsecurity.dto.CustomerRegistrationDto;
import com.springsecurity.model.Customer;

public interface CustomerService extends UserDetailsService {

	public Customer saveCustomer(CustomerRegistrationDto customerRegistrationDto);
	public Customer UpdateCustomer(CustomerRegistrationDto customerRegistrationDto,Long id);
	public Customer getCustomer(Long id);
	public List<Customer> getAllCustomer();
	public void deleteCustomer(Customer customer);
	
	public Customer logedInCustomer();
}
