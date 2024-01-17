package com.springsecurity.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private Long number;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "customer_role", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Blance_Details> blance_Details;

	public Customer() {
	}

	public Customer(String name, String email, Long number, String password, List<Role> roles) {
		this.name = name;
		this.email = email;
		this.number = number;
		this.password = password;
		this.roles = roles;
	}

	public Customer(Long id, String name, String email, Long number, String password, List<Role> roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.number = number;
		this.password = password;
		this.roles = roles;
	}

	public Customer( Long id,List<Blance_Details> blance_Details) {
		this.id = id;
		this.blance_Details = blance_Details;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public List<Blance_Details> getBlance_Details() {
		return blance_Details;
	}

	public void setBlance_Details(List<Blance_Details> blance_Details) {
		this.blance_Details = blance_Details;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", number=" + number + ", password="
				+ password + ", roles=" + roles + "]";
	}

}
