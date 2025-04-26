package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Book;
import com.example.demo.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books/view")
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@PostMapping("/books/add")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		Book savedBook=bookService.saveBook(book);
		return new ResponseEntity<>(savedBook,HttpStatus.CREATED);
	}
	
	@PutMapping("/books/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id,@RequestBody Book book){
		Book updatedBook=bookService.updateBook(id,book);
		return ResponseEntity.ok(updatedBook);	
	}
	
	@DeleteMapping("/books/delete/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id){
		bookService.deleteBook(id);
		return ResponseEntity.ok("Book deleted successfully");
	}
	
	
	
	
	

}
