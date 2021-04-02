package com.revature.models;

import java.io.Serializable;

public class Book implements Serializable
{
	
	//getters and setters
	//constructor(s)
	//toString()
	
	
	private int id;
	private String name;
	private String author;
	private boolean isCheckedOut;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String name, String author, boolean isCheckedOut) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.isCheckedOut = isCheckedOut;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	public void setCheckedOut(boolean isCheckedOut) {
		this.isCheckedOut = isCheckedOut;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", isCheckedOut=" + isCheckedOut + "]";
	}

	
	
	
	
	

}
