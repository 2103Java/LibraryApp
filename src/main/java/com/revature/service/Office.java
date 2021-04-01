package com.revature.service;

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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> getAvailableBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean withdraw(User loggedInUser, String chosenBook)
			throws BookDoesNotExistException, TooManyBooksAlreadyWithdrawnException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deposit(User loggedInUser, String depositBook) {
		// TODO Auto-generated method stub

	}

}
