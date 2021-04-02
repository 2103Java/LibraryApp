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

		//System.out.println(" yellowRepo: getUserByUsername = "+this.yellowBook.getUserByUserName(username));
		
		//System.out.println("yellowBook.getUserByUserName(username).getPassword().compareTo(password) == 0 is "+(yellowBook.getUserByUserName(username).getPassword().compareTo(password) == 0));
		if (yellowBook.getUserByUserName(username) == null) {
			return false;
		}
		if (yellowBook.getUserByUserName(username).getPassword().compareTo(password) == 0) {
			return true;
		}

		return false;
	}

	
	@Override
	public User getUser(String username) {
		return yellowBook.getUserByUserName(username);
	}

	
	@Override
	public Set<Book> getAvailableBooks() {
		
		Set<Book> booksAvailable = new HashSet<Book>();
		for (Book b : stockRoom.getAllBooks()) {
			if (!b.isCheckedOut()) {
				booksAvailable.add(b);
			}
		}
		
		return booksAvailable;
	}

	@Override
	public boolean withdraw(User loggedInUser, String chosenBook)
			throws BookDoesNotExistException, TooManyBooksAlreadyWithdrawnException {
		
	
		boolean toReturn = false;
		Book b = null;
		
		
		

		if (loggedInUser.getMyBooks() == null) {
			loggedInUser.setMyBooks(new HashSet<Book>());
		}
		

		// Check if user already has 3 books
		if (loggedInUser.getMyBooks().size() < 3) {

			// Check available books by name
			b = stockRoom.getBookByName(chosenBook);
		
			if (b != null) {
				// Check if the book is available
				if (!b.isCheckedOut()) {
					// Process the withdrawal
					//add the book to the users book set
					loggedInUser.getMyBooks().add(b);
					//set the isCheckedOut field to true
					b.setCheckedOut(true);
					//set the return bool to true for success
					toReturn = true;
				}
			}
			else {

				throw new BookDoesNotExistException();
			}
		}
		else {

			throw new TooManyBooksAlreadyWithdrawnException();
		}
		

		return toReturn;

		
		
	}

	@Override
	public boolean deposit(User loggedInUser, String depositBook) {


		
		Book b = null;
		boolean bookDeposited = false;
		
		// Check if book exists in storeRoom
		b = stockRoom.getBookByName(depositBook);
		if (b != null) {
			// Check if the book is available
			if (b.isCheckedOut()) {
				// Process the deposit
				//remove the book from the users book set
				loggedInUser.getMyBooks().remove(b);
				//set the isCheckedOut field to false
				b.setCheckedOut(false);
				bookDeposited = true;
			}
		}
		else {
			//System.out.println("BookDoesNotExistException()");
		}

		return bookDeposited;
		
		

		
	}

}
