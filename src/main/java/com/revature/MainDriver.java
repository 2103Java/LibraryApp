package com.revature;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	
	public static Map<String, Book> libraryBooks = new HashMap<>();
	public static Set<User> userSet;
	
	
	public static void inialiseValues() throws IOException {
		ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("./libraryBooks.txt"));


		Book f1 = new Book(0,"Dune","FAkeAuthor1",true);
		Book f2 = new Book(0,"Expanse1","FAkeAuthor2",false);
		Book f3 = new Book(0,"Harry Potter ","FAkeAuthor3",false);
		Book f4 = new Book(0,"Lord of the rings","FAkeAuthor3",true);
		Book f5 = new Book(0,"Donquiexete 2","FAkeAuthor4",true);
		Book f6 = new Book(0,"Harry Potter 2 ","FAkeAuthor3",true);
		Book f7 = new Book(0,"Lord of the rings 2","FAkeAuthor3",true);
		Book f8 = new Book(0,"Donquiexete 2","FAkeAuthor4",true);
		
		
		libraryBooks.put(f1.getName(), f1);
		libraryBooks.put(f2.getName(), f2);
		libraryBooks.put(f3.getName(), f3);
		libraryBooks.put(f4.getName(), f4);
		libraryBooks.put(f5.getName(), f5);
		libraryBooks.put(f6.getName(), f6);
		libraryBooks.put(f7.getName(), f7);
		libraryBooks.put(f8.getName(), f8);

		objOut.writeObject(libraryBooks);

		Set<Book> bobsBooks = new HashSet<>();
		
		bobsBooks.add(f2);
		bobsBooks.add(f3);
		
		User u = new User("Bob","p4ssw0rd",bobsBooks);
		User u2 = new User("Frank", "pAssw0rd!", new HashSet<>());

		userSet = new HashSet<>();
		userSet.add(u);
		userSet.add(u2);

		ObjectInputStream objInput = new ObjectInputStream(new FileInputStream("./libraryBooks.txt"));

		try {
			for (Object obj : ((Map) objInput.readObject()).values())
			System.out.println(obj);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try {
			inialiseValues();
		} catch (IOException e) {
			e.printStackTrace();
		}

		UserRepo ur = new BudgetDatabaseUsers(userSet); //Repo layer
		BookRepo br = new StoreRoom(libraryBooks);
		
		ServiceLayer office = new Office(ur,br); //service layer 
		
		FrontOffice fo = new FrontOffice(office); //presentation layer
		
		
	}

}
