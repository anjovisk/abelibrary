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
import com.imarques.abelibrary.model.PaymentAuthorization;
import com.imarques.abelibrary.model.Payment.PaymentStatus;
import com.imarques.abelibrary.model.PaymentData;
import com.imarques.abelibrary.model.PaymentSummary;
import com.imarques.abelibrary.model.Transaction;

@Service
public class PaymentService {
	private static List<Payment> payments = new ArrayList<Payment>();
	
	@Autowired
	private BasketService basketService;
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CreditCardAuthorizationService creditCardAuthorizationService;
	
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
		Transaction transaction = new Transaction();
		transaction.setCreditCard(paymentData.getCreditCard());
		transaction.setInstallments(paymentData.getInstallments());
		transaction.setValue(getBasketPrice(basket));
		PaymentAuthorization paymentAuthorization = creditCardAuthorizationService.authorize(transaction);
		payment.setAuthorizationId(paymentAuthorization.getId());
		payment.setStatus(PaymentAuthorization.AuthorizationStatus.SUCCESS.equals(paymentAuthorization.getStatus())
				? PaymentStatus.SUCCESS
						: PaymentStatus.ERROR);
		basket.setStatus(PaymentAuthorization.AuthorizationStatus.SUCCESS.equals(paymentAuthorization.getStatus())
				? BasketStatus.PAYED
						: BasketStatus.PENDING);
		//TODO - Utilizar messages resource
		payment.setDetails(PaymentAuthorization.AuthorizationStatus.SUCCESS.equals(paymentAuthorization.getStatus())
				? "Pagamento efetuado com sucesso"
						: "Pagamento não autorizado pela operadora do cartão");
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
