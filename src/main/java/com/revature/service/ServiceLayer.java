package com.revature.service;

import java.util.Set;

import com.revature.exceptions.BookDoesNotExistException;
import com.revature.exceptions.TooManyBooksAlreadyWithdrawnException;
import com.revature.models.Book;
import com.revature.models.User;
import com.revature.repository.BudgetDatabaseUsers;
import com.revature.repository.StoreRoom;

public interface ServiceLayer{

	boolean login(String username, String password); //throw userDoesNotExist, passwordIncorrect

	User getUser(String username);

	Set<Book> getAvailableBooks();

	boolean withdraw(User loggedInUser, String chosenBook) throws BookDoesNotExistException, TooManyBooksAlreadyWithdrawnException;

	void deposit(User loggedInUser, String depositBook);

	void WriteReposToFile(StoreRoom br, BudgetDatabaseUsers ur);

	void ReadReposFromFile();

}
