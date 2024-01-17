package com.springsecurity.dto;

public class BlanceDetailsDto {

	private Long blance;
	private String credit;
	private String debit;

	
	public BlanceDetailsDto() {
	}

	public BlanceDetailsDto(Long blance, String credit, String debit) {
		this.blance = blance;
		this.credit = credit;
		this.debit = debit;
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

	public BlanceDetailsDto(Long blance) {
		this.blance = blance;
	}

	public Long getBlance() {
		return blance;
	}

	public void setBlance(Long blance) {
		this.blance = blance;
	}

	@Override
	public String toString() {
		return "BlanceDetailsDto [blance=" + blance + "]";
	}
	
}
