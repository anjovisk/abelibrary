package com.imarques.abelibrary.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class OrderDelivery {
	public OrderDelivery(Long id, Person recipient, DeliveryAddress address) {
		this.id = id;
		this.recipient = recipient;
		this.deliveryAddress = address;
		this.deliveryStatus = DeliveryStatus.AWAITING;
	}
	
	@ApiModel
	public enum DeliveryStatus {
		@ApiModelProperty(notes = "Aguardando preparo")
		AWAITING,
		@ApiModelProperty(notes = "Preparando para envio")
		PREPARING, 
		@ApiModelProperty(notes = "Em transporte")
		ON_ROUTE, 
		@ApiModelProperty(notes = "Entregue")
		DELIVERED, 
		@ApiModelProperty(notes = "Não foi possível realizar a entrega")
		FAIL
	}
	
	private Long id;
	private Person recipient;
	private DeliveryAddress deliveryAddress;
	@ApiModelProperty(example = "PREPARING")
	private DeliveryStatus deliveryStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(notes = "Data da entrega yyyy/MM/dd HH:mm:ss", example = "2019/09/21 17:08:54")
	private LocalDateTime deliveryDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Person getRecipient() {
		return recipient;
	}
	public void setRecipient(Person recipient) {
		this.recipient = recipient;
	}
	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
