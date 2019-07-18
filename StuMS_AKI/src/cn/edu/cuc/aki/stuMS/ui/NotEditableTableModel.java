package cn.edu.cuc.aki.stuMS.ui;

import javax.swing.table.DefaultTableModel;

public class NotEditableTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	private int editableCol;

	public NotEditableTableModel(Object[][] tableData, Object[] tableHeaderName, int editableCol) {
		super(tableData, tableHeaderName);
		this.editableCol = editableCol;
	}
	
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
    	return columnIndex == editableCol ? true: false;
    }
}

