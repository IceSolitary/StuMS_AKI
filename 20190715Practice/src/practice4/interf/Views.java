package practice4.interf;

import java.util.List;

import practice4.service.Student;

public interface Views {
	Student showAddStuView();
	void showAddStuResultView(Student student);
	
	int showInputStuIdView();
	Student showEditStuView();
	void showEditStuResultView(Student student);
	
	String showSearchStuByName();
	void showStuResultView(List<Student> students);
	
	void showDeleteStuResultView();
	
	int showMainMenu();
}
