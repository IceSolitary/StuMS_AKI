package cn.edu.cuc.aki.stuMS.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class EditGradeRender extends AbstractCellEditor implements TableCellRenderer, ActionListener, TableCellEditor {
	private static final long serialVersionUID = 1L;
	private JButton button = null;
	
	private MainFrame parentFrame;

	public EditGradeRender(MainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		button = new JButton("EDIT!");
		button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		button.addActionListener(this);
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return button;
	}
}
