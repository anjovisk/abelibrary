package com.imarques.abelibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.imarques.abelibrary.model.PaymentAuthorization;
import com.imarques.abelibrary.model.Transaction;

@Service
public class CreditCardAuthorizationService {
	@Autowired
	private Environment env;
	
	public PaymentAuthorization authorize(Transaction transaction) {
		RestTemplate restTemplate = new RestTemplate();
		String authServerUrl = env.getProperty("creditcard.server.url");
		ResponseEntity<PaymentAuthorization> result = restTemplate.postForEntity(authServerUrl, transaction, PaymentAuthorization.class);
		return result.getBody();
	}
}
