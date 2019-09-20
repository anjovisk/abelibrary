package com.imarques.abelibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.imarques.abelibrary.model.User;
import com.imarques.abelibrary.util.DataContainer;

@Service
public class UserService {
	private static List<User> users;
	private static int usersCount = 10;
	
	@PostConstruct
	public void populateUsers() {
		users = new ArrayList<User>();
		for (int i = 0; i < usersCount; i++) {
			User user = new User();
			user.setId((long)i);
			user.setName(String.format("User name %s", i));
			user.setEmail(String.format("email%s@gmail.com", i));
			user.setDocumentNumber(String.format("000000000%s", i));
			users.add(user);
		}
	}
	
	public DataContainer<User> find(User searchParameters, int limit, int offset) {
		List<User> usersTemp = new ArrayList<>();
		users.forEach(book -> {
			boolean idOk = searchParameters.getId() == null || book.getId().equals(searchParameters.getId());
			boolean nameOk = searchParameters.getName() == null || book.getName().toUpperCase().contains(searchParameters.getName().toUpperCase());
			boolean emailOk = searchParameters.getEmail() == null || book.getEmail().toUpperCase().contains(searchParameters.getEmail().toUpperCase());
			boolean documentNumberOk = searchParameters.getDocumentNumber() == null || book.getDocumentNumber().toUpperCase().contains(searchParameters.getDocumentNumber().toUpperCase());
			if (idOk && nameOk && emailOk && documentNumberOk) {
				usersTemp.add(book);
			}
		});
		DataContainer<User> result = new DataContainer<User>(limit, offset, users.size(), usersTemp.subList(offset, (offset+limit <= usersTemp.size() ? offset+limit : usersTemp.size())));
		return result;
	}
	
	public User getUser(Long id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
}
