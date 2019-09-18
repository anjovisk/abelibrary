package com.imarques.abelibrary.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imarques.abelibrary.domain.Book;
import com.imarques.abelibrary.domain.Comment;

@RestController("/books")
public class BookController {
	private final List<Book> books;
	private final int booksCount = 10;
	
	public BookController() {
		books = new ArrayList<Book>();
		for (int i = 0; i < booksCount; i++) {
			Book book = new Book();
			book.setIsbn(Long.valueOf(String.format("1245%s", i)));
			book.setDescription(String.format("Description %s", i));
			book.setName(String.format("Name %s", i));
			books.add(book);
		}
	}
	
	@RequestMapping(path="/books", method=RequestMethod.GET)
	public List<Book>  getBooks(@RequestParam(name="isbn", required=false) Long isbn, 
			@RequestParam(name="name", required=false) String name,
			@RequestParam(name="description", required=false) String description) {
		List<Book> booksTemp = new ArrayList<>();
		books.forEach(book -> {
			boolean isbnOk = isbn == null || book.getIsbn().equals(isbn);
			boolean nameOk = name == null || book.getName().toUpperCase().contains(name.toUpperCase());
			boolean descriptionOk = description == null || book.getDescription().toUpperCase().contains(description.toUpperCase());
			if (isbnOk && nameOk && descriptionOk) {
				booksTemp.add(book);
			}
		});
		return booksTemp;
	}
	
	@RequestMapping(path="/books/{isbn}", method=RequestMethod.GET)
	public Book getBook(@PathVariable("isbn") Long isbn) {
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}
	
	@RequestMapping(path="/books/{isbn}/comments", method=RequestMethod.GET)
	public List<Comment> getComments(@PathVariable("isbn") Long isbn) {
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				return book.getComments();
			}
		}
		return null;
	}
	
	@RequestMapping(path="/books/{isbn}/comments", method=RequestMethod.POST)
	public Book postComment(@PathVariable("isbn") Long isbn, Comment comment) {
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				comment.setId((long)book.getComments().size()+1);
				book.getComments().add(comment);
				return book;
			}
		}
		return null;
	}
}
