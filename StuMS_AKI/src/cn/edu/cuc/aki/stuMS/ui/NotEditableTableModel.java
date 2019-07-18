package cn.edu.cuc.aki.stuMS.ui;

import javax.swing.table.DefaultTableModel;

public class NotEditableTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;

	public NotEditableTableModel(Object[][] tableData, Object[] tableHeaderName) {
		super(tableData, tableHeaderName);
	}
	
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
    	return false;
    }
}

