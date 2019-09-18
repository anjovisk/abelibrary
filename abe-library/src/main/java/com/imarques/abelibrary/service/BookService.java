package com.imarques.abelibrary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.imarques.abelibrary.domain.Book;
import com.imarques.abelibrary.domain.Comment;
import com.imarques.abelibrary.util.DataContainer;

@Service
public class BookService {
	private static List<Book> books;
	private static int booksCount = 10;
	
	@PostConstruct
	public void populateBooks() {
		books = new ArrayList<Book>();
		for (int i = 0; i < booksCount; i++) {
			Book book = new Book();
			book.setIsbn(Long.valueOf(String.format("1245%s", i)));
			book.setDescription(String.format("Description %s", i));
			book.setName(String.format("Name %s", i));
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
		DataContainer<Book> result = new DataContainer<Book>(limit, offset, books.size(), booksTemp.subList(limit, offset));
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
	
	public Comment postComments(Long isbn, Comment comment) {
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				comment.setId((long)book.getComments().size()+1);
				book.getComments().add(comment);
				return comment;
			}
		}
		return null;
	}
}
