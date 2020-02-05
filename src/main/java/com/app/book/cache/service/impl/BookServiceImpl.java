package com.app.book.cache.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.app.book.cache.model.Book;
import com.app.book.cache.repository.BookRepository;
import com.app.book.cache.service.IBookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements IBookService {
	
	private BookRepository repo;
	
	public BookServiceImpl(BookRepository repo) {
		super();
		this.repo = repo;
	}
	
	public Book save(Book book) {
		log.info("Saving Book Into DB");
		return repo.save(book);
	}
	
	public List<Book> findAll() {
		log.info("Fetching List of Books From DB");
		return repo.findAll();
	}
	
	@Cacheable(cacheNames = "books", key = "#id")
	public Book getBookById(Long id) {
		log.info("Fetching Book From DB");
		Optional<Book> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else
			return new Book();
	}
	
	@CachePut(cacheNames = "books", key = "#id")
	public Book update(Long id, Book book) {
		log.info("Updating Book Into DB");
		Optional<Book> optional = repo.findById(id);
		
		if(optional.isPresent()) {
			Book getFromDb = optional.get();
			getFromDb.setName(book.getName());
			getFromDb.setAuthor(book.getAuthor());
			getFromDb.setCategory(book.getCategory());
			getFromDb.setPublisher(book.getPublisher());
			getFromDb.setEdition(book.getEdition());
			
			return repo.save(getFromDb);
		}
		throw new RuntimeException("Book With Id:"+id+" Not Present");
	}
	
	@CacheEvict(cacheNames = "books", key = "#id")
	public void delete(Long id) {
		log.info("Deleting Book From DB");
		repo.deleteById(id);
	}
}
