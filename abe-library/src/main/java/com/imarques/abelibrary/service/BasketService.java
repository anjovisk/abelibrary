package com.imarques.abelibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.imarques.abelibrary.model.Basket;
import com.imarques.abelibrary.model.BasketItem;

@Service
public class BasketService {
	private Long basketId= Long.valueOf(1);
	
	private static List<Basket> baskets = new ArrayList<Basket>();
	
	public Basket getBasket(Long userId) {
		for (Basket basket : baskets) {
			if (basket.getUserId().equals(userId)) {
				return basket;
			}
		}
		Basket basket = new Basket();
		basket.setId(basketId++);
		basket.setUserId(userId);
		baskets.add(basket);
		return basket;
	}
	
	public BasketItem addItem(Long userId, String isbn) {
		BasketItem item = getItem(userId, isbn);
		if (item == null) {
			item = new BasketItem();
			item.setIsbn(isbn);
			Basket basket = getBasket(userId);
			basket.getItems().add(item);
		}
		item.setTotal(item.getTotal()+1);
		return item;
	}
	
	public void delete(Long userId, String isbn) {
		BasketItem item = getItem(userId, isbn);
		if (item != null) {
			item.setTotal(item.getTotal()-1);
			if (item.getTotal() <= 0) {
				Basket basket = getBasket(userId);
				basket.getItems().remove(item);
			}
		}
	}
	
	private BasketItem getItem(Long userId, String isbn) {
		Basket basket = getBasket(userId);
		for (BasketItem item : basket.getItems()) {
			if (item.getIsbn().equals(isbn)) {
				return item;
			}
		}
		return null;
	}
}
