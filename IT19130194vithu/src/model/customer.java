package model;

import java.sql.*;
public class customer
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String customerdetails(String name, String phone, String gender, String email)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into customerdetails (`cID`,`cname`,`cphone`,`cgender`,`cemail`)"  + " values (?, ?, ?, ?, ?)"; ;
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, name);
 preparedStmt.setString(3, phone);
 preparedStmt.setString(4, gender);
 preparedStmt.setString(5, email);
// execute the statement

 preparedStmt.execute();
 con.close();
 String newData = readcustomerdetails();
 output = "{\"status\":\"success\", \"data\": \"" +newData + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Data.\"}";
 System.err.println(e.getMessage());
 } 
 return output;
 }
public String readcustomerdetails()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
//Prepare the html table to be displayed
output = "<table border=\"1\"><tr><th>Customer Name</th><th>Phone</th><th>Gender</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
String query = "select * from customerdetails";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String cID = Integer.toString(rs.getInt("cID"));
String cname = rs.getString("cname");
String cphone = rs.getString("cphone");
String cgender = rs.getString("cgender");
String cemail = rs.getString("cemail");

// Add into the html table
output += "<tr><td>"+ cname + "</td>";
output += "<td>" + cphone + "</td>";
output += "<td>" + cgender + "</td>";
output += "<td>" + cemail + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-cid='" + cID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-cid='" + cID + "'></td></tr>";
} 
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the Details.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updatecustomerdeatils(String cID, String name, String phone, String gender, String email)

 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for updating."; }
 // create a prepared statement
 String query = "UPDATE customerdetails SET cname=?,cphone=?,cgender=?,cemail=?WHERE cID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setString(1, name);
 preparedStmt.setString(2, phone);
 preparedStmt.setString(3, gender);
 preparedStmt.setString(4, email);
 preparedStmt.setInt(5, Integer.parseInt(cID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newData = readcustomerdetails();
 output = "{\"status\":\"success\", \"data\": \"" +newData + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while updating the Data.\"}";
 System.err.println(e.getMessage());
 } 
 return output;
 }
public String deletecustomerdetails(String cID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from customerdetails where cID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(cID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newData = readcustomerdetails();
 output = "{\"status\":\"success\", \"data\": \"" +newData + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while deleting the Data.\"}";
 System.err.println(e.getMessage());
 } 
 return output;
 }
} 