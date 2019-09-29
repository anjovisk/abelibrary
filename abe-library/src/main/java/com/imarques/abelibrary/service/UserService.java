package com.imarques.abelibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.imarques.abelibrary.exception.UserNotFoundException;
import com.imarques.abelibrary.model.User;

@Service
public class UserService {
	@Autowired
	private Environment env;
	
	public User getUser(String username) {
		RestTemplate restTemplate = new RestTemplate();
		String authServerUrl = env.getProperty("auth.server.url");
		User result = restTemplate.getForObject(String.format("%s/%s", authServerUrl, username), User.class);
		if (result == null) {
			throw new UserNotFoundException("Usuário não encontrado");
		}
		return result;
	}
}
