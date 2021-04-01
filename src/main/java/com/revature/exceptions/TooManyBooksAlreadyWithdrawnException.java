package com.revature.exceptions;

import com.revature.models.User;

public class TooManyBooksAlreadyWithdrawnException extends RuntimeException {
	
	@Override
	public void printStackTrace() {
		
		super.printStackTrace();
		System.out.println("Too many books mate!");
	}
	
	public void printStackTrace(User u) {
		
		super.printStackTrace();
		
		System.out.println(u.getUsername() + " has too many books, precisely " + u.getMyBooks().size());
	}

}
