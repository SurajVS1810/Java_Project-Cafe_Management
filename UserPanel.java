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
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField food;
	private JTextField price;
	private JTextField desc;
	public JLabel lblUser;
	private JButton btnNewButton_1;

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
				
				food.setText(strfn);
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
		
		food = new JTextField();
		food.setBounds(36, 134, 110, 32);
		contentPane.add(food);
		food.setColumns(10);
		
		price = new JTextField();
		price.setBounds(36, 166, 110, 32);
		contentPane.add(price);
		price.setColumns(10);
		
		desc = new JTextField();
		desc.setBounds(36, 198, 110, 32);
		contentPane.add(desc);
		desc.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD TO CART");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
	}

}
