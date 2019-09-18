package com.imarques.abelibrary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imarques.abelibrary.domain.Book;
import com.imarques.abelibrary.domain.Comment;
import com.imarques.abelibrary.service.BookService;

@RestController("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(path="/books", method=RequestMethod.GET)
	public List<Book>  getBooks(@RequestParam(name="isbn", required=false) Long isbn, 
			@RequestParam(name="name", required=false) String name,
			@RequestParam(name="description", required=false) String description) {
		List<Book> booksTemp = new ArrayList<>();
		
		return booksTemp;
	}
	
	@RequestMapping(path="/books/{isbn}", method=RequestMethod.GET)
	public Book getBook(@PathVariable("isbn") Long isbn) {
		Book book = bookService.getBook(isbn);
		return book;
	}
	
	@RequestMapping(path="/books/{isbn}/comments", method=RequestMethod.GET)
	public List<Comment> getComments(@PathVariable("isbn") Long isbn) {
		Book book = bookService.getBook(isbn);
		return book != null ? book.getComments() : null;
	}
	
	@RequestMapping(path="/books/{isbn}/comments", method=RequestMethod.POST)
	public Comment postComment(@PathVariable("isbn") Long isbn, Comment comment) {
		Comment result = bookService.postComments(isbn, comment);
		return result;
	}
}
