package Cafe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AdminPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
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
	public AdminPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton users = new JButton("Users");
		users.setForeground(new Color(0, 255, 0));
		users.setBackground(new Color(0, 0, 255));
		users.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				users u=new users();
				u.setVisible(true);
				dispose();
			}
		});
		users.setBounds(174, 85, 141, 70);
		contentPane.add(users);
		
		JButton item = new JButton("Items");
		item.setForeground(new Color(0, 255, 0));
		item.setBackground(new Color(0, 0, 255));
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Food f=new Food();
				f.setVisible(true);
				dispose();
			}
		});
		item.setBounds(174, 192, 141, 64);
		contentPane.add(item);
		
		JButton order = new JButton("Orders");
		order.setForeground(new Color(0, 255, 0));
		order.setBackground(new Color(0, 0, 255));
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Orders o=new Orders();
				o.setVisible(true);
				dispose();
			}
		});
		order.setBounds(174, 300, 141, 70);
		contentPane.add(order);
		
		JLabel lblNewLabel = new JLabel("Admin Panel");
		lblNewLabel.setBackground(new Color(0, 0, 255));
		lblNewLabel.setForeground(new Color(64, 0, 64));
		lblNewLabel.setBounds(205, 22, 89, 52);
		contentPane.add(lblNewLabel);
		
		JButton logout = new JButton("logout");
		logout.setBackground(new Color(255, 255, 0));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l=new Login();
				l.setVisible(true);
				dispose();
	
			}
		});
		logout.setBounds(372, 22, 89, 23);
		contentPane.add(logout);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\admin.png"));
		lblNewLabel_1.setBounds(0, 0, 484, 461);
		contentPane.add(lblNewLabel_1);
	}
}
