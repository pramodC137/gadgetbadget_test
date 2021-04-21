package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DB_Connection;

public class User {
	
	public String insertUser(String code, String username, String pwd, String email, String role, String fname, String lname, String address, String bod)
	{
		String output = "";
		
		try
		{	
			DB_Connection obj_DB_Connection= new DB_Connection();
			Connection con = obj_DB_Connection.connect();
			if (con==null)
			{
				return "Error while connecting to the database  for inserting.!";
			}
			
			// Create a prepared statement 'itemID', ?,
			String query =  " insert into users(`userID`,`userCode`,`username`,`userPwd`,`userEmail`, `userRole`, `userFname`, `userLname`, `userAddress`, `userBod`, `joinDate` )"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//blinding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, username);
			preparedStmt.setString(4, pwd);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, role);
			preparedStmt.setString(7, fname);
			preparedStmt.setString(8, lname);
			preparedStmt.setString(9, address);
			preparedStmt.setString(10, bod);
			preparedStmt.setInt(11, 0);
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted Successfully";
		}
		catch(Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String readUsers()
	{
		String output = "";

		try
		{
			DB_Connection obj_DB_Connection= new DB_Connection();
			Connection con = obj_DB_Connection.connect();
			if(con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User Code</th>"
				+ "<th>username</th>"
				+ "<th>Password</th>"
				+ "<th>Email</th>"
				+ "<th>Role</th>"
				+ "<th>First Name</th>"
				+ "<th>Last Name</th>"
				+ "<th>Address</th>"
				+ "<th>Birthday</th>"
				+ "<th>Join Date</th>"
				+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from users";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while(rs.next())
			{
				String userID = Integer.toString(rs.getInt("userID"));
				String userCode = rs.getString("userCode");
				String username = rs.getString("username");
				String userPwd = rs.getString("userPwd");
				String userEmail = rs.getString("userEmail");
				String userRole = rs.getString("userRole");
				String userFname = rs.getString("userFname");
				String userLname = rs.getString("userLname");
				String userAddress = rs.getString("userAddress");
				String userBod = rs.getString("userBod");
				String joinDate = rs.getString("joinDate");
				
				
				// Add a row into the html table
				output += "<tr><td>" + userCode + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + userPwd + "</td>";
				output += "<td>" + userEmail + "</td>";
				output += "<td>" + userRole + "</td>";
				output += "<td>" + userFname + "</td>";
				output += "<td>" + userLname + "</td>";
				output += "<td>" + userAddress + "</td>";
				output += "<td>" + userBod + "</td>";
				output += "<td>" + joinDate + "</td>";
				
				// Buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								 + "<td><form method='post' action='items.jsp'>"
								 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
								 + "<input name='itemID' type='hidden' value='" + userID
								 + "'>" + "</form></td></tr>"; 
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
		}
		catch(Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String updateItem(String ID, String code, String name, String price, String desc)
	{
		String output = "";
		
		try
		{
			DB_Connection obj_DB_Connection= new DB_Connection();
			Connection con = obj_DB_Connection.connect();
			
			if(con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE itemsk SET itemCode=?, itemName=?, itemPrice=?, itemDesc=? WHERE itemID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Updated successfully ";
			
		}
		catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String deleteItem(String itemID)
	{
		String output = "";
		
		try 
		{
			DB_Connection obj_DB_Connection= new DB_Connection();
			Connection con = obj_DB_Connection.connect();
			if(con == null) {
				return"Error while connecting to the databae for deleting.";
			}
			
			//Create a prepared statement
			String query = "delete from itemsk where itemID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding the statement
			preparedStmt.setInt(1, Integer.parseInt(itemID));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Delete Successfully";	
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
	
		return output;
	}

}
