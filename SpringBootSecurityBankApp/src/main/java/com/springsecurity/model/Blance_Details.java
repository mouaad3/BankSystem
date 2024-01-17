package com.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Blance_Details {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long blance;
	private String credit;
	private String debit;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "blance_details_id")
	private Customer customer;
	

	public Blance_Details(Long blance, Customer customer) {
		this.blance = blance;
		this.customer = customer;
	}

	public Blance_Details(Long id, Long blance, Customer customer) {
		this.id = id;
		this.blance = blance;
		this.customer = customer;
	}

	public Blance_Details() {
	}

	public Blance_Details(Long blance, String credit, String debit, Customer customer) {
		this.blance = blance;
		this.credit = credit;
		this.debit = debit;
		this.customer = customer;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBlance() {
		return blance;
	}

	public void setBlance(Long blance) {
		this.blance = blance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Blance_Details [id=" + id + ", blance=" + blance + ", customer=" + customer + "]";
	}
	
}
