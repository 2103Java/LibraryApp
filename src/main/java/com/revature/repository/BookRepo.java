package com.revature.repository;

import java.util.Set;

import com.revature.models.Book;

public interface BookRepo {
	
	public Book getBookByName(String s);
	
	public Book getBookById();
	
	public Set<Book> getBooksByAuthor();
	
	public Set<Book> getAllBooks();
	
	public boolean deleteBook(Book b);
	
	public boolean addBook(Book b);
	
	//public boolean updateBook(Book b, String newAutor);
	
	

}
