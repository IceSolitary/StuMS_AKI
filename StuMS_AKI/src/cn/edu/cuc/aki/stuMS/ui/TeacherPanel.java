package cn.edu.cuc.aki.stuMS.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.cuc.aki.stuMS.tools.StuTools;

public class TeacherPanel extends JPanel {
	private static final long serialVersionUID = 1L;

//	private MainFrame parentFrame;
	private String id = "";
	
	// TabbedPanes
	public JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	JPanel infoPanel = new JPanel();
	JPanel ctPanel = new JPanel();
	JPanel gradePanel = new JPanel();
	JPanel settingPanel = new JPanel();
	
	// infoPanel Components
	private JLabel idLable = new JLabel("");
	private JLabel nameLable = new JLabel("");
	private JLabel sexLable = new JLabel("");
	private JLabel ageLable = new JLabel("");
	private JLabel majorLable = new JLabel("");
	
	// ctPanel Components
	private JTable ctTable;
	// ctPanel Data
	TableModel ctModel;
	ArrayList<String[]> ctData;
	
	// scPanel Components
	JComboBox<String> scCourseComboBox = new JComboBox<String>();
	JComboBox<String> scStudentComboBox = new JComboBox<String>();
	JTable scTable;
	// scPanel Data
	TableModel scModel;
	ArrayList<String[]> scData;
	ArrayList<String[]> currentScData;
	
	// settingPanel Components
	JButton changingPwButton = new JButton("修改密码");
	JButton logOutButton = new JButton("退出登录");
	
	/**
	 * Constructor
	 * @param parentFrame JFrame; the JFrame containing the Panel
	 */
	public TeacherPanel(MainFrame parentFrame) {
//		this.parentFrame = parentFrame;
		
		this.setLayout(new BorderLayout());
		
		this.add(this.tabbedPane, BorderLayout.CENTER);
		this.tabbedPane.add("个人信息", this.infoPanel);
		this.tabbedPane.add("查看课程信息", this.ctPanel);
		this.tabbedPane.add("查看/操作学生成绩", this.gradePanel);
		this.tabbedPane.add("其他设置", this.settingPanel);
		
		this.logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((MainFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).showLoginView();
			}
		});
		this.changingPwButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangingPwDialog(parentFrame.teacherPanel.getId()).setVisible(true);
			}
		});
		
		this.scCourseComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> scCourseComboBox = parentFrame.teacherPanel.scCourseComboBox;
				JComboBox<String> scStudentComboBox = parentFrame.teacherPanel.scStudentComboBox;
				ArrayList<String[]> scData = parentFrame.teacherPanel.scData;
				ArrayList<String[]> currentScData = parentFrame.teacherPanel.currentScData;
				currentScData.clear();
				
				if (scCourseComboBox.getSelectedIndex() != 0) {
					String ctid = ((String) scCourseComboBox.getSelectedItem()).split(" ")[0];
					if (scStudentComboBox.getSelectedIndex() != 0) {
						String sid = ((String) scStudentComboBox.getSelectedItem()).split(" ")[0];
						for (Iterator<String[]> iterator = scData.iterator(); iterator.hasNext();) {
							String[] tupe = (String[]) iterator.next();
							if (tupe[0].equals(ctid) && tupe[2].equals(sid)) {
								currentScData.add(tupe);
							}
						}
					} else {
						for (Iterator<String[]> iterator = scData.iterator(); iterator.hasNext();) {
							String[] tupe = (String[]) iterator.next();
							if (tupe[0].equals(ctid)) {
								currentScData.add(tupe);
							}
						}
					}
				} else if (scStudentComboBox.getSelectedIndex() != 0) {
					String sid = ((String) scStudentComboBox.getSelectedItem()).split(" ")[0];
					for (Iterator<String[]> iterator = scData.iterator(); iterator.hasNext();) {
						String[] tupe = (String[]) iterator.next();
						if (tupe[2].equals(sid)) {
							currentScData.add(tupe);
						}
					}
				}
				
				// refresh scTable
				String[] scName = {"课程号", "课程名", "学生号", "学生名", "成绩"};
				int cscRowCount = currentScData.size();
		        String[][] cscData = new String[cscRowCount][5];
		        for (int i = 0; i < cscRowCount; i++) {
					cscData[i] = currentScData.get(i);
				}
				parentFrame.teacherPanel.scModel = new NotEditableTableModel(cscData, scName);
				parentFrame.teacherPanel.scTable.setModel(parentFrame.teacherPanel.scModel);
			}
		});
		this.scStudentComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> scCourseComboBox = parentFrame.teacherPanel.scCourseComboBox;
				JComboBox<String> scStudentComboBox = parentFrame.teacherPanel.scStudentComboBox;
				ArrayList<String[]> scData = parentFrame.teacherPanel.scData;
				ArrayList<String[]> currentScData = parentFrame.teacherPanel.currentScData;
				currentScData.clear();
				
				if (scCourseComboBox.getSelectedIndex() != 0) {
					String ctid = ((String) scCourseComboBox.getSelectedItem()).split(" ")[0];
					if (scStudentComboBox.getSelectedIndex() != 0) {
						String sid = ((String) scStudentComboBox.getSelectedItem()).split(" ")[0];
						for (Iterator<String[]> iterator = scData.iterator(); iterator.hasNext();) {
							String[] tupe = (String[]) iterator.next();
							if (tupe[0].equals(ctid) && tupe[2].equals(sid)) {
								currentScData.add(tupe);
							}
						}
					} else {
						for (Iterator<String[]> iterator = scData.iterator(); iterator.hasNext();) {
							String[] tupe = (String[]) iterator.next();
							if (tupe[0].equals(ctid)) {
								currentScData.add(tupe);
							}
						}
					}
				} else if (scStudentComboBox.getSelectedIndex() != 0) {
					String sid = ((String) scStudentComboBox.getSelectedItem()).split(" ")[0];
					for (Iterator<String[]> iterator = scData.iterator(); iterator.hasNext();) {
						String[] tupe = (String[]) iterator.next();
						if (tupe[2].equals(sid)) {
							currentScData.add(tupe);
						}
					}
				}
				
				// refresh scTable
				String[] scName = {"课程号", "课程名", "学生号", "学生名", "成绩"};
				int cscRowCount = currentScData.size();
		        String[][] cscData = new String[cscRowCount][5];
		        for (int i = 0; i < cscRowCount; i++) {
					cscData[i] = currentScData.get(i);
				}
				parentFrame.teacherPanel.scModel = new NotEditableTableModel(cscData, scName);
				parentFrame.teacherPanel.scTable.setModel(parentFrame.teacherPanel.scModel);
			}
		});
		
		this.ctTable = new JTable(this.ctModel);
		this.scTable = new JTable(this.scModel);
		
		this.initData();

		this.ctTable.setRowSorter(new TableRowSorter<TableModel>(this.ctModel));
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
		
		// ctPanel
		this.ctPanel.setLayout(new BorderLayout());
		this.ctPanel.add(new JScrollPane(this.ctTable), BorderLayout.CENTER);
		// ctPanel END
		
		// gradePanel
		this.setLayout(new BorderLayout());
		Box fliterBox = Box.createHorizontalBox();
		fliterBox.add(this.scCourseComboBox);
		fliterBox.add(Box.createHorizontalStrut(50));
		fliterBox.add(this.scStudentComboBox);
		this.gradePanel.add(fliterBox, BorderLayout.NORTH);
		this.gradePanel.add(new JScrollPane(this.scTable), BorderLayout.CENTER);
		// gradePanel END
		
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
			Map<String, String> teaInfoMap = StuTools.selectStuInfo(this.id);
			this.idLable.setText(teaInfoMap.get("sid"));
			this.nameLable.setText(teaInfoMap.get("name"));
			this.sexLable.setText(teaInfoMap.get("sex"));
			this.ageLable.setText(teaInfoMap.get("age"));
			this.majorLable.setText(teaInfoMap.get("major"));
			
			// TODO: get ctData & scData & currentScData
			
			
			// set scCourseComboBox & scStudentCombox Data
			ArrayList<String> allCourses = new ArrayList<String>();
			ArrayList<String> allStudents = new ArrayList<String>();
			allCourses.add("全部");
			allStudents.add("全部");
			for (int i = 0; i < this.scData.size(); i++) {
				String courseItem = this.scData.get(i)[0] + " " + this.scData.get(i)[1];
				if (allCourses.contains(courseItem)) {
					allCourses.add(courseItem);
				}
				String stuItem = this.scData.get(i)[2] + " " + this.scData.get(i)[3];
				if (allStudents.contains(stuItem)) {
					allStudents.add(stuItem);
				}
			}
			this.scCourseComboBox = new JComboBox<String>((String[])allCourses.toArray());
			this.scStudentComboBox = new JComboBox<String>((String[])allStudents.toArray());
			
			// TODO: CT Model
	        String[] ctNames = {"课程号", "课程名"};
	        int ctRowCount = this.ctData.size();
	        String[][] ctData = new String[ctRowCount][2];
	        for (int i = 0; i < this.ctData.size(); i++) {
				ctData[i] = this.ctData.get(i);
			}
	        this.ctModel = new NotEditableTableModel(ctData, ctNames);
	        this.ctTable.setModel(this.ctModel);
	        this.ctTable.setRowSorter(new TableRowSorter<TableModel>(this.scModel));
	        
	        // TODO: SC Model
	        String[] scName = {"课程号", "课程名", "学生号", "学生名", "成绩"};
	        int scRowCount = this.scData.size();
	        String[][] scData = new String[scRowCount][5];
	        for (int i = 0; i < this.scData.size(); i++) {
				scData[i] = this.scData.get(i);
			}
	        this.scModel = new NotEditableTableModel(scData, scName);
	        this.scTable.setModel(this.scModel);
	        this.scTable.setRowSorter(new TableRowSorter<TableModel>(this.scModel));
		}
	}
}
