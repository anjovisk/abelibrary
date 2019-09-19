package com.imarques.abelibrary.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
	private Long id;
	private List<OrderItem> items;
	private BigDecimal totalCost;
	private OrderDelivery orderDelivery;
	
	public OrderDelivery getOrderDelivery() {
		return orderDelivery;
	}
	public void setOrderDelivery(OrderDelivery orderDelivery) {
		this.orderDelivery = orderDelivery;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public BigDecimal getFreightCost() {
		return freightCost;
	}
	public void setFreightCost(BigDecimal freightCost) {
		this.freightCost = freightCost;
	}
	private BigDecimal freightCost;
	
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
