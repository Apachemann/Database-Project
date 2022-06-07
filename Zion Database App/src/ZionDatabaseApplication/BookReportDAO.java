package ZionDatabaseApplication;

import java.io.*;
import java.sql.*;
import java.util.*;

public class BookReportDAO {
	
	private Connection conn;
	
	public BookReportDAO() throws Exception{
		
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
	
	private BookReport convertRowToBookReport(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("ID");
		String title = myRs.getString("Title");
		int quantity = myRs.getInt("Quantity");
		float totalSales = myRs.getFloat("TotalSales");
		
		BookReport tempBookReport = new BookReport(id, title, quantity, totalSales);
		
		return tempBookReport;
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
	
	public List<BookReport> getAllBookReports() throws Exception {
		List<BookReport> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery("SELECT book.ID, book.Title, SUM(sale.Quantity) AS Quantity, SUM(sale.UnitPrice * sale.Quantity) AS TotalSales"
					+ " FROM book"
					+ " LEFT JOIN sale"
					+ " ON book.ID = BookID"
					+ " GROUP BY book.ID"
					+ " ORDER BY book.ID");
			
			while (myRs.next()) {
				BookReport tempBookReport = convertRowToBookReport(myRs);
				list.add(tempBookReport);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BookReportDAO dao = new BookReportDAO();
		System.out.println(dao.getAllBookReports());
	}
	
}