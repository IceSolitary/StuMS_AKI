package cn.edu.cuc.aki.stuMS.ui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane = new JPanel();

	/**
	 * Components
	 */
	private JPanel cardPanel = new JPanel();
	public CardLayout cardLayout = new CardLayout();
	private JLabel copyrightLabel = new JLabel("Copyright\u00A92019 AKI");
	private JLabel titleLable = new JLabel("Student Information & Grade Manage System");
	public Component northVS = Box.createVerticalStrut(100);
	
	public LoginPanel loginPanel = new LoginPanel(this);
	public StudentPanel studentPanel = new StudentPanel(this);
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		super("学生信息成绩管理系统");

		String lookAndFeel = UIManager.getSystemLookAndFeelClassName(); 
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setMinimumSize(new Dimension(800, 600));
		this.setBounds(new Rectangle(400, 200, 800, 600));
		
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(this.contentPane);
		
		this.copyrightLabel.setFont(new Font("SimSun", Font.PLAIN, 12));
		this.copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.titleLable.setFont(new Font("Bookman Old Style", Font.PLAIN, 18));
		this.titleLable.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.cardPanel.setLayout(cardLayout);
		this.cardPanel.add(this.loginPanel, "LoginView");
		this.cardPanel.add(this.studentPanel, "StudentView");
		
		this.cardLayout.show(cardPanel, "LoginView");
		
		this.showComponents();
	}
	
	/**
	 * add all components of this panel
	 */
	private void showComponents() {
		Box northBox = Box.createVerticalBox();
		this.contentPane.add(northBox, BorderLayout.NORTH);
		northBox.add(this.northVS);
		Box titleBox = Box.createHorizontalBox();
		northBox.add(titleBox);
		titleBox.add(Box.createHorizontalGlue());
		titleBox.add(this.titleLable);
		titleBox.add(Box.createHorizontalGlue());
		
		this.contentPane.add(this.cardPanel, BorderLayout.CENTER);
		
		this.contentPane.add(this.copyrightLabel, BorderLayout.SOUTH);
	}
	
	public void showLoginView() {
		this.cardLayout.show(cardPanel, "LoginView");
		this.northVS.setPreferredSize(new Dimension(5, 100));
		this.setTitle("学生信息成绩管理系统  --  登录");
	}
	
	public void showStudentView() {
		this.northVS.setPreferredSize(new Dimension(5, 10));
		this.setTitle("学生信息成绩管理系统  --  学生  --  " + this.studentPanel.getId());
		this.cardLayout.show(this.cardPanel, "LoginView");
	}
}
