package cn.edu.cuc.aki.stuMS.ui.render;

import javax.swing.JButton;
import javax.swing.JTable;

class TableButton extends JButton {
	private static final long serialVersionUID = 1L;
	int row;
	JTable table;
	
	public TableButton(String text, JTable table) {
		super(text);
		this.table = table;
	}
}
