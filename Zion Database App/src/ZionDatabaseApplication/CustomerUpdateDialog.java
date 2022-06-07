package ZionDatabaseApplication;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerUpdateDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField newAddressTextField;

	private CustomerDAO customerDAO;
	
	private CustomerFrame customerSearchApp;
	
	private Customer previousCustomer = null;
	
	public CustomerUpdateDialog(CustomerFrame theCustomerSearchApp, CustomerDAO theCustomerDAO) {
		this();
		customerDAO = theCustomerDAO;
		customerSearchApp = theCustomerSearchApp;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerUpdateDialog dialog = new CustomerUpdateDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerUpdateDialog() {
		setTitle("Update Customer Address");
		setBounds(100, 100, 240, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("First Name");
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			firstNameTextField = new JTextField();
			contentPanel.add(firstNameTextField, "4, 2, fill, default");
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Last Name");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			lastNameTextField = new JTextField();
			contentPanel.add(lastNameTextField, "4, 4, fill, default");
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("New Address");
			contentPanel.add(lblNewLabel_2, "2, 6, right, default");
		}
		{
			newAddressTextField = new JTextField();
			contentPanel.add(newAddressTextField, "4, 6, fill, default");
			newAddressTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Update");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						updateCustomer();
					}

				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Close dialog
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void updateCustomer() {
		
		// Get the customer name from GUI
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String newAddress = newAddressTextField.getText(); 
		
		try {
			// Update the database
			customerDAO.updateCustomerAddress(firstName, lastName, newAddress);
			
			// Close dialog
			setVisible(false);
			dispose();
			
			// Refresh GUI list
			customerSearchApp.refreshCustomersView();
			
			// Show success message
			JOptionPane.showMessageDialog(customerSearchApp,
					"Customer updated successfully.",
					"Customer Updated",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					customerSearchApp,
					"Error updating customer: "
							+ exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
