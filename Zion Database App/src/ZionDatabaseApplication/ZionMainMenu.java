package ZionDatabaseApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class ZionMainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ZionMainMenu frame = new ZionMainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ZionMainMenu() {
		setTitle("Zion Database Menu");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Database Control");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add Author");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show the author window
				AuthorFrame frame = new AuthorFrame();
				frame.setVisible(true);
				
				// Populate the author table
				frame.refreshAuthorsView();
				
				// Close the main menu
				setVisible(false);
				dispose();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Report Tables");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Author Report");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show the author report window
				AuthorReportFrame frame = new AuthorReportFrame();
				frame.setVisible(true);
				
				// Populate the author report table
				frame.refreshAuthorReportsView();
				
				// Close the main menu
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Log Out");
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show the login window
				UserLoginDialog frame = new UserLoginDialog();
				frame.setVisible(true);
				
				// Close the main menu window
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Update Customer");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show the customer window
				CustomerFrame frame = new CustomerFrame();
				frame.setVisible(true);
				
				// Populate the author table
				frame.refreshCustomersView();
				
				// Close the main menu
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Book Report");
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show the book report window
				BookReportFrame frame = new BookReportFrame();
				frame.setVisible(true);
				
				// Populate the author report table
				frame.refreshBookReportsView();
				
				// Close the main menu
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnNewButton_3);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut_1);
		contentPane.add(btnNewButton_4);
	}

}
