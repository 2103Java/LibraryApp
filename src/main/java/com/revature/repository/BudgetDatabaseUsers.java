package com.revature.repository;

import java.io.Serializable;
import java.util.Set;

import com.revature.models.Book;
import com.revature.models.User;

public class BudgetDatabaseUsers implements UserRepo, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 895355277937648921L;
	
	Set<User> userSet;
	
	public BudgetDatabaseUsers(Set<User> hardCodedUsers) {
		this.userSet = hardCodedUsers;
	}

	@Override
	public User getUserByUserName(String username) {
		
		return null;
	}

	@Override
	public boolean addUser(String username, String password, Set<Book> myBooks) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Set<User> getAllUsers(){
		return userSet;
	}

}
