package com.revature.utility;

public class Calculator {
	
	final static double pi = 3.14;
	
	private  String name;
	private double age;
	private double power;
	
//	public Calculator() {
//		super(); //references the parent constructor!
//	}
	
	public void sayHello() {
		
	}
	
	public Calculator() {
		this("No name",0,0);
		

		
		this.sayHello();
		
		
	}
	
	public Calculator(double number, double anotherNumber) {
		this("No name",number,anotherNumber);
		
	}

	public Calculator(String name, double age, double power) {
		super();
		this.name = name;
		this.age = age;
		this.power = power;
	}
	
	

}
