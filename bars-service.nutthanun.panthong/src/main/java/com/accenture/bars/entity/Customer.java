package com.accenture.bars.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
	private int customerId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "status")
	private String status;

	@Column(name = "date_created")
	private LocalDateTime dateCreated;

	@Column(name = "last_edited")
	private String lastEdited;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Set<Account> account;

	public int getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getStatus() {
		return status;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public String getLastEdited() {
		return lastEdited;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}

	public Customer() {
	}

	public Customer(int customerId, String firstName, String lastName, String address, String status,
					LocalDateTime dateCreated, String lastEdited, Set<Account> account) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.status = status;
		this.dateCreated = dateCreated;
		this.lastEdited = lastEdited;
		this.account = account;
	}

}
