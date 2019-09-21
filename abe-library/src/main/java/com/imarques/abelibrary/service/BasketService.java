package com.imarques.abelibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.imarques.abelibrary.model.Basket;
import com.imarques.abelibrary.model.Basket.BasketStatus;
import com.imarques.abelibrary.model.BasketItem;

@Service
public class BasketService {
	private Long basketId= Long.valueOf(1);
	
	private static List<Basket> baskets = new ArrayList<Basket>();
	
	public Basket getPendingBasket(Long userId) {
		return getBasket(userId, BasketStatus.PENDING);
	}
	
	private Basket getBasket(Long userId, BasketStatus status) {
		for (Basket basket : baskets) {
			if (basket.getUserId().equals(userId) &&
					basket.getStatus().equals(status)) {
				return basket;
			}
		}
		Basket basket = new Basket();
		basket.setId(basketId++);
		basket.setUserId(userId);
		basket.setStatus(BasketStatus.PENDING);
		baskets.add(basket);
		return basket;
	}
	
	public Basket addItem(Long userId, Long isbn) {
		Basket basket = getPendingBasket(userId);
		BasketItem item = getItemFromPendingBasket(basket, isbn);
		if (item == null) {
			item = new BasketItem();
			item.setIsbn(isbn);
			basket.getItems().add(item);
		}
		item.setTotal(item.getTotal()+1);
		return basket;
	}
	
	public void delete(Long userId, Long isbn) {
		Basket basket = getPendingBasket(userId);
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
