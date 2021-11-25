package com.accenture.bars.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private int accountId;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "date_created")
	private LocalDateTime dateCreated;

	@Column(name = "is_active")
	private String isActive;

	@Column(name = "last_edited")
	private String lastEdited;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customerId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Set<Billing> billing;



	public int getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public String getIsActive() {
		return isActive;
	}

	public String getLastEdited() {
		return lastEdited;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public Set<Billing> getBilling() {
		return billing;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public void setBilling(Set<Billing> billing) {
		this.billing = billing;
	}

	public Account() {
	}

	public Account(int accountId, String accountName, LocalDateTime dateCreated, String isActive, String lastEdited,
				   Customer customerId, Set<Billing> billing) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.dateCreated = dateCreated;
		this.isActive = isActive;
		this.lastEdited = lastEdited;
		this.customerId = customerId;
		this.billing = billing;
	}

}
