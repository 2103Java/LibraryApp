package com.revature.utility;


public class Connection {
	
	public static Connection conn;
	
	 public Connection() {
		 
	 }
	 
	 public void connect() {
		 System.out.println("Look at me, I'm connecting!");
	 }
	 
	 static public Connection getConnection() {
		 
		 if(conn == null) {
			 conn = new Connection();
			 return conn;
		 }else {
			 return conn;
		 }
	 }

}
