package com.imarques.abelibrary.model;

import java.util.List;

public class Basket {
	private Long id;
	private List<BasketItem> items;
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
