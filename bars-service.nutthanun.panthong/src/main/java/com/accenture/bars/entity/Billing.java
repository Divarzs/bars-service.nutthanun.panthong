package com.accenture.bars.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "billing")
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "billing_id")
	private int billingId;

	@Column(name = "billing_cycle")
	private int billingCycle;

	@Column(name = "billing_month")
	private String billingMonth;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "last_edited")
	private String lastEdited;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account accountId;

	public Billing() {
	}

	public Billing(int billingId, int billingCycle, String billingMonth, Double amount, LocalDate startDate,
				   LocalDate endDate, String lastEdited, Account accountId) {
		super();
		this.billingId = billingId;
		this.billingCycle = billingCycle;
		this.billingMonth = billingMonth;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastEdited = lastEdited;
		this.accountId = accountId;
	}
	
	public int getBillingId() {
		return billingId;
	}
	
	public int getBillingCycle() {
		return billingCycle;
	}
	
	public String getBillingMonth() {
		return billingMonth;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public String getLastEdited() {
		return lastEdited;
	}
	
	public Account getAccountId() {
		return accountId;
	}
	
	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}
	
	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}
	
	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}
	
	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}
}
