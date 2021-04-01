package com.revature.repository;

import java.util.Set;

import com.revature.models.Book;
import com.revature.models.User;

public class BudgetDatabaseUsers implements UserRepo {
	
	Set<User> userSet;
	
	public BudgetDatabaseUsers(Set<User> hardCodedUsers) {
		this.userSet = hardCodedUsers;
	}

	@Override
	public User getUserByUserName(String username)
	{
		for(User user: this.userSet)
		{
			if (user.getUsername().equals(username))
			{
				return user;
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(String username, String password, Set<Book> myBooks) {
		// TODO Auto-generated method stub
		return false;
	}

}
