import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Register {

	private JFrame frmRegister;
	private JTextField txtName;
	private JTextField txtFamily;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtRetypePassword;

	/**
	 * Launch the application.
	 */
	public static void enter() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.setTitle("Register Form");
		frmRegister.setBounds(100, 100, 273, 256);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 30, 96, 14);
		frmRegister.getContentPane().add(lblName);

		JLabel lblFamily = new JLabel("Family");
		lblFamily.setBounds(10, 61, 96, 14);
		frmRegister.getContentPane().add(lblFamily);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 92, 96, 14);
		frmRegister.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 123, 96, 14);
		frmRegister.getContentPane().add(lblPassword);

		JLabel lblRetypePassword = new JLabel("Retype Password");
		lblRetypePassword.setBounds(10, 154, 96, 14);
		frmRegister.getContentPane().add(lblRetypePassword);

		txtName = new JTextField();
		txtName.setBounds(134, 27, 86, 20);
		frmRegister.getContentPane().add(txtName);
		txtName.setColumns(10);

		txtFamily = new JTextField();
		txtFamily.setColumns(10);
		txtFamily.setBounds(134, 58, 86, 20);
		frmRegister.getContentPane().add(txtFamily);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(134, 89, 86, 20);
		frmRegister.getContentPane().add(txtUsername);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(134, 120, 86, 20);
		frmRegister.getContentPane().add(txtPassword);

		txtRetypePassword = new JPasswordField();
		txtRetypePassword.setBounds(134, 151, 86, 20);
		frmRegister.getContentPane().add(txtRetypePassword);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String family = txtFamily.getText();
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());
				String retypePassword = new String(txtRetypePassword.getPassword());
				if ((!name.equals("")) && (!family.equals("")) && (!username.equals("")) && (!password.equals(""))
						&& (!retypePassword.equals(""))) {
					if (password.equals(retypePassword)) {
						User user = new User(name, family, username, password);
						UserEntityManager uem = new UserEntityManager();
						try {
							if (uem.addUser(user)) {
								JOptionPane.showMessageDialog(null, "User added successfully!");
								frmRegister.setVisible(false);
								Login.main(null);
							} else
								JOptionPane.showMessageDialog(null, "User exist!");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
				txtName.setText("");
				txtFamily.setText("");
				txtUsername.setText("");
				txtPassword.setText("");
				txtRetypePassword.setText("");
			}
		});
		btnRegister.setBounds(131, 182, 89, 23);
		frmRegister.getContentPane().add(btnRegister);
	}
}