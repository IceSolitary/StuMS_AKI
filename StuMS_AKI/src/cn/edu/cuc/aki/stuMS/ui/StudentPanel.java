package cn.edu.cuc.aki.stuMS.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.cuc.aki.stuMS.tools.StuTools;

import javax.swing.JLabel;
import javax.swing.JSeparator;

public class StudentPanel extends JPanel {
	private static final long serialVersionUID = 1L;

//	private MainFrame parentFrame;
	private String id = "";
	
	// TabbedPanes
	public JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	JPanel infoPanel = new JPanel();
	JPanel scPanel = new JPanel();
	JPanel settingPanel = new JPanel();
	
	// infoPanel Components
	private JLabel idLable = new JLabel("");
	private JLabel nameLable = new JLabel("");
	private JLabel sexLable = new JLabel("");
	private JLabel ageLable = new JLabel("");
	private JLabel majorLable = new JLabel("");
	
	// scPanel Components
	private JTable scTable;
	// scPanel Data
	TableModel scModel;
	
	// settingPanel Components
	JButton changingPwButton = new JButton("修改密码");
	JButton logOutButton = new JButton("退出登录");
	
	/**
	 * Constructor
	 * @param parentFrame JFrame; the JFrame containing the Panel
	 */
	public StudentPanel(MainFrame parentFrame) {
//		this.parentFrame = parentFrame;
				
		this.setLayout(new BorderLayout());
		
		this.add(this.tabbedPane, BorderLayout.CENTER);
		this.tabbedPane.add("个人信息", this.infoPanel);
		this.tabbedPane.add("查看成绩信息", this.scPanel);
		this.tabbedPane.add("其他设置", this.settingPanel);
		
		this.logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((MainFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).showLoginView();
			}
		});
		this.changingPwButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangingPwDialog(parentFrame.studentPanel.id).setVisible(true);
			}
		});
		
		this.scTable = new JTable(this.scModel);
		
		this.initData();
		
        this.scTable.setRowSorter(new TableRowSorter<TableModel>(this.scModel));
		
		this.showComponents();
	}
	
	/**
	 * add all components of this panel
	 */
	private void showComponents() {
		// infoPanel
		this.infoPanel.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(122, 111, 57, 15);
		infoPanel.add(lblId);
		idLable.setBounds(225, 111, 79, 15);
		infoPanel.add(idLable);
		JSeparator separator = new JSeparator();
		separator.setBounds(109, 136, 212, 10);
		infoPanel.add(separator);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(393, 111, 79, 15);
		infoPanel.add(label);
		nameLable.setBounds(496, 111, 79, 15);
		infoPanel.add(nameLable);
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(380, 136, 212, 10);
		infoPanel.add(separator_1);
		
		JLabel label_2 = new JLabel("\u6027\u522B");
		label_2.setBounds(122, 172, 79, 15);
		infoPanel.add(label_2);
		sexLable.setBounds(225, 172, 79, 15);
		infoPanel.add(sexLable);
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(109, 197, 212, 10);
		infoPanel.add(separator_2);
		
		JLabel label_4 = new JLabel("\u5E74\u9F84");
		label_4.setBounds(393, 172, 79, 15);
		infoPanel.add(label_4);
		ageLable.setBounds(496, 172, 79, 15);
		infoPanel.add(ageLable);
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(380, 197, 212, 10);
		infoPanel.add(separator_3);
		
		JLabel label_6 = new JLabel("\u4E13\u4E1A");
		label_6.setBounds(122, 237, 79, 15);
		infoPanel.add(label_6);
		majorLable.setBounds(225, 237, 350, 15);
		infoPanel.add(majorLable);
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(109, 262, 483, 10);
		infoPanel.add(separator_4);
		// infoPanel END
		
		// scPanel
		this.scPanel.setLayout(new BorderLayout());
		this.scPanel.add(new JScrollPane(this.scTable), BorderLayout.CENTER);
		
		// settingPanel
		Box settingBox = Box.createVerticalBox();
		this.settingPanel.add(settingBox);
		settingBox.add(Box.createVerticalStrut(200));
		Box settingBtnsBox = Box.createHorizontalBox();
		settingBox.add(settingBtnsBox);
		settingBtnsBox.add(this.changingPwButton);
		settingBtnsBox.add(Box.createHorizontalStrut(30));
		settingBtnsBox.add(this.logOutButton);
		// settingPanel END
	}
	
	// setter & getter
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	// setter & getter END
	
	public void initData() {
		if (!this.id.equals("")) {
			Map<String, String> stuInfoMap = StuTools.selectStuInfo(this.id);
			this.idLable.setText(stuInfoMap.get("sid"));
			this.nameLable.setText(stuInfoMap.get("name"));
			this.sexLable.setText(stuInfoMap.get("sex"));
			this.ageLable.setText(stuInfoMap.get("age"));
			this.majorLable.setText(stuInfoMap.get("major"));
			
			ArrayList<String[]> arrayData = StuTools.selectStuGrade(this.id);
			int rowCount = arrayData.size();
			String[][] tableData = new String[rowCount][5];
			for (int i = 0; i < arrayData.size(); i++) {
				tableData[i] = arrayData.get(i);
			}
			
	        String[] name={"课程号", "课程名", "教工号", "教师名", "成绩"};
	        
	        this.scModel = new NotEditableTableModel(tableData, name);
	        this.scTable.setModel(this.scModel);
	        this.scTable.setRowSorter(new TableRowSorter<TableModel>(this.scModel));
		}
	}
}