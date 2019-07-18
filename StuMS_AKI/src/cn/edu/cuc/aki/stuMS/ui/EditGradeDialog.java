package cn.edu.cuc.aki.stuMS.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EditGradeDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	public EditGradeDialog() {
		setTitle("\u4FEE\u6539\u6210\u7EE9");
		setResizable(false);
		setBounds(100, 100, 344, 131);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u65B0\u7684\u6210\u7EE9\uFF08-1~100\uFF0C-1\u4EE3\u8868\u5220\u9664\u6210\u7EE9\uFF09");
		label.setBounds(53, 23, 245, 15);
		contentPanel.add(label);
		
		textField = new JTextField();
		textField.setBounds(73, 56, 51, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int inputGrade = Integer.valueOf(textField.getText());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			});
			okButton.setBounds(178, 55, 94, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
	}
}
