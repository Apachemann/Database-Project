package ZionDatabaseApplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.util.List;

public class AuthorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JTable table;
	
	private AuthorDAO authorDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AuthorFrame frame = new AuthorFrame();
					frame.setVisible(true);
					
					// Populate the author table
					frame.refreshAuthorsView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AuthorFrame() {
		
		// Create the DAO
		try {
			authorDAO = new AuthorDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("Zion Author Table");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
					
					List<Author> authors = null;
					
					if (lastName != null && lastName.trim().length() > 0) {
						authors = authorDAO.searchAuthors(lastName);
					} else {
						authors = authorDAO.getAllAuthors();
					}
					
					// Create the model and update the table
					AuthorTableModel model = new AuthorTableModel(authors);
					
					table.setModel(model);
					
					/*
					for (Author temp : authors) {
						System.out.println(temp);
					}
					*/
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(AuthorFrame.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
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
		
		JButton btnNewButton_1 = new JButton("Add Author");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create dialog
				AuthorAddDialog dialog = new AuthorAddDialog(AuthorFrame.this, authorDAO);
				
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

	public void refreshAuthorsView() {
		
		try {
			List<Author> authors = authorDAO.getAllAuthors();
			
			// Create the model and update the "table"
			AuthorTableModel model = new AuthorTableModel(authors);
			
			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
						JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
