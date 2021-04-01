package com.revature.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import com.revature.MainDriver;
import com.revature.exceptions.BookDoesNotExistException;
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
		
		this.o = new Office(ur,br);
		
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
	public void testWithdraw()
	{
		
		/*
		 * What happens when I withdraw book?
		 * 
		 * 	It changes the state of the book 
		 * 	Changes the size of the availablebooks list 
		 * 	User has 1 more book, changes the size of user.getBooks().size();
		 */

		this.o = new Office(ur,br);

		User u = new User("Bob", "p4ssw0rd", null);

		ur = mock(UserRepo.class);

		when(ur.getUserByUserName("Bob")).thenReturn(u);

		Book book3 = MainDriver.libraryBooks.get("Harry Potter ");


		try {
			assertTrue( o.withdraw(u,book3.getName()));
		} catch (BookDoesNotExistException e) {
			e.printStackTrace();
		}
		try {
			assertFalse( o.withdraw(u, "fakebook"));
		} catch (BookDoesNotExistException e) {
			e.printStackTrace();
		}
		try {
			assertFalse(o.withdraw(u, "fake book 2"));
		} catch (BookDoesNotExistException e) {
			e.printStackTrace();
		}


	}
	
	@Test(expected = TooManyBooksAlreadyWithdrawnException.class)
	public void testTooManyBooks() throws BookDoesNotExistException {
		this.o = new Office(ur,br);

		User u = new User("Bob", "p4ssw0rd", null);

		ur = mock(UserRepo.class);

		when(ur.getUserByUserName("Bob")).thenReturn(u);

		Book book3 = MainDriver.libraryBooks.get("Harry Potter ");


//		try {
//			assertTrue( o.withdraw(u,book3.getName()));
//		} catch (BookDoesNotExistException e) {
//			e.printStackTrace();
//		}
//		try {
//			assertFalse( o.withdraw(u, "fakebook"));
//		} catch (BookDoesNotExistException e) {
//			e.printStackTrace();
//		}
//		try {
//			assertFalse(o.withdraw(u, "fake book 2"));
//		} catch (BookDoesNotExistException e) {
//			e.printStackTrace();
//		}

		for(Book bk : MainDriver.libraryBooks.values())
		{
			o.withdraw(u, bk.getName());
		}

//		throw new TooManyBooksAlreadyWithdrawnException();


		
	}

	@Test
	public void testDeposit()
	{

		/*
		 * What happens when I withdraw book?
		 *
		 * 	It changes the state of the book
		 * 	Changes the size of the availablebooks list
		 * 	User has 1 more book, changes the size of user.getBooks().size();
		 */

		this.o = new Office(ur,br);
		Book f2 = new Book(0,"Expanse1","FAkeAuthor2",false);

		Set<Book> bkset = new HashSet<>();

		bkset.add(f2);

		User u = new User("Bob", "p4ssw0rd", bkset);


		ur = mock(UserRepo.class);

		when(ur.getUserByUserName("Bob")).thenReturn(u);

		for(Book bk: bkset)
		{

			assertTrue( o.deposit(u,bk.getName()));
		}
	}
}
