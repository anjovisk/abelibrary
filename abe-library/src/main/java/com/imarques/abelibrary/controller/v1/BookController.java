package com.imarques.abelibrary.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imarques.abelibrary.model.Book;
import com.imarques.abelibrary.service.BookService;
import com.imarques.abelibrary.util.DataContainer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("BookControllerV1")
@RequestMapping("/v1/public/books")
@Api(tags = {"Books"})
public class BookController {
	@Autowired
	private BookService bookService;
	
	@ApiOperation(value = "Lista os livros do acervo")
	@RequestMapping(method=RequestMethod.GET)
	public DataContainer<Book>  getBooks(
			@ApiParam(required = false, value = "Código ISBN") @RequestParam(name="isbn", required=false) Long isbn, 
			@ApiParam(required = false, value = "Nome do livro") @RequestParam(name="name", required=false) String name,
			@ApiParam(required = false, value = "Descrição do livro") @RequestParam(name="description", required=false) String description,
			@ApiParam(required = false, value = "Quantidade máxima de livros retornados na requisição", defaultValue = "10") @RequestParam(name="limit", required=false, defaultValue = "10") int limit,
			@ApiParam(required = false, value = "Quantidade de livros ignorados na pesquisa", defaultValue = "0") @RequestParam(name="offset", required=false, defaultValue = "0") int offset) {
		Book bookParameters = new Book();
		bookParameters.setIsbn(isbn);
		bookParameters.setName(name);
		bookParameters.setDescription(description);
		return bookService.find(bookParameters, limit, offset);
	}
	
	@ApiOperation(value = "Obtém um dos livros do acervo")
	@RequestMapping(path="/{isbn}", method=RequestMethod.GET)
	public Book getBook(
			@ApiParam(required = true, value = "Código ISBN") @PathVariable("isbn") Long isbn) {
		Book book = bookService.getBook(isbn);
		return book;
	}
	
	@ApiOperation(value = "Cadastra um livro no acervo")
	@RequestMapping(method=RequestMethod.POST)
	public Book postBook(
			@ApiParam(required = true, value = "Dados cadastrais do novo livro") @RequestBody Book book) {
		Book result = bookService.save(book);
		return result;
	}
	
	@ApiOperation(value = "Altera um dos livros do acervo")
	@RequestMapping(path="/{isbn}", method=RequestMethod.PUT)
	public void putBook(
			@ApiParam(required = true, value = "Dados cadastrais para atualização do livro") @RequestBody Book book) {
		bookService.edit(book);
	}
	
	@ApiOperation(value = "Remove um dos livros do acervo")
	@RequestMapping(path="/{isbn}", method=RequestMethod.DELETE)
	public void deleteBook(
			@ApiParam(required = true, value = "Código ISBN") @PathVariable("isbn") Long isbn) {
		bookService.delete(isbn);
	}
}
