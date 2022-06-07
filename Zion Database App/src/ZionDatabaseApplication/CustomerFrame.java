package ZionDatabaseApplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JTable table;
	
	private CustomerDAO customerDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
					frame.setVisible(true);
					
					// Populate the author table
					frame.refreshCustomersView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerFrame() {
		
		// Create the DAO
		try {
			customerDAO = new CustomerDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("Zion Customer Table");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Enter Last Name");
		panel.add(lblNewLabel);
		
		lastNameTextField = new JTextField();
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String lastName = lastNameTextField.getText();
					
					List<Customer> customers = null;
					
					if (lastName != null && lastName.trim().length() > 0) {
						customers = customerDAO.searchCustomers(lastName);
					} else {
						customers = customerDAO.getAllCustomers();
					}
					
					// Create the model and update the table
					CustomerTableModel model = new CustomerTableModel(customers);
					
					table.setModel(model);
					
					/*
					for (Customer temp : customers) {
						System.out.println(temp);
					}
					*/
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(CustomerFrame.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Update Customer");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create dialog
				CustomerUpdateDialog dialog = new CustomerUpdateDialog(CustomerFrame.this, customerDAO);
				
				// Show dialog
				dialog.setVisible(true);
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show the main menu window
				ZionMainMenu frame = new ZionMainMenu();
				frame.setVisible(true);
				
				// Close the login dialog
				setVisible(false);
				dispose();
			}
		});
		panel_1.add(btnNewButton_2);
	}
	
	public void refreshCustomersView() {
		
		try {
			List<Customer> customers = customerDAO.getAllCustomers();
			
			// Create the model and update the "table"
			CustomerTableModel model = new CustomerTableModel(customers);
			
			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
						JOptionPane.ERROR_MESSAGE);
		}
		
	}
	

}
