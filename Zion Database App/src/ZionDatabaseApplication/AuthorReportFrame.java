package ZionDatabaseApplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthorReportFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private AuthorReportDAO authorReportDAO;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AuthorReportFrame frame = new AuthorReportFrame();
					frame.setVisible(true);
					
					// Populate the author report table
					frame.refreshAuthorReportsView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AuthorReportFrame() {
		// Create the DAO
		try {
			authorReportDAO = new AuthorReportDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("Zion Author Report Table");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show the main menu window
				ZionMainMenu frame = new ZionMainMenu();
				frame.setVisible(true);
				
				// Close the report table
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}
	
	public void refreshAuthorReportsView() {
		
		try {
			List<AuthorReport> authorReports = authorReportDAO.getAllAuthorReports();
			
			// Create the model and update the "table"
			AuthorReportTableModel model = new AuthorReportTableModel(authorReports);
			
			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
						JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
