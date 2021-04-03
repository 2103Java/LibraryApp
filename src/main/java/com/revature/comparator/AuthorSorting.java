package com.revature.comparator;

import java.util.Comparator;

import com.revature.models.Book;

public class AuthorSorting implements Comparator<Book>{

	@Override
	public int compare(Book o1, Book o2) {

		return (o1.getAuthor()).compareTo(o2.getAuthor());
	}

}
