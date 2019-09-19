package com.imarques.abelibrary.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Book {
	private Long isbn;
	private String name;
	private String description;
	private BigDecimal price;
	@JsonIgnore
	private List<Long> comments = new ArrayList<>();
	
	public List<Long> getComments() {
		return comments;
	}
	public void setComments(List<Long> comments) {
		this.comments = comments;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
