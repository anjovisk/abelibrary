package com.imarques.abelibrary.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imarques.abelibrary.model.Book;
import com.imarques.abelibrary.model.Comment;
import com.imarques.abelibrary.service.BookService;
import com.imarques.abelibrary.service.CommentService;
import com.imarques.abelibrary.util.DataContainer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("CommentControllerV1")
@RequestMapping("/v1/public/books/{isbn}/comments")
@Api(tags = {"Comments"})
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private BookService bookService;
	
	@ApiOperation(value = "Lista os comentários de um livro")
	@RequestMapping(method=RequestMethod.GET)
	public DataContainer<Comment> getComments(
			@ApiParam(required = true, value = "Código ISBN") @PathVariable("isbn") Long isbn,
			@ApiParam(required = false, value = "Quantidade máxima de comentários retornados na requisição", defaultValue = "10") @RequestParam(name="limit", required=false, defaultValue = "10") int limit,
			@ApiParam(required = false, value = "Quantidade de comentários ignorados na pesquisa", defaultValue = "0") @RequestParam(name="offset", required=false, defaultValue = "0") int offset) {
		DataContainer<Comment> result = commentService.find(bookService.getBook(isbn).getComments(), limit, offset);
		return result;
	}
	
	@ApiOperation(value = "Adiciona um comentário ao livro")
	@RequestMapping(method=RequestMethod.POST)
	public Comment postComment(
			@ApiParam(required = true, value = "Código ISBN") @PathVariable("isbn") Long isbn, 
			@ApiParam(required = true, value = "Commentário") @RequestBody(required = true) String text) {
		Comment result = commentService.save(isbn, text);
		Book book = bookService.getBook(isbn);
		book.getComments().add(result.getId());
		return result;
	}
}
