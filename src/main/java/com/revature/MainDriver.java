package com.revature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.comparator.AuthorSorting;
import com.revature.models.Book;
import com.revature.models.User;

public class MainDriver {
	
	static Set<Book> libraryBooks = new HashSet<>();
	static Set<User> userSet = new HashSet<>();
	
	
	public static void inialiseValues() {
	
		
	}
	
	public final static Logger loggy = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
		
		inialiseValues(); //this is grabbing data from a file
//		
//		UserRepo ur = new BudgetDatabaseUsers(userSet); //Repo layer
//		BookRepo br = new StoreRoom(libraryBooks);
//		
//		ServiceLayer office = new Office(ur,br); //service layer 
//		
//		FrontOffice fo = new FrontOffice(office); //presentation layer
//		
//		loggy.fatal("Application has started up!");
//		
//		fo.displayMenu();
		
		persistValues();
		
//		-------------------------Showing off Comparator and Comparable-------------------------
		
		List<Book> toBeSorted = new ArrayList<>();
		
		toBeSorted.addAll(libraryBooks);
		
		AuthorSorting as = new AuthorSorting();
		

		
		for(Book b: libraryBooks) {
			System.out.println(b.getName() + "|" + b.getAuthor());
		}
		
		System.out.println("-------------------------------");
		
//		Collections.sort(toBeSorted);
		
		for(Book b: toBeSorted) {
			System.out.println(b.getName() + "|" + b.getAuthor());
		}
		
		
		Collections.sort(toBeSorted,as); // Collections (utility class that has useful methods) vs Collection API
		
		
		System.out.println("-------------------------------");
		
		for(Book b: toBeSorted) {
			System.out.println(b.getName() + "|" + b.getAuthor());
		}

		
		System.out.println("---------------------------------");
		
		//This is an example of a lambda expression 
		// We don't need to instantiate an entire class to implement a sinlge method!
		// (functional style of programming)
		Comparator<Book> compareId = (Book o1,Book o2) -> {
			
			int value;
			
				if(o1.getId() > o2.getId()) {
					value = 1;
				}else if(o1.getId() < o2.getId()) {
					value = -1;
				}else {
					value = 0;
				}
			return value;
			
		};
		
		Comparator<User> compareUserUSername = (User o1,User o2) -> {
			
			int value;
			
				if(o1.getUsername().compareTo(o2.getUsername()) > 0) {
					value = 1;
				}else if(o1.getUsername().compareTo(o2.getUsername()) < 0) {
					value = -1;
				}else {
					value = 0;
				}
			return value;
			
		};
		
		
		Collections.sort(toBeSorted, compareId);
		
		for(Book b: toBeSorted) {
			System.out.println(b.getName() + "|" + b.getAuthor());
		}
		
	}

}
