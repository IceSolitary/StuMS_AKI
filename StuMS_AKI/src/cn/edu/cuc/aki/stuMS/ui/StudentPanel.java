package cn.edu.cuc.aki.stuMS.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class StudentPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private MainFrame parentFrame;
	private int id = 0;
	
	/**
	 * Constructor
	 * @param parentFrame JFrame; the JFrame containing the Panel
	 * @param id int; student id
	 */
	public StudentPanel(MainFrame parentFrame) {
		this.parentFrame = parentFrame;
				
		this.setLayout(new BorderLayout());
		
		this.showComponents();
	}
	
	/**
	 * add all components of this panel
	 */
	private void showComponents() {
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}