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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

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
		scrollPane.setBounds(220, 95, 241, 311);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel dtm= (DefaultTableModel) table.getModel();
				String strfn=(String) dtm.getValueAt(table.getSelectedRow(), 0);
				String strprice=(String) dtm.getValueAt(table.getSelectedRow(), 1);
		
				String strdesc=(String) dtm.getValueAt(table.getSelectedRow(), 2);
				
				foodname.setText(strfn);
				price.setText(strprice);
				desc.setText(strdesc);
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Food Name", "Price", "Description"
			}
		));
		
		JButton btnNewButton = new JButton("Food Details");
		btnNewButton.setBackground(new Color(255, 128, 255));
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
		foodname.setBounds(110, 85, 86, 20);
		contentPane.add(foodname);
		foodname.setColumns(10);
		
		price = new JTextField();
		price.setBounds(110, 130, 86, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		desc = new JTextField();
		desc.setBounds(110, 172, 86, 20);
		contentPane.add(desc);
		desc.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Food Name :");
		lblNewLabel.setBounds(31, 88, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price :");
		lblNewLabel_1.setBounds(45, 133, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description :");
		lblNewLabel_2.setBounds(31, 175, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton add = new JButton("Add Item");
		add.setBackground(new Color(128, 255, 0));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					if(foodname.getText().equals("")||price.getText().equals("")||desc.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please enter the values");
					}
					else
					{
					String s1=foodname.getText();
					String s2=price.getText();
					
					String s3=desc.getText();
					stmt.executeUpdate("insert into food values('"+s1+"','"+s2+"','"+s3+"')");
					JOptionPane.showMessageDialog(null, "Successfully inserted");
					}
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add.setBounds(59, 239, 105, 35);
		contentPane.add(add);
		
		JButton edit = new JButton("Edit Item");
		edit.setBackground(new Color(128, 255, 0));
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(foodname.getText().equals("")||price.getText().equals("")||desc.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter the values");
				}
				else
				{
				DefaultTableModel dtm= (DefaultTableModel) table.getModel();
				String strfn=(String) dtm.getValueAt(table.getSelectedRow(), 0);
				String s1=foodname.getText();
				String s2=price.getText();
				String s3=desc.getText();
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					String sql="update food set foodname='"+s1+"', price='"+s2+"', description='"+s3+"' where foodname='"+strfn+"'";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Successfully updated");
					
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			//	DefaultTableModel dtm= (DefaultTableModel) table.getModel();
				
				dtm.setValueAt(s1,table.getSelectedRow(), 0);
				dtm.setValueAt(s2,table.getSelectedRow(), 1);
				dtm.setValueAt(s3,table.getSelectedRow(), 2);
				
				}
				
			}
		});
		edit.setBounds(59, 299, 105, 35);
		contentPane.add(edit);
		
		JButton delete = new JButton("Delete Item");
		delete.setBackground(new Color(128, 255, 0));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					String s1=foodname.getText(); 
				
					String sql="delete from food where foodname='"+s1+"'";
					stmt.executeUpdate(sql);
						if(foodname.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please enter the values");
						else
					JOptionPane.showMessageDialog(null, "Successfully deleted");
					
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		delete.setBounds(59, 359, 105, 35);
		contentPane.add(delete);
		
		JButton logout = new JButton("logout");
		logout.setBackground(new Color(255, 255, 0));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.setVisible(true);
				dispose();
			}
		});
		logout.setBounds(372, 11, 89, 23);
		contentPane.add(logout);
		
		JButton btnNewButton_1 = new JButton("home");
		btnNewButton_1.setBackground(new Color(255, 255, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel ap=new AdminPanel();
				ap.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(11, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\admin.png"));
		lblNewLabel_3.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel_3);
	}
}
