package com.imarques.abelibrary.controller.v1;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imarques.abelibrary.model.OrderDelivery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("DeliveryControllerV1")
@RequestMapping("/v1/public/orders/{orderId}/delivery")
@Api(tags = {"Orders"})
public class DeliveryController {
	
	@ApiOperation(value = "Obtém as informações referentes à entrega do pedido")
	@RequestMapping(method=RequestMethod.GET)
	public OrderDelivery getOrder(
			@ApiParam(required = true, value = "Código do pedido") @PathVariable("orderId") Long orderId) {
		//TODO - implementar acompanhamento da entrega
		return null;
	}
}
