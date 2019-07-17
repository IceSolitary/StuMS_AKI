package cn.edu.cuc.aki.stuMS.ui.listener;

import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class TextListener implements DocumentListener {
	protected Container parentContainer;
	protected JTextField textField;
	
	public TextListener(Container parentContainer, JTextField textField) {
		this.parentContainer = parentContainer;
		this.textField = textField;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.insertUpdate(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		this.insertUpdate(e);
	}

}
