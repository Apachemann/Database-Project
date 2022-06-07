package ZionDatabaseApplication;

import java.io.*;
import java.sql.*;
import java.util.*;

public class AuthorReportDAO {
	
	private Connection conn;
	
	public AuthorReportDAO() throws Exception{
		
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
	
	private AuthorReport convertRowToAuthorReport(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("ID");
		String lastName = myRs.getString("LastName");
		String firstName = myRs.getString("FirstName");
		int totalNumberOfBooks = myRs.getInt("TotalNumberOfBooks");
		
		AuthorReport tempAuthorReport = new AuthorReport(id, lastName, firstName, totalNumberOfBooks);
		
		return tempAuthorReport;
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
	
	public List<AuthorReport> getAllAuthorReports() throws Exception {
		List<AuthorReport> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("SELECT ID, FirstName, LastName, COUNT(bookauthor.AuthorID) AS TotalNumberOfBooks"
					+ " FROM author"
					+ " LEFT JOIN bookauthor"
					+ " ON ID = AuthorID"
					+ " GROUP BY ID");
			
			while (myRs.next()) {
				AuthorReport tempAuthorReport = convertRowToAuthorReport(myRs);
				list.add(tempAuthorReport);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		AuthorReportDAO dao = new AuthorReportDAO();
		System.out.println(dao.getAllAuthorReports());
	}
	
}