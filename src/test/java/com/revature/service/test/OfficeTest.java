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

		o = new Office(ur,br);

		User u = new User("Bob", "p4ssw0rd", null);

		ur = mock(UserRepo.class);
		
		when(ur.getUserByUserName("Bob")).thenReturn(u);
		
		
		assertTrue( o.login("Bob", "p4ssw0rd"));
		assertFalse( o.login("Bob", "password"));
		assertFalse(o.login("Fakey fake", "p4ssw0rd"));
		
		
	}
	@Test
	public void testGetUser() {
		//Creating Office for testing
		o = new Office(ur,br);
		//Creating users for testing
		User u = new User("bill", "Pass", null);
		User u2 = new User("Tom", "Bo", null);
		//Testing that existing users return true
		assertEquals(u, o.getUser("bill"));
		//Testing that non existing users return false
		assertNotEquals(u2, o.getUser("Sausage"));

	}
	
	@Ignore //feature has been depcreated!
	public void testGetAvailableBooks() {
		
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
		
		
		assertEquals(3, o.getAvailableBooks());
		assertTrue(br.getAllBooks().contains(f1));
		assertTrue(br.getAllBooks().contains(f4));
		assertTrue(br.getAllBooks().contains(f5));
		assertFalse(br.getAllBooks().contains(f2));
		assertFalse(br.getAllBooks().contains(f3));
		
		
	}
	
	@Test
	public void testWithdraw() {
		
		/*
		 * What happens when I withdraw book?
		 * 
		 * 	It changes the state of the book 
		 * 	Changes the size of the availablebooks list 
		 * 	User has 1 more book, changes the size of user.getBooks().size();
		 */
		//creating an office for testing
		o = new Office(ur, br);
		//creating books to place in set
		Book f1 = new Book(0,"Fake1","FAkeAuthor1",true);
		Book f2 = new Book(0,"Fake2","FAkeAuthor2",true);
		Book f3 = new Book(0,"Fake3","FAkeAuthor1",false);
		Book f4 = new Book(0,"Fake4","FAkeAuthor1",false);
		Book f5 = new Book(0,"Fake5","FAkeAuthor1",false);
		//Creating Set of books that user has checked out
		Set<Book> fakeSetOBooks = new HashSet<>();
		fakeSetOBooks.add(f1);
		fakeSetOBooks.add(f2);
		//Creating user with set of checked out books
		User u = new User("bill", "Pass", fakeSetOBooks);
		//Creating Set for library
		Set<Book> fakeLibrary = new HashSet<>();
		fakeLibrary.add(f1);
		fakeLibrary.add(f2);
		fakeLibrary.add(f3);
		fakeLibrary.add(f4);
		fakeLibrary.add(f5);

		/*
		These tests assume o.withdraw() changes the library and the user's book set size. Along with the books "isCheckedOut" boolean.
		 */

		assertTrue(o.withdraw(u, "Fake3"));
		assertFalse(o.withdraw(u,"Fake1"));
		assertTrue(f3.isCheckedOut);
		assertEquals(3, u.getMyBooks().size());
		assertEquals(2, o.getAvailableBooks().size());
	}
	
	@Test(expected = TooManyBooksAlreadyWithdrawnException.class)
	public void testTooManyBooks() {
		
		throw new TooManyBooksAlreadyWithdrawnException();
		
	}
	
	public void testDeposit() {
		//creating an office for testing
		o = new Office(ur, br);
		//creating books to place in set
		Book f1 = new Book(0,"Fake1","FAkeAuthor1",true);
		Book f2 = new Book(0,"Fake2","FAkeAuthor2",true);
		Book f3 = new Book(0,"Fake3","FAkeAuthor1",false);
		Book f4 = new Book(0,"Fake4","FAkeAuthor1",false);
		Book f5 = new Book(0,"Fake5","FAkeAuthor1",false);
		//Creating Set of books that user has checked out
		Set<Book> fakeSetOBooks = new HashSet<>();
		fakeSetOBooks.add(f1);
		fakeSetOBooks.add(f2);
		//Creating user with set of checked out books
		User u = new User("bill", "Pass", fakeSetOBooks);
		//Creating Set for library
		Set<Book> fakeLibrary = new HashSet<>();
		fakeLibrary.add(f1);
		fakeLibrary.add(f2);
		fakeLibrary.add(f3);
		fakeLibrary.add(f4);
		fakeLibrary.add(f5);

		/*
		These tests assume o.deposit() changes the library and the user's book set size. Along with the books "isCheckedOut" boolean.
		 */

		assertFalse(o.deposit(u, "Fake3"));
		assertTrue(o.deposit(u,"Fake1"));
		assertFalse(f3.isCheckedOut);
		assertEquals(1, u.getMyBooks().size());
		assertEquals(4, o.getAvailableBooks().size());
	}
}
