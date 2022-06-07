package ZionDatabaseApplication;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.sql.*;

public class UserLoginDialog extends JDialog {

	private Connection conn;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField userNameTextField;
	private JPasswordField passwordTextField;
	
	private AuthorDAO authorDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UserLoginDialog dialog = new UserLoginDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UserLoginDialog() {
		setTitle("Manager Login");
		setBounds(100, 100, 225, 150);
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("Username");
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			userNameTextField = new JTextField();
			contentPanel.add(userNameTextField, "4, 2, fill, default");
			userNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Password");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			passwordTextField = new JPasswordField();
			contentPanel.add(passwordTextField, "4, 4, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Login");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e){
						
						try {
							// Get db properties
							Properties props = new Properties();
							props.load(new FileInputStream("demo.properties"));
						
							String user = props.getProperty("user");
							String password = props.getProperty("password");
							String dburl = props.getProperty("dburl");
						
							// Connect to database
							conn = DriverManager.getConnection(dburl, user, password);
						
							System.out.println("DB connection successful to: " + dburl);
						
							// Get the login info from the GUI
							String userName = userNameTextField.getText();
							char[] passwordChar = passwordTextField.getPassword();
							String userPassword = new String(passwordChar);
						
							// Create query
							String query = "SELECT * FROM login WHERE Username=? AND Password=?";
							PreparedStatement statement = conn.prepareStatement(query);
							statement.setString(1, userName);
							statement.setString(2, userPassword);
						
							ResultSet set = statement.executeQuery();
								if(set.next())
								{
									// Show the main menu window
									ZionMainMenu frame = new ZionMainMenu();
									frame.setVisible(true);
							
									// Close the login dialog
									setVisible(false);
									dispose();
								} else {
									JOptionPane.showMessageDialog(
											UserLoginDialog.this,
											"Invalid login.", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							} catch (SQLException | IOException query) {
								query.printStackTrace();
							}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Exit");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private static void close(Connection conn)
			throws SQLException {
		
		if (conn != null) {
			conn.close();
		}
	}
}
