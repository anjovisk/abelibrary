package com.imarques.abelibrary.model;

import java.math.BigDecimal;

public class PaymentSummary {
	private Basket basket;
	private BigDecimal price;
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
