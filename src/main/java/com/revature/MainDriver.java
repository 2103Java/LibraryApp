package com.revature;

import java.util.Scanner;

import com.revature.exceptions.BookDoesNotExistException;
import com.revature.exceptions.TooManyBooksAlreadyWithdrawnException;

public class MainDriver {
	
	public static void main(String[] args) {
		
//		throw new ArithmeticException();
		
//		throw new TooManyBooksAlreadyWithdrawnException();
		
		try {
			throw new TooManyBooksAlreadyWithdrawnException();
		}catch(TooManyBooksAlreadyWithdrawnException e) {
			e.printStackTrace();
		}
		
	}

}
