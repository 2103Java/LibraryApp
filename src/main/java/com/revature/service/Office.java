package com.revature.service;

import java.util.Set;

import com.revature.exceptions.BookDoesNotExistException;
import com.revature.exceptions.TooManyBooksAlreadyWithdrawnException;
import com.revature.models.Book;
import com.revature.models.User;
import com.revature.repository.BookRepo;
import com.revature.repository.BudgetDatabaseUsers;
import com.revature.repository.StoreRoom;
import com.revature.repository.UserRepo;

public class Office implements ServiceLayer {
	
	StoreRoom stockRoom;
	
	BudgetDatabaseUsers yellowBook;

	public Office(UserRepo ur, BookRepo br) {
		this.stockRoom = (StoreRoom) br;
		this.yellowBook = (BudgetDatabaseUsers) ur;
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		
		User u = yellowBook.getUserByUserName(username);
		//System.out.println(u + "2");
		return u;
	}

	@Override
	public Set<Book> getAvailableBooks() {
		Set<Book> tempSet = stockRoom.getAllBooks();
		return tempSet;
	}

	@Override
	public boolean withdraw(User loggedInUser, String chosenBook)
			throws BookDoesNotExistException, TooManyBooksAlreadyWithdrawnException {
		// TODO Auto-generated method stub
		Book tempBook = stockRoom.getBookByName(chosenBook);
		
		Set<Book> tempSet = stockRoom.getAllBooks();
		System.out.println(tempBook + "2");
		for(Book b:tempSet)
		{
			System.out.println(b);
		}
		if(tempSet.contains(tempBook))
			{
			tempBook.setCheckedOut(true);
			Set<Book> uBook = loggedInUser.getMyBooks();
			uBook.add(tempBook);
			
			
			
			return true;
			}
		
		
		else
		{return false;}
	}

	@Override
	public void deposit(User loggedInUser, String depositBook) {
		// TODO Auto-generated method stub

	}

}
