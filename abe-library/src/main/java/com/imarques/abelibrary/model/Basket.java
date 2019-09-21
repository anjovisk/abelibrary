	package com.imarques.abelibrary.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Basket {
	@ApiModel
	public enum BasketStatus {
		@ApiModelProperty
		pending, 
		@ApiModelProperty
		payed
	}
	
	private Long id;
	private Long userId;
	@ApiModelProperty(dataType = "com.imarques.abelibrary.model.Basket.BasketStatus", example = "pending, payed")
	private BasketStatus status;
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
	public BasketStatus getStatus() {
		return status;
	}
	public void setStatus(BasketStatus status) {
		this.status = status;
	}
}