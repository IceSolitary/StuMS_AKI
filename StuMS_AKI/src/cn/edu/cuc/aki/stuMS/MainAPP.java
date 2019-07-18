package cn.edu.cuc.aki.stuMS;

import java.awt.EventQueue;

import cn.edu.cuc.aki.stuMS.ui.MainFrame;

public class MainAPP {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
