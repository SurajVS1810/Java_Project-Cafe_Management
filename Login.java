package Cafe;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBounds(251, 127, 86, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(251, 167, 86, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		JLabel testuser = new JLabel("Username :");
		testuser.setBounds(142, 130, 74, 14);
		contentPane.add(testuser);
		
		JLabel textpassword = new JLabel("Password :");
		textpassword.setBounds(142, 170, 74, 14);
		contentPane.add(textpassword);
		
		JButton login = new JButton("LOGIN");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					PreparedStatement pst=con.prepareStatement("select * from login where uname=? and pass=?");
					pst.setString(1, username.getText());
					pst.setString(2, password.getText());
					
					ResultSet rs= pst.executeQuery();
					if(rs.next())
					{
						System.out.println("User is valid");
						JOptionPane.showMessageDialog(null, "User successfully login");
					}
					else
					{
						System.out.println("User is invalid");
						JOptionPane.showMessageDialog(null, "Invalid username or password");
					}
					rs.close();
					pst.close();
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		login.setBounds(130, 227, 105, 23);
		contentPane.add(login);
		
		JButton register = new JButton("REGISTER");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					String s1=username.getText();
					String s2=password.getText();
					stmt.executeUpdate("insert into login values('"+s1+"','"+s2+"')");
					JOptionPane.showMessageDialog(null, "User successfully registered");
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		register.setBounds(248, 227, 115, 23);
		contentPane.add(register);
		
		JButton exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  dispose();
			}
		});
		exit.setBounds(196, 261, 89, 23);
		contentPane.add(exit);
	}
}
