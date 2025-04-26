package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Review;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public Book saveBook(Book book) {
	  return bookRepository.save(book);
	}
	
	public Book updateBook(Long id,Book book) {
		Book updatingbook=bookRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not found with id:" +id));
		updatingbook.setTitle(book.getTitle());
	    updatingbook.setAuthor(book.getAuthor());
	    updatingbook.setGenre(book.getGenre());
	    
	    return bookRepository.save(updatingbook);
	}
	
	public void deleteBook(Long id) {
		if(!bookRepository.existsById(id)) {
			throw new RuntimeException("Book not found with id:"+id);
		}
		bookRepository.deleteById(id);
		}
	

	
	 }


