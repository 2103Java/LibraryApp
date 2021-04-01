package com.revature.presentation;

import java.util.Scanner;
import java.util.Set;

import com.revature.exceptions.BookDoesNotExistException;
import com.revature.models.Book;
import com.revature.models.User;
import com.revature.service.ServiceLayer;

public class FrontOffice implements PresentationLayer {
	
	ServiceLayer sLayer;
	
	public void displayMenu() {
		
		System.out.println("Welcome to my Library!");
		
		System.out.println("Once you login, you can have access to my library!");
		
		Scanner sc = new Scanner(System.in);
		
		boolean using = true;
		
		while(using) {
			
			System.out.println("Input username");
			String username = sc.nextLine();
			
			System.out.println("Input password");
			String password = sc.nextLine();
			
			
			
			if(sLayer.login(username,password)) {
				
				User loggedInUser = sLayer.getUser(username);
				
				boolean logged = true;
				
				while(logged) {
					
					
					System.out.println("Welcome " + loggedInUser.getUsername() + "!" );
					System.out.println("Here are your options:");
					System.out.println("[1] See what books you have on your account!");
					System.out.println("[2] See what books are available from the library !");
					System.out.println("[3] Withdraw a book!");
					System.out.println("[4] Deposit a book!");
					System.out.println("[0] Logout!");
					
					
					int option = -1;
					
					try {
						 option = Integer.parseInt(sc.nextLine());
						
					} catch(NumberFormatException e) {
//						e.printStackTrace();
						System.out.println("You put in an invalid character, try again!");
					} catch(Exception e) {
						e.printStackTrace();
						System.out.println("I'm not sure what happened, but something bad happened!");
					} 
					
					
					switch(option) {
					case 1: // displaying user's books
						
						displayBooks(loggedInUser.getMyBooks());
						break;
						
					case 2: //displayed library books
						
						Set<Book> libraryBooks = sLayer.getAvailableBooks();
						
						displayBooks(libraryBooks);
						
						break;
						
					case 3: //withdrawing the books!
						
						System.out.println("Which book would you like to withdraw?");
						
						displayBooks(sLayer.getAvailableBooks());
						
						String chosenBook = sc.nextLine();
						
						try {
							sLayer.withdraw(loggedInUser, chosenBook);
						} catch(BookDoesNotExistException e) {
							System.out.println("Book does not exist!");
						}
						break;
						
					case 4: //depoisting books!
						
						displayBooks(loggedInUser.getMyBooks());
						
						System.out.println("Which book would you like to deposit?");
						
						String depositBook = sc.nextLine();
						
						sLayer.deposit(loggedInUser,depositBook);
						
						System.out.println();
						break;
						
					case 0: //logout method!
						
						System.out.println("Thank you for your service!");
						logged = false;
						break;
						
					default: 
						System.out.println("You should have picked something!");
						
						
					}
					
				}
	
			}else {
				
				//failed login method!
				
				System.out.println("You credentials aren't correct!");
				System.out.println("Woould you like to try again!");
				System.out.println("Input 'Yes' or 'No'");
				
				String tryAgain = sc.nextLine();
				
				tryAgain.toUpperCase();
				
				switch(tryAgain) {
				case "YES":
					break;
				case "NO":
					using = false;
					break;
				default:
					System.out.println("Sucks to be you, try to login again!");
					break;
					
				}
				
			}
			
			
		}
		
		
		
		
	}

	public void displayBooks(Set<Book> bookList) {
		
		if(bookList.size() != 0) {
			
			for(Book b:bookList) {
				System.out.println("Name: " + b.getName() + "|"
						+ "Author:" + b.getAuthor());
				
			}
			
		}else {
			System.out.println("You have no books!");
		}
		
	}
}
