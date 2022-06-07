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

public class AuthorAddDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField birthDateTextField;

	private AuthorDAO authorDAO;
	
	private AuthorFrame authorSearchApp;
	
	public AuthorAddDialog(AuthorFrame theAuthorSearchApp, AuthorDAO theAuthorDAO) {
		this();
		authorDAO = theAuthorDAO;
		authorSearchApp = theAuthorSearchApp;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AuthorAddDialog dialog = new AuthorAddDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AuthorAddDialog() {
		setTitle("Add Author");
		setBounds(100, 100, 230, 200);
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("ID");
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			idTextField = new JTextField();
			contentPanel.add(idTextField, "4, 2, fill, default");
			idTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("First Name");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			firstNameTextField = new JTextField();
			contentPanel.add(firstNameTextField, "4, 4, fill, default");
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Last Name");
			contentPanel.add(lblNewLabel_2, "2, 6, right, default");
		}
		{
			lastNameTextField = new JTextField();
			contentPanel.add(lastNameTextField, "4, 6, fill, default");
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Birth Date");
			contentPanel.add(lblNewLabel_3, "2, 8, right, default");
		}
		{
			birthDateTextField = new JTextField();
			contentPanel.add(birthDateTextField, "4, 8, fill, default");
			birthDateTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						saveAuthor();
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

	protected void saveAuthor() {
		
		// Get the author info from the GUI
		// Need to get the id as a string 
		// (can only get it from the text field as a string), then convert it into an int
		String idStr = idTextField.getText(); 
		int id = Integer.parseInt(idStr);
		
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String birthDate = birthDateTextField.getText();
		
		Author tempAuthor = new Author(id, lastName, firstName, birthDate);
			
		try {
			// Save to the database
			authorDAO.addAuthor(tempAuthor);
			
			// Close dialog
			setVisible(false);
			dispose();
			
			// Refresh GUI list
			authorSearchApp.refreshAuthorsView();
			
			// Show success message
			JOptionPane.showMessageDialog(authorSearchApp,
					"Author added successfully.",
					"Author Added",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					authorSearchApp,
					"Error saving author: "
							+ exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
