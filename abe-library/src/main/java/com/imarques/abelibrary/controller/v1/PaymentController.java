package com.imarques.abelibrary.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imarques.abelibrary.model.Payment;
import com.imarques.abelibrary.model.PaymentData;
import com.imarques.abelibrary.model.PaymentSummary;
import com.imarques.abelibrary.service.PaymentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("PaymentControllerV1")
@RequestMapping("/v1/public/baskets/{userId}/payment")
@Api(tags = {"Payment"})
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@ApiOperation(value = "Obtém o resumo do carrinho de compras")
	@RequestMapping(method=RequestMethod.GET)
	public PaymentSummary getBasket(
			@ApiParam(required = true, value = "Código do usuário") @PathVariable("userId") Long userId) {
		PaymentSummary result = paymentService.calculate(userId);
		return result;
	}
	
	@ApiOperation(value = "Realiza o pagamento")
	@RequestMapping(method=RequestMethod.PUT)
	public Payment pay(
			@ApiParam(required = true, value = "Código do usuário") @PathVariable("userId") Long userId,
			@ApiParam(required = true, value = "Dados para pagamento") @RequestBody PaymentData paymentData) {
		Payment result = paymentService.pay(userId, paymentData);
		return result;
	}
}
