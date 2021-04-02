package com.revature;

import java.util.HashSet;
import java.util.Set;

import com.revature.models.Book;
import com.revature.models.User;
import com.revature.presentation.FrontOffice;
import com.revature.repository.BookRepo;
import com.revature.repository.BudgetDatabaseUsers;
import com.revature.repository.StoreRoom;
import com.revature.repository.UserRepo;
import com.revature.service.Office;
import com.revature.service.ServiceLayer;

public class MainDriver {
	
	static Set<Book> libraryBooks;
	static Set<User> userSet;
	
	
	public static void initaliseValues() {
		
		libraryBooks = new HashSet<Book>();
		userSet = new HashSet<User>();
		
		Book f1 = new Book(0,"Dune","FAkeAuthor1",true);
		Book f2 = new Book(0,"Expanse1","FAkeAuthor2",false);
		Book f3 = new Book(0,"Harry Potter ","FAkeAuthor3",false);
		Book f4 = new Book(0,"Lord of the rings","FAkeAuthor3",true);
		Book f5 = new Book(0,"Donquiexete 2","FAkeAuthor4",true);
		Book f6 = new Book(0,"Harry Potter 2 ","FAkeAuthor3",true);
		Book f7 = new Book(0,"Lord of the rings 2","FAkeAuthor3",true);
		Book f8 = new Book(0,"Donquiexete 2","FAkeAuthor4",true);
		
		
		libraryBooks.add(f1);
		libraryBooks.add(f2);
		libraryBooks.add(f3);
		libraryBooks.add(f4);
		libraryBooks.add(f5);
		libraryBooks.add(f6);
		libraryBooks.add(f7);
		libraryBooks.add(f8);
		
		Set<Book> bobsBooks = new HashSet<>();
		
		bobsBooks.add(f2);
		bobsBooks.add(f3);
		
		User u = new User("Bob","p4ssw0rd",bobsBooks);
		User u2 = new User("Frank", "pAssw0rd!", new HashSet<>());
		
		
		
		
		
		userSet.add(u);
		userSet.add(u2);
		
		
		
	}
	
	public static void main(String[] args) {
		
		initaliseValues();
		
		BudgetDatabaseUsers ur = new BudgetDatabaseUsers(userSet); //Repo layer
		StoreRoom br = new StoreRoom(libraryBooks);
		
		ServiceLayer office = new Office(ur,br); //service layer 
		
		FrontOffice fo = new FrontOffice(office); //presentation layer
		
		
		office.WriteReposToFile(br, ur);
		office.ReadReposFromFile();
		
	}

}
