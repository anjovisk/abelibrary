package com.imarques.abelibrary.model;

public class OrderDelivery {
	private Long id;
	private String recipientName;
	private OrderDeliveryAddress deliveryAddress;
	private OrderDeliveryStatus deliveryStatus;
	
	public OrderDeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(OrderDeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public OrderDeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(OrderDeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
