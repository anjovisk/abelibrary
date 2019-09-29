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
import com.imarques.abelibrary.model.Basket;
import com.imarques.abelibrary.service.BasketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("BasketControllerV1")
@RequestMapping("/v1/public/baskets/{username}")
@Api(tags = {"Baskets"})
public class BasketController {
	@Autowired
	private BasketService basketService;
	
	@ApiOperation(value = "Obtém o carrinho de compras")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Basket> getBasket(
			@ApiParam(required = true, value = "Nickname do usuário") @PathVariable("username") String username) {
		Basket result = null;
		try {
			result = basketService.getPendingBasket(username);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		return ResponseEntity.accepted().body(result);
	}
	
	@ApiOperation(value = "Adiciona um item no carrinho de compras")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Basket> addItem(
			@ApiParam(required = true, value = "Nickname do usuário") @PathVariable("username") String username, 
			@ApiParam(required = true, value = "isbn") @RequestBody(required = true) String isbn) {
		Basket result = null;
		try {
			result = basketService.addItem(username, Long.valueOf(isbn));
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		return ResponseEntity.accepted().body(result);
	}
	
	@ApiOperation(value = "Remove um item no carrinho de compras")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteItem(
			@ApiParam(required = true, value = "Nickname do usuário") @PathVariable("username") String username, 
			@ApiParam(required = true, value = "isbn") @RequestBody(required = true) String isbn) {
		try {
			basketService.delete(username, Long.valueOf(isbn));
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		return ResponseEntity.accepted().body(null);
	}
}
