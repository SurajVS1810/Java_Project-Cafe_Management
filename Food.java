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
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Food extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField foodname;
	private JTextField price;
	private JTextField desc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Food frame = new Food();
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
	public Food() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 95, 241, 291);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Food Name", "Price", "Description"
			}
		));
		
		JButton btnNewButton = new JButton("Food Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from food");
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
		btnNewButton.setBounds(294, 62, 121, 23);
		contentPane.add(btnNewButton);
		
		foodname = new JTextField();
		foodname.setBounds(110, 43, 86, 20);
		contentPane.add(foodname);
		foodname.setColumns(10);
		
		price = new JTextField();
		price.setBounds(110, 92, 86, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		desc = new JTextField();
		desc.setBounds(110, 143, 86, 20);
		contentPane.add(desc);
		desc.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Food Name :");
		lblNewLabel.setBounds(31, 46, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price :");
		lblNewLabel_1.setBounds(31, 95, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description :");
		lblNewLabel_2.setBounds(31, 146, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton add = new JButton("Add Item");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					String s1=foodname.getText();
					String s2=price.getText();
					int i=Integer.parseInt(s2);
					String s3=desc.getText();
					stmt.executeUpdate("insert into food values('"+s1+"',"+i+",'"+s3+"')");
					JOptionPane.showMessageDialog(null, "Successfully inserted");
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add.setBounds(59, 200, 89, 35);
		contentPane.add(add);
		
		JButton edit = new JButton("Edit Item");
		edit.setBounds(59, 262, 89, 35);
		contentPane.add(edit);
		
		JButton delete = new JButton("Delete Item");
		delete.setBounds(59, 328, 89, 35);
		contentPane.add(delete);
	}
}
