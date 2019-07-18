package cn.edu.cuc.aki.stuMS.ui.render;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import cn.edu.cuc.aki.stuMS.tools.STeaTools;
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
		if (JOptionPane.showOptionDialog(SwingUtilities.getWindowAncestor((Component) e.getSource()), "确定要删除该课程吗？", "警告", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null) == 0) {
			STeaTools.deleteTCourse(String.valueOf(this.button.table.getValueAt(this.button.row, 0)), this.parentFrame.studentConsularPanel.getId());
			this.parentFrame.studentConsularPanel.initData();
			JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor((Component) e.getSource()), "删除成功！", "提示", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
