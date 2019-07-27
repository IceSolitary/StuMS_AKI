package practice4.service;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import practice4.interf.Service;

public class StudentClassService implements Service{
	private String name;
	private int capacity = 40; // 最大容量
	private List<Student> students = null; // 学生班级集合
	private String filepath;

	// 构造函数
	public StudentClassService(String name, String filepath){
		this.name = name;
		this.filepath = filepath;
		
		if (read(filepath)!=null) {
			students = read(filepath);
		}else {
			students = new ArrayList<Student>();
		}
		
	}
	public List<Student> getStudents() {
		return students;
	}
	/**
	 * 初始化班级并会覆写到文件中
	 */
	public void init() {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 2;) {
			System.out.println("输入第" + (i + 1) + "个学生的信息(学号 姓名 英语成绩 数学成绩 计算机成绩)");
			try {
				String input = scanner.nextLine();
				String[] temp = input.split("\\s");
				Student stu = new Student(temp[0], temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
						Integer.parseInt(temp[4]));
				System.out.println("正在添加......");
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
	/**
	 * 删除学生并保存
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteStudentById(String id) {
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

	/**
	 * 打印该班级内所有学生的信息
	 */
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

	/**
	 * 在指定路径下序列化保存全部学生的名单
	 * @param filePath
	 * @throws IOException 
	 */
	public void save(String filePath)  {
		this.filepath = filePath;
		File fil = new File(this.filepath);
		if (!fil.exists()) {
			try {
				fil.createNewFile();
				FileOutputStream fileOut = new FileOutputStream(filePath);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(students);
				out.close();
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} else {
			try {
				FileOutputStream fileOut = new FileOutputStream(filePath);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(students);
				out.close();
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

	/**
	 * 反序列化从文件中读出学生列表
	 * 
	 * @param filePath
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Student> read(String filePath) {
		List<Student> stuInDat = null;
		try {
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			stuInDat = (List<Student>) in.readObject();

			System.out.println("反序列化成功！");
			in.close();
			fileIn.close();
		} catch (Exception e) {
			System.out.println("暂无内容可读！");
			
			return null;
		}
		return stuInDat;
	}
	/**
	 * 根据id查找学生
	 * 查找成功返回学生对象，失败返回null
	 */
	@Override
	public Student findStudentById(String id) {
		List<Student> students = this.getStudents();
		for (Student stu:students) {
			if (stu.getID().equals(id)) {
				return stu;
			}
		}
		return null;
	}
	/**
	 * 根据name查找学生
	 * 返回List<Student>，可能为null
	 */
	@Override
	public List<Student> findStudentByName(String name) {
		List<Student> candidate = new ArrayList<Student>();
		List<Student> stus = this.getStudents();
		for (Student stu:stus) {
			if (stu.getName().equals(name)) {
				candidate.add(stu);
			}
		}
		return candidate;
	}

	

}
