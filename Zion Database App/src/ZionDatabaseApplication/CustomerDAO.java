package ZionDatabaseApplication;

import java.io.*;
import java.sql.*;
import java.util.*;

public class CustomerDAO {
	
	private Connection conn;
	
	public CustomerDAO() throws Exception{
		
		// Get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// Connect to database
		conn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	public void updateCustomerAddress(String firstName, String lastName, String newAddress) throws Exception {
		PreparedStatement myStmt = null;
		
		try {
			// prepare statement
			myStmt = conn.prepareStatement("UPDATE Customer"
					+ " SET Address=?"
					+ " WHERE FirstName=? AND LastName=?");
			
			myStmt.setString(1, newAddress);
			myStmt.setString(2, firstName);
			myStmt.setString(3, lastName);
			
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	private Customer convertRowToCustomer(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("ID");
		int zip = myRs.getInt("Zip");
		String lastName = myRs.getString("LastName");
		String firstName = myRs.getString("FirstName");
		String address = myRs.getString("Address");
		String city = myRs.getString("City");
		String state = myRs.getString("State");
		
		Customer tempCustomer = new Customer(id, zip, lastName, firstName, address, city, state);
		
		return tempCustomer;
	}
	
	private static void close(Connection conn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (conn != null) {
			conn.close();
		}
	}
		
	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
			close(null, myStmt, myRs);		
		}

	private void close(Statement myStmt) throws SQLException {
			close(null, myStmt, null);		
		}
	
	public List<Customer> getAllCustomers() throws Exception {
		List<Customer> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM Customer");
			
			while (myRs.next()) {
				Customer tempCustomer = convertRowToCustomer(myRs);
				list.add(tempCustomer);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Customer> searchCustomers(String lastName) throws Exception {
		List<Customer> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			lastName += "%";
			myStmt = conn.prepareStatement("SELECT * FROM Customer WHERE LastName LIKE ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				Customer tempCustomer = convertRowToCustomer(myRs);
				list.add(tempCustomer);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		CustomerDAO dao = new CustomerDAO();
		System.out.println(dao.searchCustomers("Halloway"));
		System.out.println(dao.getAllCustomers());
	}
	
}