import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 238, 160);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(22, 11, 64, 14);
		frmLogin.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 50, 64, 14);
		frmLogin.getContentPane().add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(107, 8, 105, 20);
		frmLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(107, 47, 105, 20);
		frmLogin.getContentPane().add(txtPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());
				if ((!username.equals("")) && (!password.equals(""))) {
					User user = new User(username, password);
					UserEntityManager uem = new UserEntityManager();
					try {
						user = uem.userExist(user);
						if (user != null) {
							JOptionPane.showMessageDialog(null, "Hello " + user.getName());
							frmLogin.setVisible(false);
							Paint.enter(user);
						} else {
							JOptionPane.showMessageDialog(null, "User not exist!");
						}
					} catch (HeadlessException | SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Enter username and password!");
				}
			}
		});
		btnLogin.setBounds(127, 89, 85, 23);
		frmLogin.getContentPane().add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.setVisible(false);
				Register.enter();
			}
		});
		btnRegister.setBounds(22, 89, 85, 23);
		frmLogin.getContentPane().add(btnRegister);
	}
}