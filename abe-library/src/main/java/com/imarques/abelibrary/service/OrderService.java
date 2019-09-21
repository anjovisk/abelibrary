package com.imarques.abelibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.imarques.abelibrary.model.Basket;
import com.imarques.abelibrary.model.DeliveryAddress;
import com.imarques.abelibrary.model.Order;
import com.imarques.abelibrary.model.OrderItem;
import com.imarques.abelibrary.model.Person;

@Service
public class OrderService {
	private Long orderId= Long.valueOf(1);
	private Long orderItemId= Long.valueOf(1);
	private static List<Order> orders = new ArrayList<Order>();
	
	public Order create(Basket basket, Person recipient, DeliveryAddress address) {
		Order result = new Order(orderId++, recipient, address);
		result.setBasketId(basket.getId());
		List<OrderItem> orderItens = new ArrayList<OrderItem>();
		basket.getItems().forEach(basketItem -> {
			for (int itemIndex = 1; itemIndex <= basketItem.getTotal(); itemIndex++) {
				OrderItem orderItem = new OrderItem();
				orderItem.setIsbn(basketItem.getIsbn());
				orderItem.setId(orderItemId++);
				orderItens.add(orderItem);
			}
		});
		result.setItems(orderItens);
		orders.add(result);
		return result;
	}
	
	public Order getOrder(Long id) {
		for (Order order: orders) {
			if (order.getId().equals(id)) {
				return order;
			}
		}
		return null;
	}
}
