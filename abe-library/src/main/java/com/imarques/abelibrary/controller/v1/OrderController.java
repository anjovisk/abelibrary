package com.imarques.abelibrary.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imarques.abelibrary.model.Order;
import com.imarques.abelibrary.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("OrderControllerV1")
@RequestMapping("/v1/public/orders")
@Api(tags = {"Order"})
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value = "Obtém o resumo do carrinho de compras")
	@RequestMapping(path="/{orderId}",  method=RequestMethod.GET)
	public Order getOrder(
			@ApiParam(required = true, value = "Código do pedido") @PathVariable("orderId") Long orderId) {
		Order result = orderService.getOrder(orderId);
		return result;
	}
}
