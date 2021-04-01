package com.revature.repository;

import java.util.Set;

import com.revature.models.Book;
import com.revature.models.User;

public class BudgetDatabaseUsers implements UserRepo {

	@Override
	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(String username, String password, Set<Book> myBooks) {
		// TODO Auto-generated method stub
		return false;
	}

}
