	package com.imarques.abelibrary.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Basket {
	@ApiModel
	public enum BasketStatus {
		@ApiModelProperty(notes = "Pagamento pendente")
		PENDING, 
		@ApiModelProperty(notes = "Pagamento realizado com sucesso")
		PAYED
	}
	
	private Long id;
	private String username;
	@ApiModelProperty(example = "PAYED")
	private BasketStatus status;
	private List<BasketItem> items = new ArrayList<>();
	
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
	public BasketStatus getStatus() {
		return status;
	}
	public void setStatus(BasketStatus status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
