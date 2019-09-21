package com.imarques.abelibrary.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class Payment {
	@ApiModel
	public enum PaymentStatus {
		@ApiModelProperty(notes = "Pagamento realizado com sucesso")
		SUCCESS, 
		@ApiModelProperty(notes = "Falha no pagamento")
		ERROR
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(notes = "Data do pagamento no formato yyyy/MM/dd HH:mm:ss", example = "2019/09/21 17:08:54")
	private LocalDateTime paymentDate;
	private PaymentData paymentData;
	private BigDecimal value;
	private Long basketId;
	@ApiModelProperty(dataType = "com.imarques.abelibrary.model.Paument.PaymentStatus", example = "SUCCESS")
	private PaymentStatus status;
	private String details;
	
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	public PaymentData getPaymentData() {
		return paymentData;
	}
	public void setPaymentData(PaymentData paymentData) {
		this.paymentData = paymentData;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Long getBasketId() {
		return basketId;
	}
	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
