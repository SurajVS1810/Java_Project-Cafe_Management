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
import javax.swing.ImageIcon;

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
		btnNewButton.setBackground(new Color(128, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					String s=cartlabel.getText();
					ResultSet rs=stmt.executeQuery("select foodname,price,quantity,total_price from cart where uname='"+s+"'");
					
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
		btnNewButton_1.setBackground(new Color(255, 255, 0));
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
		cartlabel.setBounds(428, 11, 46, 14);
		contentPane.add(cartlabel);
		
		name = new JTextField();
		name.setBounds(88, 122, 86, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setBounds(88, 161, 86, 20);
		contentPane.add(address);
		address.setColumns(10);
		
		no = new JTextField();
		no.setBounds(88, 205, 86, 20);
		contentPane.add(no);
		no.setColumns(10);
		
		land = new JTextField();
		land.setBounds(88, 247, 86, 20);
		contentPane.add(land);
		land.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setBounds(10, 125, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Address :");
		lblNewLabel_1.setBounds(10, 164, 68, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone No :");
		lblNewLabel_2.setBounds(10, 205, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Land Mark :");
		lblNewLabel_3.setBounds(10, 250, 68, 14);
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
						String k=rs.getString(1);
						int i=Integer.parseInt(k);
						int j=i+7;
						String s=Integer.toString(j);
					String s1=name.getText();
					String s2=address.getText();
					
					String s3=no.getText();
					String s4=land.getText();
					String s5=cartlabel.getText();
					
					DefaultTableModel dtm= (DefaultTableModel) table.getModel();
					String s6=(String) dtm.getValueAt(table.getSelectedRow(), 0);
					String s7=(String) dtm.getValueAt(table.getSelectedRow(), 1);
					String s8=(String) dtm.getValueAt(table.getSelectedRow(), 2);
					String s9=(String) dtm.getValueAt(table.getSelectedRow(), 3);
					
					stmt.executeUpdate("insert into orders values('"+s+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"',CURRENT_TIMESTAMP)");
					JOptionPane.showMessageDialog(null, "Your Order is Placed, Food will be arrived in 30 minutes,Thanku...");
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
		
		JButton btnNewButton_3 = new JButton("Home");
		btnNewButton_3.setBackground(new Color(255, 255, 0));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPanel u=new UserPanel();
				u.lblUser.setText(cartlabel.getText());
				u.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(10, 27, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("DELETE");
		btnNewButton_4.setBackground(new Color(255, 0, 0));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					DefaultTableModel dtm= (DefaultTableModel) table.getModel();
					String strfn=(String) dtm.getValueAt(table.getSelectedRow(), 0);
				
					
						if(strfn.equals(""))
						{
							JOptionPane.showMessageDialog(null, "Please enter the values");
						}
						else
						{
							String sql="delete from cart where foodname='"+strfn+"'";
							stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Successfully deleted");
						}
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(289, 384, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(203, 125, 271, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm= (DefaultTableModel) table.getModel();
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Food Name", "Price", "Quantity", "Total Price"
			}
		));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\admin.png"));
		lblNewLabel_4.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel_4);
	}

}
