package com.imarques.abelibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imarques.abelibrary.model.Basket;
import com.imarques.abelibrary.model.Basket.BasketStatus;
import com.imarques.abelibrary.model.BasketItem;
import com.imarques.abelibrary.model.User;

@Service
public class BasketService {
	@Autowired
	private UserService userService;
	
	private Long basketId= Long.valueOf(1);
	
	private static List<Basket> baskets = new ArrayList<Basket>();
	
	public Basket getPendingBasket(String username) {
		return getBasket(username, BasketStatus.PENDING);
	}
	
	private Basket getBasket(String username, BasketStatus status) {
		User user = userService.getUser(username);
		Basket result = null;
		for (Basket basket : baskets) {
			if (basket.getUsername().equals(user.getId()) &&
					basket.getStatus().equals(status)) {
				result = basket;
				break;
			}
		}
		if (result == null) {
			result = new Basket();
			result.setId(basketId++);
			result.setUsername(user.getId());
			result.setStatus(BasketStatus.PENDING);
			baskets.add(result);
		}
		return result;
	}
	
	public Basket addItem(String username, Long isbn) {
		Basket basket = getPendingBasket(username);
		BasketItem item = getItemFromPendingBasket(basket, isbn);
		if (item == null) {
			item = new BasketItem();
			item.setIsbn(isbn);
			basket.getItems().add(item);
		}
		item.setTotal(item.getTotal()+1);
		return basket;
	}
	
	public void delete(String username, Long isbn) {
		Basket basket = getPendingBasket(username);
		BasketItem item = getItemFromPendingBasket(basket, isbn);
		if (item != null) {
			item.setTotal(item.getTotal()-1);
			if (item.getTotal() <= 0) {
				basket.getItems().remove(item);
			}
		}
	}
	
	private BasketItem getItemFromPendingBasket(Basket basket, Long isbn) {
		for (BasketItem item : basket.getItems()) {
			if (item.getIsbn().equals(isbn)) {
				return item;
			}
		}
		return null;
	}
}
