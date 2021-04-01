package com.revature.repository;

import java.util.Set;

import com.revature.models.Book;
import com.revature.models.User;

public interface UserRepo {
	
	public User getUserByUserName(String username);
	
	public boolean addUser(String username, String password, Set<Book> myBooks);
	//public deleteUser(User u)
	


}
