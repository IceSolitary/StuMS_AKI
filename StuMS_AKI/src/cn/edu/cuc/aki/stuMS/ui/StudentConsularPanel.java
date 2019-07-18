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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.cuc.aki.stuMS.tools.STeaTools;
import cn.edu.cuc.aki.stuMS.ui.render.EditStuInfoRender;
import cn.edu.cuc.aki.stuMS.ui.render.RemoveCTRender;

public class StudentConsularPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private MainFrame parentFrame;
	private String id = "";
	
	// TabbedPanes
	public JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	JPanel infoPanel = new JPanel();
	JPanel gradePanel = new JPanel();
	JPanel ctPanel = new JPanel();
	JPanel stuPanel = new JPanel();
	JPanel settingPanel = new JPanel();
	
	// infoPanel Components
	private JLabel idLable = new JLabel("");
	private JLabel nameLable = new JLabel("");
	private JLabel sexLable = new JLabel("");
	private JLabel ageLable = new JLabel("");
	private JLabel majorLable = new JLabel("");
	
	// scPanel Components
	JComboBox<String> scCourseComboBox = new JComboBox<String>();
	JComboBox<String> scStudentComboBox = new JComboBox<String>();
	JTable scTable;
	// scPanel Data
	TableModel scModel;
	public ArrayList<String[]> scData;
	
	// ctPanel Components
	JComboBox<String> ctCourseComboBox = new JComboBox<String>();
	JComboBox<String> ctNTeacherComboBox = new JComboBox<String>();
	public JTable ctTable;
	private JButton addCTButton = new JButton("开设新的课程");
	// ctPanel Data
	TableModel ctModel;
	ArrayList<String[]> ctData;
	ArrayList<String[]> allCTCourses = STeaTools.selectAllCourse();
	ArrayList<String[]> allNTeachers = STeaTools.selectAllNTeachers();
	
	// stuPanel Components
	public JTable stuTable;
	// stuPanel Data
	TableModel stuModel;
	ArrayList<String[]> stuData;
	
	// settingPanel Components
	JButton changingPwButton = new JButton("修改密码");
	JButton logOutButton = new JButton("退出登录");
	
	/**
	 * Constructor
	 * @param parentFrame JFrame; the JFrame containing the Panel
	 */
	public StudentConsularPanel(MainFrame parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setLayout(new BorderLayout());
		
		this.add(this.tabbedPane, BorderLayout.CENTER);
		this.tabbedPane.add("个人信息", this.infoPanel);
		this.tabbedPane.add("查看学生成绩", this.gradePanel);
		this.tabbedPane.add("查看/修改学院开课", this.ctPanel);
		this.tabbedPane.add("查看/修改学生信息", this.stuPanel);
		this.tabbedPane.add("其他设置", this.settingPanel);
		
		this.logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((MainFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).showLoginView();
			}
		});
		this.changingPwButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangingPwDialog(parentFrame.studentConsularPanel.getId()).setVisible(true);
				System.out.println(parentFrame.studentConsularPanel.getId());
			}
		});
		this.addCTButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedCIndex = parentFrame.studentConsularPanel.ctCourseComboBox.getSelectedIndex();
				int selectedTIndex = parentFrame.studentConsularPanel.ctNTeacherComboBox.getSelectedIndex();
				
				if (JOptionPane.showOptionDialog(SwingUtilities.getWindowAncestor((Component) e.getSource()), "确定要添加该课程吗？", "警告", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null) == 0) {
					STeaTools.addSTCourse(allCTCourses.get(selectedCIndex)[0], allNTeachers.get(selectedTIndex)[0], id);
					parentFrame.studentConsularPanel.initData();
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor((Component) e.getSource()), "添加成功！", "提示", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		this.ctTable = new JTable(this.ctModel);
		this.scTable = new JTable(this.scModel);
		this.stuTable = new JTable(this.stuModel);
		
		this.initData();
		
		this.scCourseComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> scCourseComboBox = parentFrame.studentConsularPanel.scCourseComboBox;
				JComboBox<String> scStudentComboBox = parentFrame.studentConsularPanel.scStudentComboBox;
				
				if (scCourseComboBox.getSelectedIndex() != -1 && scStudentComboBox.getSelectedIndex() != -1) {
					ArrayList<String[]> scDataRefer = parentFrame.studentConsularPanel.scData;
					ArrayList<String[]> currentScDataTemp = new ArrayList<String[]>();
					
					if (scCourseComboBox.getSelectedIndex() != 0) {
						String ctid = ((String) scCourseComboBox.getSelectedItem()).split(" ")[0];
						if (scStudentComboBox.getSelectedIndex() != 0) {
							String sid = ((String) scStudentComboBox.getSelectedItem()).split(" ")[0];
							for (Iterator<String[]> iterator = scDataRefer.iterator(); iterator.hasNext();) {
								String[] tuple = (String[]) iterator.next();
								if (tuple[0].equals(ctid) && tuple[2].equals(sid)) {
									currentScDataTemp.add(tuple);
								}
							}
						} else {
							for (Iterator<String[]> iterator = scDataRefer.iterator(); iterator.hasNext();) {
								String[] tuple = (String[]) iterator.next();
								if (tuple[0].equals(ctid)) {
									currentScDataTemp.add(tuple);
								}
							}
						}
					} else if (scStudentComboBox.getSelectedIndex() != 0) {
						String sid = ((String) scStudentComboBox.getSelectedItem()).split(" ")[0];
						for (Iterator<String[]> iterator = scDataRefer.iterator(); iterator.hasNext();) {
							String[] tuple = (String[]) iterator.next();
							if (tuple[2].equals(sid)) {
								currentScDataTemp.add(tuple);
							}
						}
					}
					
					if (scCourseComboBox.getSelectedIndex() == 0 && scStudentComboBox.getSelectedIndex() == 0) {
						currentScDataTemp = scDataRefer;
					}
					
					// refresh scTable
					String[] scName = {"课程号", "课程名", "学生号", "学生名", "成绩"};
					int cscRowCount = currentScDataTemp.size();
			        String[][] cscData = new String[cscRowCount][5];
			        for (int i = 0; i < cscRowCount; i++) {
						cscData[i] = currentScDataTemp.get(i);
					}
					parentFrame.studentConsularPanel.scModel = new NotEditableTableModel(cscData, scName, -1);
					parentFrame.studentConsularPanel.scTable.setModel(parentFrame.studentConsularPanel.scModel);
					parentFrame.studentConsularPanel.scTable.setRowSorter(new TableRowSorter<TableModel>(parentFrame.studentConsularPanel.scModel));
				}
			}
		});
		this.scStudentComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox<String> scCourseComboBox = parentFrame.studentConsularPanel.scCourseComboBox;
				JComboBox<String> scStudentComboBox = parentFrame.studentConsularPanel.scStudentComboBox;
				
				if (scCourseComboBox.getSelectedIndex() != -1 && scStudentComboBox.getSelectedIndex() != -1) {
					ArrayList<String[]> scDataRefer = parentFrame.studentConsularPanel.scData;
					ArrayList<String[]> currentScDataTemp = new ArrayList<String[]>();
					
					if (scCourseComboBox.getSelectedIndex() != 0) {
						String ctid = ((String) scCourseComboBox.getSelectedItem()).split(" ")[0];
						if (scStudentComboBox.getSelectedIndex() != 0) {
							String sid = ((String) scStudentComboBox.getSelectedItem()).split(" ")[0];
							for (Iterator<String[]> iterator = scDataRefer.iterator(); iterator.hasNext();) {
								String[] tuple = (String[]) iterator.next();
								if (tuple[0].equals(ctid) && tuple[2].equals(sid)) {
									currentScDataTemp.add(tuple);
								}
							}
						} else {
							for (Iterator<String[]> iterator = scDataRefer.iterator(); iterator.hasNext();) {
								String[] tuple = (String[]) iterator.next();
								if (tuple[0].equals(ctid)) {
									currentScDataTemp.add(tuple);
								}
							}
						}
					} else if (scStudentComboBox.getSelectedIndex() != 0) {
						String sid = ((String) scStudentComboBox.getSelectedItem()).split(" ")[0];
						for (Iterator<String[]> iterator = scDataRefer.iterator(); iterator.hasNext();) {
							String[] tuple = (String[]) iterator.next();
							if (tuple[2].equals(sid)) {
								currentScDataTemp.add(tuple);
							}
						}
					}
					
					if (scCourseComboBox.getSelectedIndex() == 0 && scStudentComboBox.getSelectedIndex() == 0) {
						currentScDataTemp = scDataRefer;
					}
					
					// refresh scTable
					String[] scName = {"课程号", "课程名", "学生号", "学生名", "成绩"};
					int cscRowCount = currentScDataTemp.size();
			        String[][] cscData = new String[cscRowCount][5];
			        for (int i = 0; i < cscRowCount; i++) {
						cscData[i] = currentScDataTemp.get(i);
					}
					parentFrame.studentConsularPanel.scModel = new NotEditableTableModel(cscData, scName, -1);
					parentFrame.studentConsularPanel.scTable.setModel(parentFrame.studentConsularPanel.scModel);
					parentFrame.studentConsularPanel.scTable.setRowSorter(new TableRowSorter<TableModel>(parentFrame.studentConsularPanel.scModel));
				}
			}
		});

		this.ctTable.setRowSorter(new TableRowSorter<TableModel>(this.ctModel));
		this.scTable.setRowSorter(new TableRowSorter<TableModel>(this.scModel));
		this.stuTable.setRowSorter(new TableRowSorter<TableModel>(this.stuModel));
		
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
		
		// gradePanel
		this.gradePanel.setLayout(new BorderLayout());
		Box fliterBox = Box.createHorizontalBox();
		fliterBox.add(Box.createHorizontalStrut(10));
		fliterBox.add(new JLabel("course FLITER:  "));
		fliterBox.add(this.scCourseComboBox);
		fliterBox.add(Box.createHorizontalStrut(50));
		fliterBox.add(new JLabel("student FLITER:  "));
		fliterBox.add(this.scStudentComboBox);
		fliterBox.add(Box.createHorizontalStrut(10));
		this.gradePanel.add(fliterBox, BorderLayout.NORTH);
		this.gradePanel.add(new JScrollPane(this.scTable), BorderLayout.CENTER);
		// gradePanel END
		
		// ctPanel
		this.ctPanel.setLayout(new BorderLayout());
		this.ctPanel.add(new JScrollPane(this.ctTable), BorderLayout.CENTER);
		Box addCTBox = Box.createHorizontalBox();
		this.ctPanel.add(addCTBox, BorderLayout.SOUTH);
		addCTBox.add(Box.createHorizontalStrut(10));
		addCTBox.add(this.ctCourseComboBox);
		addCTBox.add(Box.createHorizontalStrut(30));
		addCTBox.add(this.ctNTeacherComboBox);
		addCTBox.add(Box.createHorizontalStrut(50));
		addCTBox.add(this.addCTButton);
		addCTBox.add(Box.createHorizontalStrut(10));
		// ctPanel END
		
		// stuPanel
		this.stuPanel.setLayout(new BorderLayout());
		this.stuPanel.add(new JScrollPane(this.stuTable), BorderLayout.CENTER);
		// stuPanel END
		
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
			Map<String, String> teaInfoMap = STeaTools.selectSTInfo(this.id);
			this.idLable.setText(teaInfoMap.get("tid"));
			this.nameLable.setText(teaInfoMap.get("tname"));
			this.sexLable.setText(teaInfoMap.get("sex"));
			this.ageLable.setText(teaInfoMap.get("age"));
			this.majorLable.setText(teaInfoMap.get("major"));
			
			// set scData & ctData & stuData
			this.scData = STeaTools.searchStuGrade(this.id);
			this.ctData = STeaTools.searchCT(this.id);
			this.stuData = STeaTools.searchStuInfo(this.id);
			
			// set scCourseComboBox & scStudentCombox Data
			ArrayList<String> allCourses = new ArrayList<String>();
			ArrayList<String> allStudents = new ArrayList<String>();
			allCourses.add("全部");
			allStudents.add("全部");
			for (int i = 0; i < this.scData.size(); i++) {
				String courseItem = this.scData.get(i)[0] + " " + this.scData.get(i)[1];
				if (!allCourses.contains(courseItem)) {
					allCourses.add(courseItem);
				}
				String stuItem = this.scData.get(i)[2] + " " + this.scData.get(i)[3];
				if (!allStudents.contains(stuItem)) {
					allStudents.add(stuItem);
				}
			}
			
			this.scCourseComboBox.removeAllItems();
			this.scStudentComboBox.removeAllItems();

			for (String string : allCourses) {
				this.scCourseComboBox.addItem(string);
			}
			for (String string : allStudents) {
				this.scStudentComboBox.addItem(string);
			}
			// set scCourseComboBox & scStudentCombox Data END
			
			// set ctCourseComboBox & ctStudentCombox Data
			
			for (String[] tuple : this.allCTCourses) {
				this.ctCourseComboBox.addItem(tuple[1]);
			}
			for (String[] tuple : this.allNTeachers) {
				this.ctNTeacherComboBox.addItem(tuple[1]);
			}
			// set ctCourseComboBox & ctStudentCombox Data END
			
	        // set scTable & ctTable
	        String[] scName = {"课程号", "课程名", "学生号", "学生名", "成绩"};
	        int scRowCount = this.scData.size();
	        String[][] scData = new String[scRowCount][5];
	        for (int i = 0; i < this.scData.size(); i++) {
				scData[i] = this.scData.get(i);
			}
	        this.scModel = new NotEditableTableModel(scData, scName, -1);
	        this.scTable.setModel(this.scModel);
	        this.scTable.setRowSorter(new TableRowSorter<TableModel>(this.scModel));
	        
	        String[] ctNames = {"课程号", "课程名", "授课教师教工号", "授课教师名", "删除此课程"};
	        int ctRowCount = this.ctData.size();
	        String[][] ctData = new String[ctRowCount][4];
	        for (int i = 0; i < this.ctData.size(); i++) {
				ctData[i] = this.ctData.get(i);
			}
	        this.ctModel = new NotEditableTableModel(ctData, ctNames, 4);
	        this.ctTable.setModel(this.ctModel);
	        this.ctTable.setRowSorter(new TableRowSorter<TableModel>(this.ctModel));
	        this.ctTable.getColumnModel().getColumn(4).setCellEditor(new RemoveCTRender(this.parentFrame));
	        this.ctTable.getColumnModel().getColumn(4).setCellRenderer(new RemoveCTRender(this.parentFrame));
	        
	        String[] stuNames = {"学号", "学生名", "性别", "年龄", "专业", "修改学生信息"};
	        int stuRowCount = this.stuData.size();
	        String[][] stuData = new String[stuRowCount][5];
	        for (int i = 0; i < this.stuData.size(); i++) {
				stuData[i] = this.stuData.get(i);
			}
	        this.stuModel = new NotEditableTableModel(stuData, stuNames, 5);
	        this.stuTable.setModel(this.stuModel);
	        this.stuTable.setRowSorter(new TableRowSorter<TableModel>(this.stuModel));
	        this.stuTable.getColumnModel().getColumn(5).setCellEditor(new EditStuInfoRender(this.parentFrame));
	        this.stuTable.getColumnModel().getColumn(5).setCellRenderer(new EditStuInfoRender(this.parentFrame));
		}
	}
}
