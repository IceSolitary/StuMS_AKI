package cn.edu.cuc.aki.stuMS.ui.render;

import java.awt.BorderLayout;
import java.awt.Component;
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

import cn.edu.cuc.aki.stuMS.exception.CourseNotMatchStudentException;
import cn.edu.cuc.aki.stuMS.tools.NTeaTools;
import cn.edu.cuc.aki.stuMS.ui.MainFrame;

public class RemoveCTRender extends AbstractCellEditor implements TableCellRenderer, ActionListener, TableCellEditor {
	private static final long serialVersionUID = 1L;
	private TableButton button = null;
	
	private MainFrame parentFrame;

	public RemoveCTRender(MainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		button = new TableButton("REMOVE!", parentFrame.studentConsularPanel.ctTable);
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
		new RemoveCTDialog(this.parentFrame.studentConsularPanel.getId(), String.valueOf(this.button.table.getValueAt(this.button.row, 0)), String.valueOf(this.button.table.getValueAt(this.button.row, 2)), parentFrame);
	}
}

class RemoveCTDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	public RemoveCTDialog(String id, String tid, String cid, MainFrame rootFrame) {
		setTitle("\u4FEE\u6539\u6210\u7EE9");
		setResizable(false);
		setBounds(500, 300, 344, 131);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u65B0\u7684\u6210\u7EE9\uFF08-1~100\uFF0C-1\u4EE3\u8868\u5220\u9664\u6210\u7EE9\uFF09");
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
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
					EditGradeDialog dialog = (EditGradeDialog) SwingUtilities.getWindowAncestor((Component) e.getSource());
					try {
						int inputGrade = Integer.valueOf(textField.getText());
						if (inputGrade < -1 || inputGrade > 100) {
							throw new NumberFormatException();
						}
						
						try {
							NTeaTools.alterStuGrade(tid, sid, kkid, inputGrade);
							rootFrame.teacherPanel.initData();
							JOptionPane.showMessageDialog(dialog, "锟睫改成硷拷锟缴癸拷", "锟斤拷示", JOptionPane.PLAIN_MESSAGE);
						} catch (CourseNotMatchStudentException e1) {
							e1.printStackTrace();
						}
						
						dialog.dispose();
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(dialog, "锟斤拷锟斤拷某杉锟斤拷锟绞斤拷锟斤拷锟饺凤拷锟�", "锟睫革拷失锟斤拷", 0);
					}
				}
			});
			okButton.setBounds(178, 55, 94, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		this.setVisible(true);
	}
}
