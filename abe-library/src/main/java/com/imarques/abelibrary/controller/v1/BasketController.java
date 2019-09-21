package com.imarques.abelibrary.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imarques.abelibrary.model.Basket;
import com.imarques.abelibrary.model.BasketItem;
import com.imarques.abelibrary.service.BasketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("BasketControllerV1")
@RequestMapping("/v1/public/users/{id}/baskets")
@Api(tags = {"Basket"})
public class BasketController {
	@Autowired
	private BasketService basketService;
	
	@ApiOperation(value = "Obtém o carrinho de compras")
	@RequestMapping(method=RequestMethod.GET)
	public Basket getBasket(
			@ApiParam(required = true, value = "Código do usuário") @PathVariable("id") Long id) {
		Basket result = basketService.getPendingBasket(id);
		return result;
	}
	
	@ApiOperation(value = "Adiciona um item no carrinho de compras")
	@RequestMapping(method=RequestMethod.POST)
	public BasketItem addItem(
			@ApiParam(required = true, value = "Código do usuário") @PathVariable("id") Long id, 
			@ApiParam(required = true, value = "isbn") @RequestBody(required = true) String isbn) {
		BasketItem result = basketService.addItem(id, Long.valueOf(isbn));
		return result;
	}
	
	@ApiOperation(value = "Remove um item no carrinho de compras")
	@RequestMapping(method=RequestMethod.DELETE)
	public void deleteItem(
			@ApiParam(required = true, value = "Código do usuário") @PathVariable("id") Long id, 
			@ApiParam(required = true, value = "isbn") @RequestBody(required = true) String isbn) {
		basketService.delete(id, Long.valueOf(isbn));
	}
}
