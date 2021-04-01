package com.revature.service;

import java.util.HashSet;
import java.util.Set;

import com.revature.exceptions.BookDoesNotExistException;
import com.revature.exceptions.TooManyBooksAlreadyWithdrawnException;
import com.revature.models.Book;
import com.revature.models.User;
import com.revature.repository.BookRepo;
import com.revature.repository.UserRepo;

public class Office implements ServiceLayer {
	
	BookRepo stockRoom;
	
	UserRepo yellowBook;

	public Office(UserRepo ur, BookRepo br) {
		this.stockRoom = br;
		this.yellowBook = ur;
	}

	@Override
	public boolean login(String username, String password) {
		User verifyPass = yellowBook.getUserByUserName(username);
		
		if(verifyPass == null) {
			return false;
		}
		
		//What happens if comparing a null value?
		if(verifyPass.getPassword().equals(password)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public User getUser(String username) {
		return yellowBook.getUserByUserName(username);
	}

	@Override
	public Set<Book> getAvailableBooks() {
		Set<Book> books = stockRoom.getAllBooks();
		Set<Book> availBooks = new HashSet<>();
		
		for(Book book: books) {
			if(book.isCheckedOut() == true) {
				availBooks.add(book);
			}
		}
		
		return availBooks;
	}

	@Override
	public boolean withdraw(User loggedInUser, String chosenBook)
			throws BookDoesNotExistException, TooManyBooksAlreadyWithdrawnException {
		
		if(loggedInUser.getMyBooks().size() < 3) {
			
			Set<Book> availBooks = getAvailableBooks();
			
			
		}
		else {
			throw new TooManyBooksAlreadyWithdrawnException();
		}
		return false;
	}

	@Override
	public boolean deposit(User loggedInUser, String depositBook) {
		// TODO Auto-generated method stub
		return true;

	}

}
