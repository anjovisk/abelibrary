package com.imarques.abelibrary.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
	private Long id;
	private Long userId;
	private List<BasketItem> items = new ArrayList<>();
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<BasketItem> getItems() {
		return items;
	}
	public void setItems(List<BasketItem> items) {
		this.items = items;
	}
}
