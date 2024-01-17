package com.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springsecurity.dto.CustomerRegistrationDto;
import com.springsecurity.model.Customer;
import com.springsecurity.service.CustomerService;

@Controller
@RequestMapping("/api/v1/bank")
public class RequestController {

	private CustomerService customerServise;

	@Autowired
	public RequestController(CustomerService customerServise) {
		this.customerServise = customerServise;
	}

	@ModelAttribute("customer")
	public CustomerRegistrationDto getDto() {
		return new CustomerRegistrationDto();
	}
	
	@GetMapping("/customer")
	public String home(Model model) {
		Customer customer=customerServise.logedInCustomer();
		model.addAttribute("customer", customer);
		return "index";
	}
	
	@GetMapping("/customers")
	public String getAllCustomer(Model model) {
      List<Customer> customers = customerServise.getAllCustomer();
      model.addAttribute("customers", customers);
      return "list_customer";
	}

	@GetMapping("/customer/edit/{id}")
	public String getCustomer(@PathVariable("id") Long id,Model model) {
		Customer customer = customerServise.getCustomer(id);
		model.addAttribute("customer", customer);
		return "update_customer";
	}
	
	@PostMapping("/update/{id}")
	public String updateCustomer(@PathVariable("id") Long id, @ModelAttribute("customer") CustomerRegistrationDto customerRegistrationDto,BindingResult result,Model model) {
		if (result.hasErrors()) {
			 return "update_customer";
			 }
		customerServise.UpdateCustomer(customerRegistrationDto,id);
		return "redirect:/api/v1/bank/customers";
	}
	
	@GetMapping("/customer/delete/{id}")
	public String deleteCustomer(@PathVariable("id") Long id,Model model) {
		Customer customer = customerServise.getCustomer(id);
		customerServise.deleteCustomer(customer);
		return "redirect:/api/v1/bank/customers";
	}
}
