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
	
	public void testGetUser() {
		
	}
	
	@Ignore //feature has been depcreated!
	public void testGetAvailableBooks() {
		// create set of available books
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

		// when calling getAllBooks() return fakeset from above
		when(br.getAllBooks()).thenReturn(fakeSet);
		
		//
		assertEquals(2, o.getAvailableBooks());
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
		 * 	User has 1 more book, changes the size of user.getBooks().size();
		 */

		// fake user
		User u = new User("Bob", "p4ssw0rd", null);

		// create books and add to fake set
		Set<Book> fakeSet = new HashSet<>();
		Book f1 = new Book(0,"Fake1","FAkeAuthor1",true);
		Book f2 = new Book(0,"Fake2","FAkeAuthor2",false);
		Book f3 = new Book(0,"Fake3","FAkeAuthor3",false);

		fakeSet.add(f1);
		fakeSet.add(f2);
		fakeSet.add(f3);

		// make bob's book list
		Set<Book> bobBooks = new HashSet<>();
		// create book
		Book f4 = new Book(0,"Fake4","FAkeAuthor3",true);
		// add book to bob's book list
		bobBooks.add(f4);

		// tell mockito to return false when Bob withdraws book
		when(br.getBookByName("Fake1")).thenReturn(f1);

		// user withdraws fake book
		o.withdraw(u, "Fake1");

		// assert true if book exists and can be checked out
		assertTrue(o.withdraw(u, "Fake2"));

		// assert false if book does not exist
		assertFalse(o.withdraw(u, "Fake100"));

		// assert false if book exists but cannot be checked out
		assertFalse(o.withdraw(u, "Fake1"));

		// check if isCheckedOut changes to true after withdrawl
		o.withdraw(u, "Fake3");
		assertTrue(f3.isCheckedOut);
	}

  @Test(expected = TooManyBooksAlreadyWithdrawnException.class)
	
	@Test(expected = TooManyBooksAlreadyWithdrawnException.class)
	public void testTooManyBooks() {

		/*
		 * look at books user already checked out
		 * if > 3 => throw exception
		 */

		throw new TooManyBooksAlreadyWithdrawnException();
		
	}
	
	public void testDeposit() {
		
	}
}
