package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	
	public Connection connect() 
	{
		Connection con = null;
		
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget","root","");
				
				//For testing
				System.out.print("Successfully Connected");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return con;
	}

}
