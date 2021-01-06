package myjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class doConnection {
	static Connection doConnect()//??????/why seprate
	{
		Connection con=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			 //con=DriverManager.getConnection("http://localhost/phpmyadmin/learndb","root","");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost/learndb","root","");//syntax**
			
			//trycatch here as if con not initialsed then error--agar catch chaal con uniniyalsed and so initialise upar and to make its scope , so that can be returned, declare it bahar in fxn
			System.out.println("connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return con;//cant return if write in try as scope
		
	}
	public static void main(String args[])
	{

		doConnect();
	
	}
}
