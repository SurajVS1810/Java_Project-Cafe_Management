package Cafe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
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
import java.awt.Color;

public class UserPanel extends JFrame {

	private JPanel contentPane;
	public JLabel lblUser;
	private JButton btnNewButton_1;
	private JLabel foodlabel;
	private JLabel pricelabel;
	private JLabel desclabel;
	private JButton cart;
	private JTextField quantity;
	private JLabel quan;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel img;
	private JLabel lblNewLabel;

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
		
		JButton list = new JButton("Food List");
		list.setBackground(new Color(192, 192, 192));
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
		list.setBounds(291, 107, 96, 23);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("ADD TO CART");
		btnNewButton.setBackground(new Color(128, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mca2253","mca2253");
					Statement stmt=con.createStatement();
					if(foodlabel.getText().equals("")||pricelabel.getText().equals("")||desclabel.getText().equals("")||quantity.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please enter the values");
					}
					else
					{

					String s1=lblUser.getText();
					String s2=foodlabel.getText();
					String s3=pricelabel.getText();
					String s4=quantity.getText();
					int i=Integer.parseInt(s4);
					int j=Integer.parseInt(s3);
					int p=i*j;
					String s=Integer.toString(p);
					stmt.executeUpdate("insert into cart values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s+"')");
					JOptionPane.showMessageDialog(null, "Successfully inserted");
					}
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(44, 334, 123, 32);
		contentPane.add(btnNewButton);
		
		lblUser = new JLabel();
		lblUser.setBounds(428, 11, 46, 14);
		contentPane.add(lblUser);
		
		btnNewButton_1 = new JButton("logout");
		btnNewButton_1.setBackground(new Color(255, 255, 128));
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
		foodlabel.setBounds(118, 165, 110, 32);
		contentPane.add(foodlabel);
		
		pricelabel = new JLabel();
		pricelabel.setBounds(118, 204, 96, 32);
		contentPane.add(pricelabel);
		
		desclabel = new JLabel();
		desclabel.setBounds(112, 247, 94, 32);
		contentPane.add(desclabel);
		
		cart = new JButton("Show Cart");
		cart.setBackground(new Color(192, 192, 192));
		cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cart c=new Cart();
				c.cartlabel.setText(lblUser.getText());
				c.setVisible(true);
				dispose();
			}
		});
		cart.setBounds(277, 388, 110, 32);
		contentPane.add(cart);
		
		quantity = new JTextField();
		quantity.setBounds(112, 303, 86, 20);
		contentPane.add(quantity);
		quantity.setColumns(10);
		
		quan = new JLabel("Quantity : ");
		quan.setBounds(20, 306, 66, 14);
		contentPane.add(quan);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 141, 219, 226);
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
		
		ImageIcon bc=new ImageIcon(getClass().getResource("userpanel (1).jpg"));
		
		label = new JLabel("New label");
		label.setBounds(175, 11, -138, 100);
		contentPane.add(label);
		
		img = new JLabel(bc);
		img.setBounds(22, 11, 194, 106);
		contentPane.add(img);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\admin.png"));
		lblNewLabel.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel);
	}

}
