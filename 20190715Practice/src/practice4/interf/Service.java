package practice4.interf;

import java.util.List;

import practice4.service.Student;


public interface Service {
	boolean addStudent(Student student);
	Student findStudentById(String id);
	boolean editStudent(Student student);
	List<Student> findStudentByName(String name);
	boolean deleteStudentById(String id);
}
