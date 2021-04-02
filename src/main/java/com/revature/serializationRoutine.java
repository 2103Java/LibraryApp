package com.revature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class serializationRoutine implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5268097770127853895L;

	public void serialize(Object o) {
		
		try {
	         FileOutputStream fileOut = new FileOutputStream("./" + o.toString());
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(o);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in ?");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public void serializeList(List<Object> lo) {
		
		try {
	         FileOutputStream fileOut = new FileOutputStream("./Objects.txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(lo);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in ?");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public List<Object> deserialize(String filename) {
		
		  List<Object> e = null;
	      try {
	         FileInputStream fileIn = new FileInputStream(filename);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (List<Object>) in.readObject();
	         in.close();
	         fileIn.close();
	         return e;
	      } catch (IOException i) {
	         i.printStackTrace();
	         return null;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return null;
	      }
	}
}
