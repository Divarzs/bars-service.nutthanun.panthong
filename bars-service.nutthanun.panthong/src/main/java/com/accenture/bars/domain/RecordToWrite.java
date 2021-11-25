package com.accenture.bars.domain;

import java.time.LocalDate;
import java.util.List;

public class RecordToWrite {

	private List<RecordToWrite> record;
	private Integer billingCycle;
	private LocalDate startDate;
	private LocalDate endDate;
	private String accountName;
	private String firstName;
	private String lastName;
	private Double amount;

	public RecordToWrite(List<RecordToWrite> record, Integer billingCycle, LocalDate startDate, LocalDate endDate,
			String accountName, String firstName, String lastName, Double amount) {
		super();
		this.record = record;
		this.billingCycle = billingCycle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.accountName = accountName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.amount = amount;
	}
	public List<RecordToWrite> getRecord() {
		return record;
	}
	public Integer getBillinCycle() {
		return billingCycle;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public String getAccountName() {
		return accountName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setRecord(List<RecordToWrite> record) {
		this.record = record;
	}
	public void setBillingCycle(Integer billingCycle) {
		this.billingCycle = billingCycle;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
