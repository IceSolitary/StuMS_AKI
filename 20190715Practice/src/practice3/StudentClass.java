package practice3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentClass {
	private String name;
	private int capacity = 40; // 最大容量
	private List<Student> students = null; // 学生班级集合
	private String filepath;

	// 构造函数
	public StudentClass(String name, String filepath) {
		this.name = name;
		this.filepath = filepath;

		students = read(filepath);

	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void init() {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 2;) {
			System.out.println("输入第" + (i + 1) + "个学生的信息(学号 姓名 英语成绩 数学成绩 计算机成绩)");
			try {
				String input = scanner.nextLine();
				String[] temp = input.split("\\s");
				Student stu = new Student(temp[0], temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
						Integer.parseInt(temp[4]));
				if (!addStudent(stu))
					throw new Exception("该学号已存在");
				i++;
			} catch (Exception e) {
				System.err.println("输入有误：" + e);
				continue;
			}

		}
	}

	/**
	 * 向班级中添加学生
	 * 
	 * @param stu
	 * @return 成功添加返回true，如果已存在该学生返回false
	 */
	public boolean addStudent(Student stu) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getID().equals(stu.getID())) {
				return false;
			}
		}
		students.add(stu);
		save(filepath);
		return true;
	}

	// 根据输入设置学生信息
	public void setStudents(List<Student> stus) {
		this.students = stus;
	}

	// 根据输入的学生姓名查询同名学生的所有信息
	public List<Student> getStudents(String stuName) {

		List<Student> candidate = new ArrayList<Student>();
		Student stu = null;
		for (int i = 0; i < students.size(); i++) {
			stu = students.get(i);
			if (stu.getName().equals(stuName)) {
				candidate.add(stu);
			}
		}
		return candidate;
	}

	/**
	 * 删除学生并保存
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteStudent(String id) {
		for (Student student : students) {
			if (student.getID().equals(id)) {
				students.remove(student);
				System.out.println("已删除" + id + "号学生！");
				save(filepath);
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据Student对象ID更新班级中的学生
	 * 
	 * @param newstu
	 * @return
	 */
	public boolean editStudent(Student newstu) {
		for (Student student : students) {
			if (student.getID().equals(newstu.getID())) {
				students.remove(student);
				students.add(newstu);
				save(filepath);
				return true;
			}
		}
		return false;
	}

	// 打印该班级内所有学生的信息
	@Override
	public String toString() {
		String s;
		s = "班级：" + name + "\t" + "容量:" + capacity + "\t" + "实际人数：" + students.size() + "\n\n";
		s = s + "学号" + "\t" + "姓名" + "\t" + "英语" + "\t" + "数学" + "\t" + "计算机" + "\t" + "总成绩\n";

		for (int i = 0; i < students.size(); i++) {
			s = s + students.get(i).getID() + "\t" + students.get(i).getName() + "\t" + students.get(i).getEng() + "\t"
					+ students.get(i).getMath() + "\t" + students.get(i).getComp() + "\t" + students.get(i).getSum()
					+ "\n";
		}
		return s;
	}

	// 序列化保存
	public void save(String filePath) {
		this.filepath = filePath;
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(students);
			out.close();
			fileOut.close();
			// System.out.printf("Serialized data is saved in "+filePath+"\n");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * 反序列化从文件中读出学生列表
	 * 
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Student> read(String filePath) {
		List<Student> stuInDat = null;
		try {
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			stuInDat = (List<Student>) in.readObject();

			System.out.println("反序列化成功！");
			in.close();
			fileIn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return stuInDat;
	}

}
