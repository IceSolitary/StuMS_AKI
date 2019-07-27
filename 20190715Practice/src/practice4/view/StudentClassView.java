package practice4.view;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import practice4.interf.Views;
import practice4.service.Student;
import practice4.service.StudentClassService;

public class StudentClassView implements Views{
	StudentClassService studentClass = null;
	public StudentClassView (String name,String filepath)  {
		File fil = new File(filepath);
		try {
			fil.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		studentClass = new StudentClassService(name, filepath);
	}
	/**
	 * 展示添加学生功能界面
	 * 返回添加成功的学生对象
	 */
	@Override
	public Student showAddStuView() {
		int j = 0;
		while (j != -1) {
			System.out.println("输入学生的信息(学号 姓名 英语成绩 数学成绩 计算机成绩)");
			try {
				Scanner sc = new Scanner(System.in);
				String input = sc.nextLine();
				String[] temp = input.split("\\s");
				Student stu = new Student(temp[0], temp[1], Integer.parseInt(temp[2]),
						Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
				if (!studentClass.addStudent(stu))
					throw new Exception("该学号已存在");
				System.out.println("添加成功！");
				j = -1;
				System.out.println(stu.toString());
				return stu;
			} catch (Exception e) {
				// System.err.println("输入有误："+e);
				continue;
			}
		}
		return null;
	}
	/**
	 * 控制台打印被添加的学生对象
	 */
	@Override
	public void showAddStuResultView(Student student) {
		System.out.println(student.toString());
	}
	
	
	@Override
	public int showInputStuIdView() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 编辑学生信息
	 * 
	 */
	@Override
	public Student showEditStuView() {
		System.out.println("输入要修改的学生信息(学号 姓名 英语成绩 数学成绩 计算机成绩)：");
		int k = 0;
		while (k != -1) {
			try {
				Scanner sc = new Scanner(System.in);
				String input = sc.nextLine();
				String[] temp = input.split("\\s");
				Student stu = new Student(temp[0], temp[1], Integer.parseInt(temp[2]),
						Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
				if (!studentClass.editStudent(stu))
					throw new Exception("不存在该学生!请重新输入：");
				k = -1;
				System.out.println(stu.toString());
				return stu;
			} catch (Exception e) {
				System.out.println("输入有误："+e.getMessage());
				continue;
			}
		}
		return null;
	}
	@Override
	public void showEditStuResultView(Student student) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 根据姓名输出所有候选学生
	 */
	@Override
	public String showSearchStuByName() {
		System.out.println("请输入姓名");
		int k = 0;
		List<Student> result= null;
		while (k != -1) {
			try {
				Scanner sc = new Scanner(System.in);
				String name = sc.nextLine();
				result = studentClass.findStudentByName(name);
				if (result == null)
					throw new Exception("不存在该学生!请重新输入：");
				
				for (Student student : result) {
					System.out.println(student.toString());
				}
				k = -1;
				return name;
			} catch (Exception e) {
				System.out.println("输入有误："+e.getMessage());
				continue;
			}
		}
		return null;
	}
	@Override
	public void showStuResultView(List<Student> students) {
		
		
	}
	
	
	/**
	 * 根据学号查找所有候选学生
	 */
	public void showSearchStuById() {
		System.out.println("请输入学号");
		int k = 0;
		Student result = null;
		while (k != -1) {
			try {
				Scanner sc = new Scanner(System.in);
				String id = sc.nextLine();
				result = studentClass.findStudentById(id);
				if (result == null)
					throw new Exception("不存在该学生!请重新输入：");
				System.out.println(result.toString());				
				k = -1;
				return;
			} catch (Exception e) {
				System.out.println("输入有误："+e.getMessage());
				continue;
			}
		}
		return ;
	}
	
	/**
	 * 根据输入的学号删除指定学生
	 */
	@Override
	public void showDeleteStuResultView() {
		System.out.println("输入要删除的学生学号：");
		int i = 0;
		while (i != -1) {
			if (studentClass.deleteStudentById(new Scanner(System.in).next())) {
				System.out.println("已删除");
				i = -1;
			} else {
				System.out.println("不存在该学生！请重新输入：");
			}
		}
		
	}
	/**
	 * 主菜单
	 */
	@Override
	public int showMainMenu() {
		Scanner scanner = new Scanner(System.in);
		int key = -9;
		while (key != 7) {
			System.out.println("**************菜单**************");
			System.out.println("\t1.添加学生");
			System.out.println("\t2.学号查询");
			System.out.println("\t3.姓名查询");
			System.out.println("\t4.删除学生");
			System.out.println("\t5.编辑学生");
			System.out.println("\t6.显示班级");
			System.out.println("\t7.退出程序");
			System.out.println("\t8.初始化班级");
			System.out.println("请输入数字选择菜单：");
			try {
				if (scanner.hasNext()) {
					key = scanner.nextInt();
				} else {
					System.out.println("输入格式错误!!!!请输入数字：");
					Scanner scanner2 = new Scanner(System.in);
					scanner2.nextInt();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			switch (key) {
			case 1:
				//添加学生
				showAddStuView();
				break;
			case 2:
				//学号查询
				showSearchStuById();
				break;
			case 3:
				//姓名查询
				showSearchStuByName();
				break;
			case 4:
				//删除
				showDeleteStuResultView();
				break;
			case 5:
				//编辑学生
				showEditStuView();
				break;
			case 6:
				//显示班级
				System.out.println(studentClass.toString());
				break;
			case 7:
				key = 7;
				System.out.println("( ^_^ )/~~拜拜");
				break;
			case 8:
				studentClass.init();
				break;
			}
		}
		scanner.close();
		return 0;
	}
}
