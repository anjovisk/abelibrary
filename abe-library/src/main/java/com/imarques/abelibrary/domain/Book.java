package com.imarques.abelibrary.domain;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private Long isbn;
	private String name;
	private String description;
	private List<Comment> comments = new ArrayList<>();
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
