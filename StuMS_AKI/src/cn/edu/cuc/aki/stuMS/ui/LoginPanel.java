package cn.edu.cuc.aki.stuMS.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.MaskFormatter;

import cn.edu.cuc.aki.stuMS.exception.IDNotExistException;
import cn.edu.cuc.aki.stuMS.exception.PasswordErrorException;
import cn.edu.cuc.aki.stuMS.tools.LoginTools;
import cn.edu.cuc.aki.stuMS.ui.listener.TextListener;

public class LoginPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public MainFrame parentFrame;
	
	// Components
	JLabel loginLabel = new JLabel("Login ~", JLabel.CENTER);
	JLabel tipLabel = new JLabel("Please enter the 6-digit id and its corresponding password ~", JLabel.CENTER);
	JLabel idLabel = new JLabel("ID  :  ", JLabel.CENTER);
	JFormattedTextField idTextField;
	JLabel pwLabel = new JLabel("PASSWORD  :  ", JLabel.CENTER);
	JPasswordField pwTextField = new JPasswordField(15);
	JButton loginButton = new JButton("LOGIN!!!! ヾ(≧▽≦*)o");

	// Components controller 
	private boolean idReady = false;
	private boolean pwReady = false;
	
	/**
	 * Constructor
	 * @param parentFrame MainFrame; the JFrame contains the Panel
	 */
	public LoginPanel(MainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		((MainFrame) this.parentFrame).northVS.setPreferredSize(new Dimension(5, 100));
		
		this.setLayout(new BorderLayout());
		
		this.loginLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		this.tipLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
		this.idLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
		this.pwLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
		try {
			MaskFormatter ms = new MaskFormatter("######");
			ms.setPlaceholderCharacter('+');
			this.idTextField = new JFormattedTextField(ms);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		this.idTextField.setColumns(15);
		this.idTextField.setToolTipText("请输入6位数字的ID (/≧▽≦)/");
		this.pwTextField.setEchoChar('*');
		this.pwTextField.setToolTipText("请输入ID对应的密码 (*/ω\\ *)");
		this.loginButton.setFont(new Font("Sim Sun 12", Font.PLAIN, 12));
		this.loginButton.setPreferredSize(new Dimension(260, 25));
		
		this.loginButton.addActionListener(this);
		
		// set form validation
		this.idTextField.getDocument().addDocumentListener(new TextListener(this, this.idTextField) {	
			@Override
			public void insertUpdate(DocumentEvent e) {
				int valueLen = this.textField.getText().replace("+", "").length();
				LoginPanel lPanel = (LoginPanel) this.parentContainer;
				if (valueLen == 6) {
					lPanel.setIdReady(true);
				} else {
					lPanel.setIdReady(false);
				}
				lPanel.loginButton.setEnabled(lPanel.isIdReady() && lPanel.isPwReady());
			}
		});
		this.pwTextField.getDocument().addDocumentListener(new TextListener(this, this.pwTextField) {
			@Override
			public void insertUpdate(DocumentEvent e) {
				LoginPanel lPanel = (LoginPanel) this.parentContainer;
				if (this.textField.getText().equals("")) {
					lPanel.setPwReady(false);
				} else {
					lPanel.setPwReady(true);
				}
				lPanel.loginButton.setEnabled(lPanel.isIdReady() && lPanel.isPwReady());
			}
		});
		
		this.showComponents();
		this.initData();
	}
	
	/**
	 * add all components of this panel
	 */
	private void showComponents() {
		Box b1 = Box.createVerticalBox();
		this.add(new JPanel().add(b1), BorderLayout.CENTER);
		
		b1.add(Box.createVerticalStrut(85));
		
		Box loginLableBox = Box.createHorizontalBox();
		b1.add(loginLableBox);
		loginLableBox.add(Box.createHorizontalGlue());
		loginLableBox.add(this.loginLabel);
		loginLableBox.add(Box.createHorizontalGlue());
		
		b1.add(Box.createVerticalStrut(8));
		
		Box tipLableBox = Box.createHorizontalBox();
		b1.add(tipLableBox);
		tipLableBox.add(Box.createHorizontalGlue());
		tipLableBox.add(this.tipLabel);
		tipLableBox.add(Box.createHorizontalGlue());
		
		b1.add(Box.createVerticalStrut(8));
		
		Box inputBox = Box.createHorizontalBox();
		b1.add(inputBox);
		GridBagLayout inputLayout = new GridBagLayout();
		JPanel inputPanel = new JPanel(inputLayout);
		inputBox.add(inputPanel);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = 1;
		inputPanel.add(this.idLabel, constraints);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		inputPanel.add(this.idTextField, constraints);
		constraints.gridwidth = 1;
		inputPanel.add(this.pwLabel, constraints);
		inputPanel.add(this.pwTextField);
		
		Box loginButtonBox = Box.createHorizontalBox();
		b1.add(loginButtonBox);
		loginButtonBox.add(Box.createHorizontalGlue());
		loginButtonBox.add(this.loginButton);
		loginButtonBox.add(Box.createHorizontalGlue());
		
		b1.add(Box.createVerticalStrut(185));
	}
	
	public void initData() {
		this.idTextField.setText("");
		this.pwTextField.setText("");
	}

	// setter & getter
	public boolean isIdReady() {
		return this.idReady;
	}
	
	public boolean isPwReady() {
		return this.pwReady;
	}
	
	public void setIdReady(boolean b) {
		this.idReady = b;
	}
	
	public void setPwReady(boolean b) {
		this.pwReady = b;
	}
	// setter & getter END
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String inputId = this.idTextField.getText();
		String inputPw = String.valueOf(this.pwTextField.getPassword());
		
		try {
			int role = LoginTools.login(inputId, inputPw);
			switch (role) {
			case 1:
				this.parentFrame.studentPanel.setId(inputId);
				this.parentFrame.showStudentView();
				break;
			case 2:
				this.parentFrame.teacherPanel.setId(inputId);
				this.parentFrame.showTeacherView();
				break;
			case 3:
				this.parentFrame.studentConsularPanel.setId(inputId);
				this.parentFrame.showStudentConsularView();
				break;
			default:
				break;
			}
		} catch (PasswordErrorException e1) {
			JOptionPane.showMessageDialog(this, "您输入的账号或密码不正确！", "登录失败", 0);
		} catch (IDNotExistException e1) {
			JOptionPane.showMessageDialog(this, "您输入的ID不存在！", "登陆失败", 0);
		}
	}
}
