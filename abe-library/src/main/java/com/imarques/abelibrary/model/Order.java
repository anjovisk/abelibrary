package com.imarques.abelibrary.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class Order {
	public Order(Long id, Person recipient, DeliveryAddress address) {
		this.id = id;
		this.orderDelivery = new OrderDelivery(id, recipient, address);
		this.date = LocalDateTime.now();
	}
	
	private Long id;
	private Long basketId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(notes = "Data em que o pedido foi gerado no formato yyyy-MM-ddTHH:mm:ssTZD", example = "2019/09/21 17:08:54")
	private LocalDateTime date;
	private List<OrderItem> items = new ArrayList<OrderItem>();
	private OrderDelivery orderDelivery;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBasketId() {
		return basketId;
	}
	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public OrderDelivery getOrderDelivery() {
		return orderDelivery;
	}
	public void setOrderDelivery(OrderDelivery orderDelivery) {
		this.orderDelivery = orderDelivery;
	}
	
}
