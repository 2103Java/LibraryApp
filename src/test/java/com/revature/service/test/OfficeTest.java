package com.revature.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import com.revature.exceptions.BookDoesNotExistException;
import com.revature.service.ServiceLayer;
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

	@Mock
	private ServiceLayer sl;

	@Mock
	private Book bo;

	private Office o;

	
	@Ignore
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
		User u = new User("Bob", "p4ssw0rd", null);
		User nu = new User("f", "pass", null);

		o = new Office(ur,br);

		when(sl.getUser("Bob")).thenReturn(u);
		assertEquals(u, o.getUser("Bob"));

		assertEquals(nu, o.getUser("Bob"));
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

		// boolean withdraw(User loggedInUser, String chosenBook) throws BookDoesNotExistException, TooManyBooksAlreadyWithdrawnException;

		sl = mock(ServiceLayer.class);
		bo = mock(Book.class);

		User u = new User("John", "secretshh", null);

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

		try {
			assertTrue(f1.isCheckedOut());
			assertFalse(f2.isCheckedOut());

			when(sl.withdraw(u, f2.getName())).thenReturn(true);
			when(sl.withdraw(u, f2.getName())).thenReturn(false);

			assertTrue(f2.isCheckedOut()); // Makes sure that when f2 is added to a user it's is checked out is true
			assertEquals(1, fakeSet.size()); // Checks that the  of the fakeSet has

		} catch(BookDoesNotExistException e) {
			e.printStackTrace();
		} catch(TooManyBooksAlreadyWithdrawnException e) {
			e.printStackTrace();
		}



		
	}
	
	@Test(expected = TooManyBooksAlreadyWithdrawnException.class)
	public void testTooManyBooks() {
		
		throw new TooManyBooksAlreadyWithdrawnException();
		
	}
	
	public void testDeposit() {
		//void deposit(User loggedInUser, String depositBook);
		Set<Book> fakeSet = new HashSet<>();
		Book f1 = new Book(0,"Fake1","FAkeAuthor1",true);
		Book f4 = new Book(0,"Fake4","FAkeAuthor3",true);
		Book f5 = new Book(0,"Fake5","FAkeAuthor4",true);

		fakeSet.add(f1);
		fakeSet.add(f4);
		fakeSet.add(f5);

		User u = new User("Bob", "pass", fakeSet);

		assertEquals(3, u.getMyBooks().size());

		o = new Office(ur,br);

		assertEquals(0, o.getAvailableBooks().size()); // Checking that the office has no books.

		o.deposit(u, f1.getName());

		assertEquals(2, u.getMyBooks().size()); // Checking if the user has lost a book.
		assertEquals(1, o.getAvailableBooks().size()); // Checking if the office has gained a book.

	}
}
