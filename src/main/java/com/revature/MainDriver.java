package com.revature;

import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
	
	
	public static void inialiseValues() {
		
		String filename = "./Books.txt";
		

		Object obj = null;
		
		ObjectInputStream ois = null;
		try {
			
			ois = new ObjectInputStream(new FileInputStream(filename));
			
			while ( (obj = ois.readObject()) != null) {
				Book curBook = (Book)obj;
				System.out.println((curBook));
			}

			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch(EOFException e) {
				//System.out.println("EOF");
			}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		/*
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
		
		
		
		
		
		userSet = new HashSet<>();
		userSet.add(u);
		userSet.add(u2);
		*/
		
		
	}
	
	public static void writeBooks(ArrayList<Book> books) {
		
		
		String filename = "./Books.txt";


		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
			
			for (Book bk : books)
			oos.writeObject(bk);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("main() start");
		
		Book book1 = new Book(1, "Welcome to London", "J.R. Briggleton", true);
		Book book2 = new Book(2, "The Great Book", "Arthur", true);
		Book book3 = new Book(3, "Good Book", "Patrickson", true);
		Book book4 = new Book(4, "The Mysterious Forest", "Frank", true);
		Book book5 = new Book(5, "The Mysterious Forest 2", "Frank", true);
		ArrayList<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);
		
		writeBooks(books);
		inialiseValues();
		/*
		UserRepo ur = new BudgetDatabaseUsers(userSet); //Repo layer
		BookRepo br = new StoreRoom(libraryBooks);
		
		ServiceLayer office = new Office(ur,br); //service layer 
		
		FrontOffice fo = new FrontOffice(office); //presentation layer
		*/
		
		
	}

}
