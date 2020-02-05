package com.app.book.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.book.cache.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
