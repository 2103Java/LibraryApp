package com.revature.repository;

import java.util.Map;
import java.util.Set;

import com.revature.models.Book;

public class StoreRoom implements BookRepo{
	
	//hardcoding database
	private Map<String, Book> libraryBooks;
	
	public StoreRoom(Map<String, Book> hardCodedBooks) {
		this.libraryBooks = hardCodedBooks;
	}

	@Override
	public Book getBookByName(String name) {
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
		// TODO Auto-generated method stub
		return null;
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
