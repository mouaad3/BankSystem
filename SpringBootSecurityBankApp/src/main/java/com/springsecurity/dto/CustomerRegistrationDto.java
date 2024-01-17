package com.springsecurity.dto;

/*Query query = session.createQuery("from lahetys order by lahetysNro DESC");
query.setMaxResults(1);
Lahetys last = (Lahetys) query.uniqueResult();*/

import com.springsecurity.password.PasswordConfig;

public class CustomerRegistrationDto {
	
		private String name;
		private String email;
		private Long number;
		private String password;


		private PasswordConfig passwordConfig;
		
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
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "CustomerRegistrationDto [name=" + name + ", email=" + email + ", number=" + number + ", password="
					+ password + "]";
		}

}
