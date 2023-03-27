package Cafe;

import java.awt.EventQueue;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

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
		username.setBounds(255, 253, 121, 33);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel testuser = new JLabel("Username :");
		testuser.setBounds(135, 253, 81, 33);
		contentPane.add(testuser);
		
		JLabel textpassword = new JLabel("Password :");
		textpassword.setBackground(new Color(255, 255, 255));
		textpassword.setBounds(135, 310, 74, 17);
		contentPane.add(textpassword);
		
		JButton login = new JButton("LOGIN");
		login.setBackground(new Color(0, 255, 255));
		login.setForeground(new Color(0, 0, 0));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					PreparedStatement pst=con.prepareStatement("select * from login where uname=? and password=?");
					if(username.getText().equals("")||password.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please enter the values");
					}
					else
					{
					String s1=username.getText();
					String s2=password.getText();
					pst.setString(1, s1);
					pst.setString(2, s2);
					
					ResultSet rs= pst.executeQuery();
					
					if(rs.next())
					{
						
							if(s1.equals("admin"))
							{
								JOptionPane.showMessageDialog(null, "Admin successfully login");
								AdminPanel ap=new AdminPanel();
								ap.setVisible(true);
								dispose();
								
							}	
							else
							{
								JOptionPane.showMessageDialog(null, "User successfully login");
								
								UserPanel u=new UserPanel();
								u.lblUser.setText(s1);
								Cart c=new Cart();
								
								u.setVisible(true);
								dispose();
							}	
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid username or password");
						
					}
					rs.close();
					pst.close();
					}
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		login.setBounds(111, 359, 105, 23);
		contentPane.add(login);
		
		JButton register = new JButton("REGISTER");
		register.setBackground(new Color(0, 255, 255));
		register.setForeground(new Color(0, 0, 0));
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from login");
					String s1=username.getText();
					String s2=password.getText();
					if(username.getText().equals("")||password.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please enter the values");
					}
					else
					{
					
						
						while(rs.next())
						{
							
							if(rs.getString(1).equals(s1))
							{
								
								JOptionPane.showMessageDialog(null, "Please enter the another username");
							}
							
								
						}
						
						stmt.executeUpdate("insert into login values('"+s1+"','"+s2+"')");
						JOptionPane.showMessageDialog(null, "User successfully registered");
						
					
					
					}
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		register.setBounds(251, 359, 115, 23);
		contentPane.add(register);
		
		JButton exit = new JButton("EXIT");
		exit.setBackground(new Color(255, 0, 0));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  dispose();
			}
		});
		exit.setBounds(196, 393, 89, 23);
		contentPane.add(exit);
		
		
		ImageIcon ic=new ImageIcon(getClass().getResource("Loginback (2).jpeg"));
		ImageIcon bc=new ImageIcon(getClass().getResource("back (3).jpg"));
		
		JLabel label = new JLabel("New label");
		label.setBounds(251, 23, -14, -5);
		contentPane.add(label);
		
		password = new JPasswordField();
		password.setBounds(255, 302, 121, 33);
		contentPane.add(password);
		
		JLabel online = new JLabel(ic);
		online.setBounds(0, 0, 484, 221);
		contentPane.add(online);
		
		JLabel lblNewLabel = new JLabel(bc);
		lblNewLabel.setBounds(0, 220, 484, 241);
		contentPane.add(lblNewLabel);
	}
}
