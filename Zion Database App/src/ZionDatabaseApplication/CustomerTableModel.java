package ZionDatabaseApplication;

import java.util.List;
import javax.swing.table.AbstractTableModel;

class CustomerTableModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int ADDRESS_COL = 3;
	private static final int CITY_COL = 4;
	private static final int STATE_COL = 5;
	private static final int ZIP_COL = 6;
	
	private String[] columnNames = { "ID", "First Name", "Last Name",
			"Address", "City", "State", "Zip" };
	private List<Customer> customers;

	public CustomerTableModel(List<Customer> theCustomers) {
		customers = theCustomers;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Customer tempCustomer = customers.get(row);

		switch (col) {
		case ID_COL:
			return tempCustomer.getId();
		case LAST_NAME_COL:
			return tempCustomer.getLastName();
		case FIRST_NAME_COL:
			return tempCustomer.getFirstName();
		case ADDRESS_COL:
			return tempCustomer.getAddress();
		case CITY_COL:
			return tempCustomer.getCity();
		case STATE_COL:
			return tempCustomer.getState();
		case ZIP_COL:
			return tempCustomer.getZip();
		default:
			return tempCustomer.getLastName();
		}
	}
}