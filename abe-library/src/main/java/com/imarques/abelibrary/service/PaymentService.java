package com.imarques.abelibrary.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imarques.abelibrary.model.Book;
import com.imarques.abelibrary.model.PaymentSummary;

@Service
public class PaymentService {
	@Autowired
	private BasketService basketService;
	@Autowired
	private BookService bookService;
	
	public PaymentSummary calculate(Long userId) {
		PaymentSummary result = new PaymentSummary();
		result.setBasket(basketService.getPendingBasket(userId));
		List<Long> booksId = new ArrayList<>();
		result.getBasket().getItems().forEach(basketItem -> {
			for (int i = 0; i < basketItem.getTotal(); i++) {
				booksId.add(basketItem.getIsbn());
			}
		});
		List<Book> books = bookService.getBooks(booksId);
		result.setPrice(new BigDecimal(books.stream().mapToDouble(book -> book.getPrice().doubleValue()).sum(), MathContext.DECIMAL64));
		return result;
	}
}
