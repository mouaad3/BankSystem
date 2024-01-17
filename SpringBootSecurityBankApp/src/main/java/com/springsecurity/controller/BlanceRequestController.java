package com.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springsecurity.dto.BlanceDetailsDto;
import com.springsecurity.model.Blance_Details;
import com.springsecurity.model.Customer;
import com.springsecurity.service.BlanceDetailsService;
import com.springsecurity.service.CustomerService;
import com.springsecurity.service.SecurityService;

@Controller
@RequestMapping("/api/v1/blance")
public class BlanceRequestController implements ErrorController {
	private BlanceDetailsService blanceDetailsService;
	@Autowired
	private CustomerService  customerService;
	@Autowired
	public BlanceRequestController(BlanceDetailsService blanceDetailsService) {
		this.blanceDetailsService = blanceDetailsService;
	}
	
	
	@ModelAttribute("blance")
	public BlanceDetailsDto getBlanceDto() {
		return new BlanceDetailsDto();
	}
	
	@GetMapping
	public String showForm() {
		return "credit_form";
	}
	
	@GetMapping("list")
	public String blanceList(Model model) {
		Customer logedInCustomer = customerService.logedInCustomer();
		List<Blance_Details> blanceDetails = blanceDetailsService.getBlanceDetails(logedInCustomer);
		model.addAttribute("blanceDetails", blanceDetails);
		return "blanceList";
	}
	
	@PostMapping
	public String saveBlance(@ModelAttribute("blance") BlanceDetailsDto blanceDetailsDto,Model model) {
		Customer logedInCustomer = customerService.logedInCustomer();
		blanceDetailsService.saveBlance(blanceDetailsDto,logedInCustomer);
		return "redirect:/api/v1/bank/customer";
	}


	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
