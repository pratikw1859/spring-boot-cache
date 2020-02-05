package com.app.book.cache.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.book.cache.model.Book;
import com.app.book.cache.service.IBookService;

@RestController
@RequestMapping(BookController.URL)
public class BookController {
	public static final String URL = "/api/v1/book";
	private IBookService bookService;

	public BookController(IBookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@PostMapping
    public ResponseEntity<?> save(@RequestBody Book book) {
		Book savedInDb = bookService.save(book);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedInDb.getId()).toUri();
		return ResponseEntity.created(location).body(savedInDb);
	}
	
	@GetMapping
	public List<Book> getAll() {
		return bookService.findAll();
	}
	
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable("id") Long id) {
		return bookService.getBookById(id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		bookService.delete(id);
		return "Book with Id:"+id+" Deleted Successfully";
	}
	
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
		return bookService.update(id, book);
	}
}
