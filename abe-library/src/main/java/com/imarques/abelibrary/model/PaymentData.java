package com.imarques.abelibrary.model;

public class PaymentData {
	private CreditCard creditCard;
	private int installments;
	private Person recipient;
	private DeliveryAddress deliveryAddress;
	
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public int getInstallments() {
		return installments;
	}
	public void setInstallments(int installments) {
		this.installments = installments;
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
}
