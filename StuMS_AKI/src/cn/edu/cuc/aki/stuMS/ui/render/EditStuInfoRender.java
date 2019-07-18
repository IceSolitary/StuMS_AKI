package cn.edu.cuc.aki.stuMS.ui.render;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import cn.edu.cuc.aki.stuMS.tools.STeaTools;
import cn.edu.cuc.aki.stuMS.ui.MainFrame;

public class EditStuInfoRender extends AbstractCellEditor implements TableCellRenderer, ActionListener, TableCellEditor {
	private static final long serialVersionUID = 1L;
	private TableButton button = null;
	
	private MainFrame parentFrame;

	public EditStuInfoRender(MainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		button = new TableButton("EDIT!", parentFrame.studentConsularPanel.stuTable);
		button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
		button.addActionListener(this);
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		this.button.row = row;
		return button;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.button.row = row;
		return button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new EditStuInfoDialog(this.parentFrame.studentConsularPanel.getId(), 
				String.valueOf(this.button.table.getValueAt(this.button.row, 0)),
				String.valueOf(this.button.table.getValueAt(this.button.row, 1)), 
				String.valueOf(this.button.table.getValueAt(this.button.row, 2)), 
				String.valueOf(this.button.table.getValueAt(this.button.row, 3)), 
				String.valueOf(this.button.table.getValueAt(this.button.row, 4)),
				this.parentFrame
		);
	}
}

class EditStuInfoDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JTextField sexTextField;
	private JTextField majorTextField;

	public EditStuInfoDialog(String id, String sid, String sname, String sex, String age, String major, MainFrame rootFrame) {
		setType(Type.UTILITY);
		setTitle("\u4FEE\u6539\u5B66\u751F\u4FE1\u606F");
		setBounds(500, 300, 451, 227);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(41, 38, 44, 15);
		contentPanel.add(label);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(95, 35, 94, 21);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5E74\u9F84\uFF1A");
		label_1.setBounds(241, 38, 44, 15);
		contentPanel.add(label_1);
		
		ageTextField = new JTextField();
		ageTextField.setColumns(10);
		ageTextField.setBounds(295, 35, 94, 21);
		contentPanel.add(ageTextField);
		
		JLabel label_2 = new JLabel("\u6027\u522B\uFF1A");
		label_2.setBounds(41, 101, 44, 15);
		contentPanel.add(label_2);
		
		sexTextField = new JTextField();
		sexTextField.setColumns(10);
		sexTextField.setBounds(95, 98, 94, 21);
		contentPanel.add(sexTextField);
		
		JLabel label_3 = new JLabel("\u4E13\u4E1A\uFF1A");
		label_3.setBounds(241, 101, 44, 15);
		contentPanel.add(label_3);
		
		majorTextField = new JTextField();
		majorTextField.setColumns(10);
		majorTextField.setBounds(295, 98, 94, 21);
		contentPanel.add(majorTextField);
		
		this.nameTextField.setText(sname);
		this.ageTextField.setText(age);
		this.sexTextField.setText(sex);
		this.majorTextField.setText(major);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						EditStuInfoDialog dialog = (EditStuInfoDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
						
						String nName = nameTextField.getText();
						String nAge = ageTextField.getText();
						String nSex = sexTextField.getText();
						String nMajor = majorTextField.getText();
						
						try {
							if (nName.equals("") || nAge.equals("") || nSex.equals("") || nMajor.equals("")) {
								throw new Exception("存在输入项为空！");
							}
							
							int nAgeInt;
							try {
								nAgeInt = Integer.valueOf(nAge);
								if (nAgeInt < 0) {
									throw new Exception("年龄格式不正确！");
								}
							} catch (NumberFormatException e2) {
								throw new Exception("年龄格式不正确！");
							}
							
							if (!nSex.equals("男") && !nSex.equals("女")) {
								throw new Exception("性别格式不正确");
							}
							int nSexInt;
							if (nSex.equals("男")) {
								nSexInt = 1;
							} else {
								nSexInt = 2;
							}
							
							STeaTools.alterStuInfo(sid, nName, nSexInt, nAgeInt, nMajor, rootFrame.studentConsularPanel.getId());
							rootFrame.studentConsularPanel.initData();
							JOptionPane.showMessageDialog(dialog, "修改学生信息成功", "提示", JOptionPane.PLAIN_MESSAGE);
							dialog.dispose();
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(dialog, e2.getMessage(), "修改学生信息失败", 0);
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						EditStuInfoDialog dialog = (EditStuInfoDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
						dialog.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
		this.setVisible(true);
	}
}
