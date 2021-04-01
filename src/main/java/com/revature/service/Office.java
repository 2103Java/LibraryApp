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

  // breakout room 2 was here!
	@Override
	public boolean login(String username, String password) {
		User u = yellowBook.getUserByUserName(username);

		if(u.getPassword().equals(password)){
			return true;
		} else {
			return false;
		}
	}

	// breakout room 2 was here!
	@Override
	public User getUser(String username) {
		return yellowBook.getUserByUserName(username);
	}

	// Room 2 was here!
	@Override
	public Set<Book> getAvailableBooks() {
		// call getAllBooks
		// iterate through set
		//
		Set<Book> All = this.stockRoom.getAllBooks();
		Set<Book> Available = new HashSet<>();
		for(Book b:All) {
			if(!b.isCheckedOut()) {
				Available.add(b);
			}
		}
		return Available;
	}

	// Room 2 was here!
	@Override
	public boolean withdraw(User loggedInUser, String chosenBook)
			throws BookDoesNotExistException, TooManyBooksAlreadyWithdrawnException {

		Book b = this.stockRoom.getBookByName(chosenBook);
		Set<Book> myBooks = loggedInUser.getMyBooks();
		if(myBooks.size() > 2) {
			throw new TooManyBooksAlreadyWithdrawnException();
		}
		b.setCheckedOut(true);
		myBooks.add(b);
		return false;
	}

	@Override
	public void deposit(User loggedInUser, String depositBook) {
		// TODO Auto-generated method stub

	}

}
