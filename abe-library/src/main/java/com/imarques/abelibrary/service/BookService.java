package com.imarques.abelibrary.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.imarques.abelibrary.model.Book;
import com.imarques.abelibrary.util.DataContainer;

@Service
public class BookService {
	private static List<Book> books;
	private static int booksCount = 100;
	
	@PostConstruct
	public void populateBooks() {
		books = new ArrayList<Book>();
		for (int i = 0; i < booksCount; i++) {
			Book book = new Book();
			book.setIsbn(Long.valueOf(String.format("%s", i)));
			book.setDescription(String.format("Description %s", i));
			book.setName(String.format("Name %s", i));
			book.setPrice(BigDecimal.valueOf(i));
			books.add(book);
		}
	}
	
	public DataContainer<Book> find(Book searchParameters, int limit, int offset) {
		List<Book> booksTemp = new ArrayList<>();
		books.forEach(book -> {
			boolean isbnOk = searchParameters.getIsbn() == null || book.getIsbn().equals(searchParameters.getIsbn());
			boolean nameOk = searchParameters.getName() == null || book.getName().toUpperCase().contains(searchParameters.getName().toUpperCase());
			boolean descriptionOk = searchParameters.getDescription() == null || book.getDescription().toUpperCase().contains(searchParameters.getDescription().toUpperCase());
			if (isbnOk && nameOk && descriptionOk) {
				booksTemp.add(book);
			}
		});
		DataContainer<Book> result = new DataContainer<Book>(limit, offset, books.size(), booksTemp.subList(offset, (offset+limit <= booksTemp.size() ? offset+limit : booksTemp.size())));
		return result;
	}
	
	public Book getBook(Long isbn) {
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}
	
	public List<Book> getBooks(List<Long> isbns) {
		List<Book> result = isbns.stream().map(isbn -> getBook(isbn)).collect(Collectors.toList());
		return result;
	}
	
	public Book save(Book book) {
		books.add(book);
		//TODO - Adicionar validação de ISBN
		return book;
	}
	
	public void delete(Long isbn) {
		Book book = getBook(isbn);
		//TODO - Adicionar validação para verificar se o livro existe
		books.remove(book);
	}
	
	public void edit(Book book) {
		Book item = getBook(book.getIsbn());
		item.setName(book.getName());
		item.setDescription(book.getDescription());
		item.setPrice(book.getPrice());
	}
}
