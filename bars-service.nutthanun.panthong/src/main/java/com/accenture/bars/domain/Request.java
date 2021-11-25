package com.accenture.bars.domain;

import java.time.LocalDate;

public class Request {

	private int billingCycle;
	private LocalDate startDate;
	private LocalDate endDate;

	public Request(int billingCycle, LocalDate startDate, LocalDate endDate) {
		super();
		this.billingCycle = billingCycle;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getBillingCycle() {
		return billingCycle;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
