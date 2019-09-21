package com.imarques.abelibrary.model;

public class BasketItem {
	private Long isbn;
	private int total = 0;
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
