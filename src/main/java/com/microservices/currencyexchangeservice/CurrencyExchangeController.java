package com.microservices.currencyexchangeservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	public Environment environment;

	@Autowired
	public ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		// ExchangeValue exchangeValue = new ExchangeValue(1000L, from,
		// to,BigDecimal.valueOf(72));
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return exchangeValue;
	}

	@GetMapping("/currency-exchange/to/{to}")
	public List<ExchangeValue> retrieveCurrencyAllValue(@PathVariable String to) {

		List<ExchangeValue> exchangeValue = repository.retrieveCurrencyAllValue(to);
		exchangeValue.get(0).setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return exchangeValue;
	}
}
