package ZionDatabaseApplication;

import java.util.List;
import javax.swing.table.AbstractTableModel;

class AuthorTableModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int BIRTH_DATE_COL = 3;
	

	private String[] columnNames = { "ID", "First Name", "Last Name",
			"Birth Date" };
	private List<Author> authors;

	public AuthorTableModel(List<Author> theAuthors) {
		authors = theAuthors;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return authors.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Author tempAuthor = authors.get(row);

		switch (col) {
		case ID_COL:
			return tempAuthor.getId();
		case LAST_NAME_COL:
			return tempAuthor.getLastName();
		case FIRST_NAME_COL:
			return tempAuthor.getFirstName();
		case BIRTH_DATE_COL:
			return tempAuthor.getBirthDate();
		default:
			return tempAuthor.getLastName();
		}
	}
}