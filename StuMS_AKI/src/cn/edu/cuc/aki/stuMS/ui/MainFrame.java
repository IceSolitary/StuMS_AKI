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
	public TeacherPanel teacherPanel = new TeacherPanel(this);
	public StudentConsularPanel studentConsularPanel = new StudentConsularPanel(this);
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel"; 
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
		this.cardPanel.add(this.teacherPanel, "TeacherView");
		this.cardPanel.add(this.studentConsularPanel, "StudentConsularView");
		
		this.showLoginView();
		
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
		northBox.add(Box.createVerticalStrut(10));
		
		this.contentPane.add(this.cardPanel, BorderLayout.CENTER);
		
		this.contentPane.add(this.copyrightLabel, BorderLayout.SOUTH);
	}
	
	public void showLoginView() {
		this.northVS.setPreferredSize(new Dimension(5, 100));
		this.setTitle("ѧ����Ϣ�ɼ�����ϵͳ  --  ��¼");
		this.cardLayout.show(cardPanel, "LoginView");
		this.loginPanel.initData();
		this.setSize(800, 600);// cardLayout �л�ʱnorthVS��ʾ����ı䣬����ȸı��Ż���£�ԭ����qwq PS:ֱ���ڹ��캯�������������northVS��仯
	}
	
	public void showStudentView() {
		this.northVS.setPreferredSize(new Dimension(5, 10));
		this.setTitle("ѧ����Ϣ�ɼ�����ϵͳ  --  ѧ��  --  " + this.studentPanel.getId());
		this.cardLayout.show(this.cardPanel, "StudentView");
		this.studentPanel.initData();
		this.studentPanel.tabbedPane.setSelectedIndex(0);
		this.setSize(801, 600); 
	}
	
	public void showTeacherView() {
		this.northVS.setPreferredSize(new Dimension(5, 10));
		this.setTitle("ѧ����Ϣ�ɼ�����ϵͳ  --  ��ʦ  --  " + this.teacherPanel.getId());
		this.cardLayout.show(cardPanel, "TeacherView");
		this.teacherPanel.initData();
		this.teacherPanel.tabbedPane.setSelectedIndex(0);
		this.setSize(801, 600);
	}
	
	public void showStudentConsularView() {
		this.northVS.setPreferredSize(new Dimension(5, 10));
		this.setTitle("ѧ����Ϣ�ɼ�����ϵͳ  --  ������ʦ  --  " + this.studentConsularPanel.getId());
		this.cardLayout.show(cardPanel, "StudentConsularView");
		this.studentConsularPanel.initData();
		this.studentConsularPanel.tabbedPane.setSelectedIndex(0);
		this.setSize(801, 600);
	}
}
