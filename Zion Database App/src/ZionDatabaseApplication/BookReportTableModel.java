package ZionDatabaseApplication;

import java.util.List;
import javax.swing.table.AbstractTableModel;

class BookReportTableModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int TITLE_COL = 1;
	private static final int QUANTITY_COL = 2;
	private static final int TOTAL_SALES_COL = 3;
	
	
	private String[] columnNames = { "ID", "Title", "Quantity",
			"Total Sales" };
	private List<BookReport> bookReports;

	public BookReportTableModel(List<BookReport> theBookReports) {
		bookReports = theBookReports;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return bookReports.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		BookReport tempBookReport = bookReports.get(row);

		switch (col) {
		case ID_COL:
			return tempBookReport.getId();
		case TITLE_COL:
			return tempBookReport.getTitle();
		case QUANTITY_COL:
			return tempBookReport.getQuantity();
		case TOTAL_SALES_COL:
			return tempBookReport.getTotalSales();
		default:
			return tempBookReport.getTitle();
		}
	}
}