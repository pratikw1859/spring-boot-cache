package com.app.book.cache.service;

import java.util.List;

import com.app.book.cache.model.Book;

public interface IBookService {
	
	public Book save(Book book);
	
	public List<Book> findAll();
	
	public Book getBookById(Long id);
	
	public Book update(Long id, Book book);
	
	public void delete(Long id);
}
