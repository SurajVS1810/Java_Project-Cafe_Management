package Cafe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class users extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					users frame = new users();
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
	public users() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton userbutton = new JButton("Show Users");
		userbutton.setBackground(new Color(128, 255, 0));
		userbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from login");
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
		userbutton.setBounds(172, 65, 130, 23);
		contentPane.add(userbutton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm= (DefaultTableModel) table.getModel();
				String struname=(String) dtm.getValueAt(table.getSelectedRow(), 0);
			}
		});
		scrollPane.setBounds(130, 102, 215, 220);
		contentPane.add(scrollPane);
		
		JLabel deletelabel = new JLabel();
		deletelabel.setBounds(113, 346, 46, 17);
		contentPane.add(deletelabel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm= (DefaultTableModel) table.getModel();
				String struname=(String) dtm.getValueAt(table.getSelectedRow(), 0);
				deletelabel.setText(struname);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username", "Password"
			}
		));
		
		JButton deleteuser = new JButton("DELETE USER");
		deleteuser.setBackground(new Color(255, 0, 0));
		deleteuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					DefaultTableModel dtm= (DefaultTableModel) table.getModel();
					String struname=(String) dtm.getValueAt(table.getSelectedRow(), 0);
					
						if(deletelabel.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Please enter the values");
						}
						else
						{
							String sql="delete from login where uname='"+struname+"'";
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Successfully deleted");
						}
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		deleteuser.setBounds(182, 346, 120, 23);
		contentPane.add(deleteuser);
		
		JButton btnNewButton = new JButton("logout");
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(372, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("home");
		btnNewButton_1.setBackground(new Color(255, 255, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel ap=new AdminPanel();
				ap.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\admin.png"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
		
		
	}
}
