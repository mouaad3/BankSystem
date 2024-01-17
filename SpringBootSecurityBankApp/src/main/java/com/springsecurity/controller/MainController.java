package com.springsecurity.controller;

import com.springsecurity.dto.LoginRequest;
import com.springsecurity.dto.LoginResponse;
import com.springsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.springsecurity.dto.CustomerRegistrationDto;
import com.springsecurity.service.CustomerService;

@Controller
@RequestMapping("/")
public class MainController {

	private CustomerService customerService;
	private final UserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	@Autowired
	public MainController(CustomerService customerServise, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.customerService = customerServise;
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@GetMapping("registration")
	public String showForm() {
		return "registration";
	}

	@ModelAttribute("customer")
	public CustomerRegistrationDto getDto() {
		return new CustomerRegistrationDto();
	}

	@PostMapping("registration")
	public String saveCustomer(@ModelAttribute("customer") CustomerRegistrationDto customerRegistrationDto) {
		customerService.saveCustomer(customerRegistrationDto);
		return "redirect:login?success";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}


	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
			String jwt = jwtUtil.generateToken(userDetails);

			return ResponseEntity.ok(new LoginResponse(jwt));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
