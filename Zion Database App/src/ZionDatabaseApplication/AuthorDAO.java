package ZionDatabaseApplication;

import java.io.*;
import java.sql.*;
import java.util.*;

public class AuthorDAO {
	
	private Connection conn;
	
	public AuthorDAO() throws Exception{
		
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
	
	public void addAuthor(Author theAuthor) throws Exception {
		PreparedStatement myStmt = null;
		
		try {
			// Prepare statement
			myStmt = conn.prepareStatement("INSERT INTO author" 
					+ " (ID, LastName, FirstName, BirthDate)"
					+ " values (?, ?, ?, ?)");
			
			// Set parameters
			myStmt.setInt(1, theAuthor.getId());
			myStmt.setString(2, theAuthor.getLastName());
			myStmt.setString(3, theAuthor.getFirstName());
			myStmt.setString(4, theAuthor.getBirthDate());
			
			// Execute SQL
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	private Author convertRowToAuthor(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("ID");
		String lastName = myRs.getString("LastName");
		String firstName = myRs.getString("FirstName");
		String birthDate = myRs.getString("BirthDate");
		
		Author tempAuthor = new Author(id, lastName, firstName, birthDate);
		
		return tempAuthor;
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
	
	public List<Author> getAllAuthors() throws Exception {
		List<Author> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM Author");
			
			while (myRs.next()) {
				Author tempAuthor = convertRowToAuthor(myRs);
				list.add(tempAuthor);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Author> searchAuthors(String lastName) throws Exception {
		List<Author> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			lastName += "%";
			myStmt = conn.prepareStatement("SELECT * FROM Author WHERE LastName LIKE ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				Author tempAuthor = convertRowToAuthor(myRs);
				list.add(tempAuthor);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		AuthorDAO dao = new AuthorDAO();
		System.out.println(dao.searchAuthors("Ta"));
		System.out.println(dao.getAllAuthors());
	}
	
}