package com.microservices.currencyexchangeservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	
	// creating query method
	ExchangeValue findByFromAndTo(String from, String to);

	@Query("select u from ExchangeValue u where u.to=:to")
	List<ExchangeValue> retrieveCurrencyAllValue(@Param("to") String to);
}