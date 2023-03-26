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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public JLabel lblUser;
	private JButton btnNewButton_1;
	private JLabel foodlabel;
	private JLabel pricelabel;
	private JLabel desclabel;
	private JButton cart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPanel frame = new UserPanel();
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
	public UserPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 136, 223, 224);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel dtm= (DefaultTableModel) table.getModel();
				String strfn=(String) dtm.getValueAt(table.getSelectedRow(), 0);
				String strprice=(String) dtm.getValueAt(table.getSelectedRow(), 1);
		
				String strdesc=(String) dtm.getValueAt(table.getSelectedRow(), 2);
				
				foodlabel.setText(strfn);
				pricelabel.setText(strprice);
				desclabel.setText(strdesc);
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
		
		JButton list = new JButton("Food List");
		list.addActionListener(new ActionListener() {
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
		list.setBounds(277, 88, 96, 23);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("ADD TO CART");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					if(foodlabel.getText().equals("")||pricelabel.getText().equals("")||desclabel.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please enter the values");
					}
					else
					{

					String s1=lblUser.getText();
					String s2=foodlabel.getText();
					String s3=pricelabel.getText();
					
					stmt.executeUpdate("insert into cart values('"+s1+"','"+s2+"','"+s3+"')");
					JOptionPane.showMessageDialog(null, "Successfully inserted");
					}
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(36, 260, 123, 32);
		contentPane.add(btnNewButton);
		
		lblUser = new JLabel();
		lblUser.setBounds(428, 11, 46, 14);
		contentPane.add(lblUser);
		
		btnNewButton_1 = new JButton("logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(405, 28, 69, 23);
		contentPane.add(btnNewButton_1);
		
		foodlabel = new JLabel();
	
		foodlabel.setBounds(36, 133, 110, 32);
		contentPane.add(foodlabel);
		
		pricelabel = new JLabel();
		pricelabel.setBounds(36, 168, 96, 32);
		contentPane.add(pricelabel);
		
		desclabel = new JLabel();
		desclabel.setBounds(36, 211, 94, 32);
		contentPane.add(desclabel);
		
		cart = new JButton("Show Cart");
		cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cart c=new Cart();
				c.cartlabel.setText(lblUser.getText());
				c.setVisible(true);
				dispose();
			}
		});
		cart.setBounds(188, 392, 110, 32);
		contentPane.add(cart);
	}

}
