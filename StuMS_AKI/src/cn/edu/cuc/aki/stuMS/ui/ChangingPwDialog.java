package cn.edu.cuc.aki.stuMS.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;

import cn.edu.cuc.aki.stuMS.tools.UserTools;
import cn.edu.cuc.aki.stuMS.ui.listener.TextListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangingPwDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private final JPanel contentPanel = new JPanel();
	public JPasswordField passwordField;
	public JPasswordField passwordField_1;
	public JButton okButton = new JButton("OK");
	
	/**
	 * Create the dialog.
	 */
	public ChangingPwDialog(String id) {
		this.id = id;
		
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(600, 350, 354, 235);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		label.setBounds(33, 53, 101, 15);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("\u8BF7\u91CD\u590D\u8F93\u5165\u5BC6\u7801\uFF1A");
		label_1.setBounds(33, 101, 101, 15);
		contentPanel.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 50, 159, 21);
		contentPanel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(144, 98, 159, 21);
		contentPanel.add(passwordField_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.setActionCommand("OK");
				okButton.setEnabled(false);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ChangingPwDialog dialog = (ChangingPwDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
						dialog.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		passwordField.getDocument().addDocumentListener(new TextListener(this, this.passwordField_1) {
			@Override
			public void insertUpdate(DocumentEvent e) {
				String newPw = String.valueOf(((ChangingPwDialog) this.parentContainer).passwordField.getPassword());
				String repeatPw = String.valueOf(((ChangingPwDialog) this.parentContainer).passwordField_1.getPassword());
				if (!newPw.equals("")  && newPw.equals(repeatPw)) {
					((ChangingPwDialog) this.parentContainer).okButton.setEnabled(true);
				} else {
					((ChangingPwDialog) this.parentContainer).okButton.setEnabled(false);
				}
			}
		});
		passwordField_1.getDocument().addDocumentListener(new TextListener(this, this.passwordField_1) {
			@Override
			public void insertUpdate(DocumentEvent e) {
				String newPw = String.valueOf(((ChangingPwDialog) this.parentContainer).passwordField.getPassword());
				String repeatPw = String.valueOf(((ChangingPwDialog) this.parentContainer).passwordField_1.getPassword());
				if (!newPw.equals("")  && newPw.equals(repeatPw)) {
					((ChangingPwDialog) this.parentContainer).okButton.setEnabled(true);
				} else {
					((ChangingPwDialog) this.parentContainer).okButton.setEnabled(false);
				}
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangingPwDialog dialog = (ChangingPwDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
				UserTools.alterPassword(String.valueOf(dialog.passwordField.getPassword()), dialog.id);
				JOptionPane.showMessageDialog(dialog, "修改密码成功", "提示", JOptionPane.PLAIN_MESSAGE);
				dialog.dispose();
			}
		});
	}
}
