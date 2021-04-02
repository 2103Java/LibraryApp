package com.revature;

import java.util.ArrayList;
import java.util.List;

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
	
	static List<Book> libraryBooks;
	static List<User> userList;
	
	
	public static void inialiseValues() {
		
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
		
		List<Book> bobsBooks = new ArrayList<>();
		
		bobsBooks.add(f2);
		bobsBooks.add(f3);
		
		User u = new User("Bob","p4ssw0rd",bobsBooks);
		User u2 = new User("Frank", "pAssw0rd!", new ArrayList<>());
		
		
		userList = new ArrayList<>();
		userList.add(u);
		userList.add(u2);
		
		
		
	}
	
	public static void main(String[] args) {
		
		Book f1 = new Book(0,"Dune","FAkeAuthor1",true);
		Book f2 = new Book(0,"Expanse1","FAkeAuthor2",false);
		Book f3 = new Book(0,"Harry Potter ","FAkeAuthor3",false);
		Book f4 = new Book(0,"Lord of the rings","FAkeAuthor3",true);
		Book f5 = new Book(0,"Donquiexete 2","FAkeAuthor4",true);
		Book f6 = new Book(0,"Harry Potter 2 ","FAkeAuthor3",true);
		Book f7 = new Book(0,"Lord of the rings 2","FAkeAuthor3",true);
		Book f8 = new Book(0,"Donquiexete 2","FAkeAuthor4",true);
		
		List<Object> libraryBooks = new ArrayList<Object>();
		libraryBooks.add(f1);
		libraryBooks.add(f2);
		libraryBooks.add(f3);
		libraryBooks.add(f4);
		libraryBooks.add(f5);
		libraryBooks.add(f6);
		libraryBooks.add(f7);
		libraryBooks.add(f8);
		
		
		serializationRoutine s = new serializationRoutine();
		
//		for (Book b: libraryBooks) {
//			s.serialize(b);
//		}
		s.serializeList(libraryBooks);
		
		List<Object> fromFile = s.deserialize("./Objects.txt");
		System.out.println(fromFile.size());
		for(Object O: fromFile) {
			Book b1 = (Book) O;
			System.out.println(b1.getName());
		}
		
		
		
		//UserRepo ur = new BudgetDatabaseUsers(userList); //Repo layer
		//BookRepo br = new StoreRoom(libraryBooks);
		
		//ServiceLayer office = new Office(ur,br); //service layer 
		
		//FrontOffice fo = new FrontOffice(office); //presentation layer
		
		
	}

}
