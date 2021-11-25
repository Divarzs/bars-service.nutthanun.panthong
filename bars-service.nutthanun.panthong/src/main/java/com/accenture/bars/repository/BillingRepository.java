package com.accenture.bars.repository;

import java.time.LocalDate;

import com.accenture.bars.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {
	Billing findByBillingCycleAndStartDateAndEndDate(int billingCycle,
													 LocalDate startDate, LocalDate endDate);
}
