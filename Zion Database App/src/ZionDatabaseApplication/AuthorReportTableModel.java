package ZionDatabaseApplication;

import java.util.List;
import javax.swing.table.AbstractTableModel;

class AuthorReportTableModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int TOTAL_NUMBER_OF_BOOKS_COL = 3;
	
	
	private String[] columnNames = { "ID", "First Name", "Last Name",
			"Book Quantity" };
	private List<AuthorReport> authorReports;

	public AuthorReportTableModel(List<AuthorReport> theAuthorReports) {
		authorReports = theAuthorReports;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return authorReports.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		AuthorReport tempAuthorReport = authorReports.get(row);

		switch (col) {
		case ID_COL:
			return tempAuthorReport.getId();
		case LAST_NAME_COL:
			return tempAuthorReport.getLastName();
		case FIRST_NAME_COL:
			return tempAuthorReport.getFirstName();
		case TOTAL_NUMBER_OF_BOOKS_COL:
			return tempAuthorReport.getTotalNumberOfBooks();
		default:
			return tempAuthorReport.getLastName();
		}
	}
}