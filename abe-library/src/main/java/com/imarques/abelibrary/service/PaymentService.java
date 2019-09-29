package com.imarques.abelibrary.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imarques.abelibrary.model.Basket;
import com.imarques.abelibrary.model.Basket.BasketStatus;
import com.imarques.abelibrary.model.Book;
import com.imarques.abelibrary.model.Payment;
import com.imarques.abelibrary.model.Payment.PaymentStatus;
import com.imarques.abelibrary.model.PaymentData;
import com.imarques.abelibrary.model.PaymentSummary;

@Service
public class PaymentService {
	private static List<Payment> payments = new ArrayList<Payment>();
	
	@Autowired
	private BasketService basketService;
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderService orderService;
	
	public PaymentSummary calculate(String username) {
		PaymentSummary result = new PaymentSummary();
		result.setBasket(basketService.getPendingBasket(username));
		result.setPrice(getBasketPrice(result.getBasket()));
		return result;
	}
	
	public Payment pay(String username, PaymentData paymentData) {
		Basket basket = basketService.getPendingBasket(username);
		Payment payment = new Payment();
		payment.setBasketId(basket.getId());
		payment.setPaymentData(paymentData);
		payment.setPaymentDate(LocalDateTime.now());
		payment.setValue(getBasketPrice(basket));
		//TODO - Simular status do pagamento
		payment.setStatus(PaymentStatus.SUCCESS);
		basket.setStatus(BasketStatus.PAYED);
		//TODO - Utilizar messages resource
		payment.setDetails("Pagamento efetuado com sucesso");
		payments.add(payment);
		orderService.create(basket, paymentData.getRecipient(), paymentData.getDeliveryAddress());
		return payment;
	}
	
	private BigDecimal getBasketPrice(Basket basket) {
		List<Long> booksId = new ArrayList<>();
		basket.getItems().forEach(basketItem -> {
			for (int i = 0; i < basketItem.getTotal(); i++) {
				booksId.add(basketItem.getIsbn());
			}
		});
		List<Book> books = bookService.getBooks(booksId);
		BigDecimal result = new BigDecimal(books.stream().mapToDouble(book -> book.getPrice().doubleValue()).sum(), MathContext.DECIMAL64);
		return result;
	}
}
