package com.imarques.abelibrary.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imarques.abelibrary.exception.UserNotFoundException;
import com.imarques.abelibrary.model.Payment;
import com.imarques.abelibrary.model.PaymentData;
import com.imarques.abelibrary.model.PaymentSummary;
import com.imarques.abelibrary.service.PaymentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("PaymentControllerV1")
@RequestMapping("/v1/public/baskets/{username}/payment")
@Api(tags = {"Baskets"})
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@ApiOperation(value = "Obtém o resumo do carrinho de compras")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<PaymentSummary> getBasket(
			@ApiParam(required = true, value = "Nickname do usuário") @PathVariable("username") String username) {
		PaymentSummary result = null;
		try {
			result = paymentService.calculate(username);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		return ResponseEntity.accepted().body(result);
	}
	
	@ApiOperation(value = "Realiza o pagamento")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Payment> pay(
			@ApiParam(required = true, value = "Nickname do usuário") @PathVariable("username") String username,
			@ApiParam(required = true, value = "Dados para pagamento") @RequestBody PaymentData paymentData) {
		Payment result = null;
		try {
			result = paymentService.pay(username, paymentData);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		return ResponseEntity.accepted().body(result);
	}
}
