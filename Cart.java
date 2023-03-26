package Cafe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;

public class Cart extends JFrame {

	private JPanel contentPane;
	public JLabel cartlabel;
	private JTextField name;
	private JTextField address;
	private JTextField no;
	private JTextField land;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart frame = new Cart();
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
	public Cart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Show Cart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					String s=cartlabel.getText();
					ResultSet rs=stmt.executeQuery("select foodname,price from cart where uname='"+s+"'");
					
					ResultSetMetaData rm=rs.getMetaData();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(291, 70, 101, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(385, 27, 89, 23);
		contentPane.add(btnNewButton_1);
		
		cartlabel = new JLabel();
		cartlabel.setBounds(410, 11, 46, 14);
		contentPane.add(cartlabel);
		
		name = new JTextField();
		name.setBounds(88, 77, 86, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setBounds(88, 120, 86, 20);
		contentPane.add(address);
		address.setColumns(10);
		
		no = new JTextField();
		no.setBounds(88, 162, 86, 20);
		contentPane.add(no);
		no.setColumns(10);
		
		land = new JTextField();
		land.setBounds(88, 207, 86, 20);
		contentPane.add(land);
		land.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setBounds(10, 80, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Adress :");
		lblNewLabel_1.setBounds(10, 123, 68, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone No :");
		lblNewLabel_2.setBounds(10, 165, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Land Mark :");
		lblNewLabel_3.setBounds(10, 210, 68, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_2 = new JButton("Order now");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					if(name.getText().equals("")||address.getText().equals("")||no.getText().equals("")||land.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please enter the values");
					}
					else
					{
					ResultSet rs=stmt.executeQuery("select count(*) from orders");
					if(rs.next())
					{
						String s1=rs.getString(1);
						int i=Integer.parseInt(s1);
						int j=i++;
					
					String s2=name.getText();
					String s3=address.getText();
					
					String s4=no.getText();
					String s5=land.getText();
					String s6=cartlabel.getText();
					
					DefaultTableModel dtm= (DefaultTableModel) table.getModel();
					String s7=(String) dtm.getValueAt(table.getSelectedRow(), 0);
					String s8=(String) dtm.getValueAt(table.getSelectedRow(), 1);
					
					
					stmt.executeUpdate("insert into orders values("+j+",'"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"',CURRENT_TIMESTAMP)");
					JOptionPane.showMessageDialog(null, "Successfully inserted");
					}
					}
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBackground(new Color(0, 255, 0));
		btnNewButton_2.setBounds(54, 290, 110, 47);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(249, 116, 206, 210);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Food Name", "Price"
			}
		));
		
		JButton btnNewButton_3 = new JButton("Home");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPanel u=new UserPanel();
				u.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(10, 27, 89, 23);
		contentPane.add(btnNewButton_3);
	}

}
