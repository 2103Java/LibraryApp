package com.revature.repository;

import java.io.Serializable;
import java.util.Set;

import com.revature.models.Book;

public class StoreRoom implements BookRepo, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4507651331674640016L;
	//hardcoding database
	
	private Set<Book> libraryBooks;
	
	public StoreRoom(Set<Book> hardCodedBooks) {
		this.libraryBooks = hardCodedBooks;
	}

	@Override
	public Book getBookByName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> getBooksByAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> getAllBooks() {
		return libraryBooks;
	}

	@Override
	public boolean deleteBook(Book b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBook(Book b) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
