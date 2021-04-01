package com.revature.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import com.revature.exceptions.BookDoesNotExistException;
import com.revature.exceptions.TooManyBooksAlreadyWithdrawnException;
import com.revature.models.Book;
import com.revature.models.User;
import com.revature.repository.BookRepo;
import com.revature.repository.UserRepo;
import com.revature.service.Office;

public class OfficeTest {
	
	@Mock
	private BookRepo br;
	
	@Mock
	private UserRepo ur;
	
	private Office o;

	
	@Test
	public void testLogin() {
		
		
		
		User u = new User("Bob", "p4ssw0rd", null);
		
		ur = mock(UserRepo.class);
		
		when(ur.getUserByUserName("Bob")).thenReturn(u);

		o = new Office(ur,br); //Moved the office object declaration to be after we set 
							   //Mockito variable behavior
		
		assertTrue( o.login("Bob", "p4ssw0rd"));
		assertFalse( o.login("Bob", "password"));
		assertFalse(o.login("Fakey fake", "p4ssw0rd"));
		
		
	}
	
	@Ignore
	public void testGetUser() {
		
	}
	
	@Test //feature has been depcreated!
	public void testGetAvailableBooks() {
		br = mock(BookRepo.class);
		ur = mock(UserRepo.class);
		
		Set<Book> fakeSet = new HashSet<>();
		Book f1 = new Book(0,"Fake1","FAkeAuthor1",true);
		Book f2 = new Book(0,"Fake2","FAkeAuthor2",false);
		Book f3 = new Book(0,"Fake3","FAkeAuthor3",false);
		Book f4 = new Book(0,"Fake4","FAkeAuthor3",true);
		Book f5 = new Book(0,"Fake5","FAkeAuthor4",true);
		
		fakeSet.add(f1);
		fakeSet.add(f2);
		fakeSet.add(f3);
		fakeSet.add(f4);
		fakeSet.add(f5);
		
		when(br.getAllBooks()).thenReturn(fakeSet);
		
		o = new Office(ur,br);
		
		
		assertEquals(3, o.getAvailableBooks().size()); //added .size() for assertEquals
		assertTrue(br.getAllBooks().contains(f1));
		assertTrue(br.getAllBooks().contains(f4));
		assertTrue(br.getAllBooks().contains(f5));
		assertFalse(o.getAvailableBooks().contains(f2)); //changed to assert on getAvailableBooks() method not getAllBooks() method
		assertFalse(o.getAvailableBooks().contains(f3));
		
		
	}
	
	@Ignore
	public void testWithdraw() {
		
		/*
		 * What happens when I withdraw book?
		 * 
		 * 	It changes the state of the book 
		 * 	Changes the size of the availablebooks list 
		 * 	User has 1 more book, changes the size of user.getBooks().size();
		 */
		

		o = new Office(ur,br);

		User u = new User("Ben", "password", null);		
		ur = mock(UserRepo.class);
		when(ur.getUserByUserName("Ben")).thenReturn(u);
		
		
		Set<Book> fakeSet = new HashSet<>();
		Book f1 = new Book(0,"Fake1","FAkeAuthor1",true);
		Book f2 = new Book(0,"Fake2","FAkeAuthor2",false);
		Book f3 = new Book(0,"Fake3","FAkeAuthor3",false);
		Book f4 = new Book(0,"Fake4","FAkeAuthor3",true);
		Book f5 = new Book(0,"Fake5","FAkeAuthor4",true);
		
		fakeSet.add(f1);
		fakeSet.add(f2);
		fakeSet.add(f3);
		fakeSet.add(f4);
		fakeSet.add(f5);
		
		Set<Book> userBooks = new HashSet<>();
		userBooks.add(f1);
		userBooks.add(f4);
		userBooks.add(f5);
		
		when(u.getMyBooks()).thenReturn(userBooks);
		
		// Test if Book's isCheckOut property is set to true after being checked out;
		try {
			o.withdraw(u, "Fake2");
		} catch (TooManyBooksAlreadyWithdrawnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BookDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(f2.isCheckedOut());
		
		
		// Test if withdraw() returns false after attempting to
		// checkout a book that is already marked as checked out;
		try {
			assertFalse(o.withdraw(u, "Fake1"));
		} catch (TooManyBooksAlreadyWithdrawnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BookDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert that withdraw() returns false if a user with 3 books out attempts to withdraw another
		try {
			o.withdraw(u, "Fake1");
		} catch (TooManyBooksAlreadyWithdrawnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BookDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(3, fakeSet.size());
		assertEquals(1, u.getMyBooks().size());

	}
	
	@Ignore//(expected = TooManyBooksAlreadyWithdrawnException.class)
	public void testTooManyBooks() {
		
		throw new TooManyBooksAlreadyWithdrawnException();
		
	}
	
	
	@Ignore
	public void testDeposit() {
		
		o = new Office(ur,br);

		User u = new User("Ben", "password", null);		
		ur = mock(UserRepo.class);
		when(ur.getUserByUserName("Ben")).thenReturn(u);
		
		
		Set<Book> fakeSet = new HashSet<>();
		Book f1 = new Book(0,"Fake1","FAkeAuthor1",true);
		Book f2 = new Book(0,"Fake2","FAkeAuthor2",false);
		Book f3 = new Book(0,"Fake3","FAkeAuthor3",false);
		Book f4 = new Book(0,"Fake4","FAkeAuthor3",true);
		Book f5 = new Book(0,"Fake5","FAkeAuthor4",true);
		Book f6 = new Book(0,"Fake6","FAkeAuthor6",true);
		
		fakeSet.add(f1);
		fakeSet.add(f2);
		fakeSet.add(f3);
		fakeSet.add(f4);
		fakeSet.add(f5);
		
		Set<Book> userBooks = new HashSet<>();
		userBooks.add(f1);
		userBooks.add(f4);
		userBooks.add(f5);
		
		when(u.getMyBooks()).thenReturn(userBooks);
		

		// Check that a book is not marked as checked out after depositing it
		o.deposit(u, "Fake1");
		assertFalse(f2.isCheckedOut());
		
		// Check that a book that is not checked out can't be deposited (returns false)
		assertFalse(o.deposit(u, "Fake2"));
		
		// Check that a book a user doesn't have can't be deposited by them (returns false)
		assertFalse(o.deposit(u, "Fake6"));

		
		
	}
}
