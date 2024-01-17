package com.springsecurity.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.auth.CustomerApplication;
import com.springsecurity.dto.CustomerRegistrationDto;
import com.springsecurity.model.Customer;
import com.springsecurity.model.Role;
import com.springsecurity.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private Customer logedInCustomer;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
//		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Customer logedInCustomer() {
		System.out.println(logedInCustomer);
		return logedInCustomer;
	}
	
	@Override
	public Customer getCustomer(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Invalid number "+id));
		return customer;
	}
	
	@Override
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
	
	@Override
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

	@Override
	public Customer saveCustomer(CustomerRegistrationDto customerRegistrationDto) {
		Customer customer = new Customer(customerRegistrationDto.getName(), customerRegistrationDto.getEmail(),
				customerRegistrationDto.getNumber(), customerRegistrationDto.getPassword(),
				Arrays.asList(new Role("ROLE_CUSTOMER")));

		// Check if the user being saved is the first user (ADMIN)
		if (customerRepository.count() == 0) {
			// Set the first user as ADMIN
			customer.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		}

		return customerRepository.save(customer);
	}


	@Override
	public Customer UpdateCustomer(CustomerRegistrationDto customerRegistrationDto,Long id) {
		 Customer customer = customerRepository.findById(id).orElseThrow(()->new IllegalArgumentException("invalid id "+id));
		if(customer!=null) {
			Role role = customer.getRoles().get(0);
			Long id1=role.getId();
			if(id1!=null) {
				Customer updateCustomer = new Customer(id,customerRegistrationDto.getName(), customerRegistrationDto.getEmail(),
						customerRegistrationDto.getNumber(), customerRegistrationDto.getPassword(),
						Arrays.asList(new Role(id1,"ROLE_CUSTOMER")));
				//update
				return customerRepository.save(updateCustomer);
			}
			else {
				deleteCustomer(customer);
			}
		}
		    //save
			return saveCustomer(customerRegistrationDto);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(username);
		if (customer == null) {
			throw new UsernameNotFoundException("invalied username or password");
		}

		logedInCustomer=customer;
		return new CustomerApplication(getAuthorities(customer), customer.getEmail(),
				passwordEncoder.encode(customer.getPassword()), true, true, true, true);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Customer customer) {
		List<Role> roles = customer.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return  authorities;
	}
}