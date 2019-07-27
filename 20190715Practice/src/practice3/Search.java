package practice3;

import java.util.List;
import java.util.Scanner;

public class Search {
	StudentClass studentClass = null;

	public Search(StudentClass stu) {
		this.studentClass = stu;
	}

	public Search(StudentClass stu, String filePath) {
		this.studentClass = stu;
		this.studentClass.setStudents(StudentClass.read(filePath));
		// System.out.println(studentClass.toString());
	}

	public void searchByName() {
		Scanner scanner = new Scanner(System.in);
		Student stu = null;
		System.out.println("输入要查找的学生名字：");
		String name = scanner.next();

		System.out.println("结果：");
		// scanner.close();
		List<Student> stus = studentClass.getStudents(name);
		for (int i = 0; i < stus.size(); i++) {
			stu = stus.get(i);
			if (stu.getName().equals(name)) {
				System.out.println(stu.toString());
			}
		}
		System.out.println("查询完毕");
	}

	public void searchByID() {
		Student stu = null;
		Scanner scanner2 = new Scanner(System.in);

		System.out.println("输入要查找的学生学号：");
		String id = scanner2.next();
		// scanner2.close();
		System.out.println("结果：");

		List<Student> students = studentClass.getStudents();
		for (int i = 0; i < students.size(); i++) {
			stu = students.get(i);
			if (stu.getID().equals(id)) {
				System.out.println(stu.toString());
				System.out.println("查询完毕");
				return;

			}
		}

		System.out.println("无");
		return;
	}
}
