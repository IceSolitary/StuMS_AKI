package practice3;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		String filepath = "sf.dat";
		StudentClass sf = new StudentClass("SoftWare", filepath);
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
			try {
				if (scanner.hasNextInt()) {
					key = scanner.nextInt();
				} else {
					System.out.println("error!!!!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			switch (key) {
			case 1:
				int j = 0;
				while (j != -1) {
					System.out.println("输入学生的信息(学号 姓名 英语成绩 数学成绩 计算机成绩)");
					try {
						Scanner sc = new Scanner(System.in);
						String input = sc.nextLine();
						String[] temp = input.split("\\s");
						Student stu = new Student(temp[0], temp[1], Integer.parseInt(temp[2]),
								Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
						if (!sf.addStudent(stu))
							throw new Exception("该学号已存在");
						System.out.println("添加成功！");
						j = -1;
					} catch (Exception e) {
						// System.err.println("输入有误："+e);
						continue;
					}
				}
				break;
			case 2:
				Search search = new Search(sf, filepath);
				search.searchByID();
				break;
			case 3:
				Search search2 = new Search(sf, filepath);
				search2.searchByName();
				break;
			case 4:
				System.out.println("输入要删除的学生学号：");
				int i = 0;
				while (i != -1) {
					if (sf.deleteStudent(new Scanner(System.in).next())) {
						i = -1;
					} else {
						System.out.println("不存在该学生！请重新输入：");
					}
				}
				break;
			case 5:
				System.out.println("输入要修改的学生信息(学号 姓名 英语成绩 数学成绩 计算机成绩)：");
				int k = 0;
				while (k != -1) {
					try {
						Scanner sc = new Scanner(System.in);
						String input = sc.nextLine();
						String[] temp = input.split("\\s");
						Student stu = new Student(temp[0], temp[1], Integer.parseInt(temp[2]),
								Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
						if (!sf.editStudent(stu))
							throw new Exception("不存在该学生!请重新输入：");
						k = -1;
					} catch (Exception e) {
						// System.err.println("输入有误："+e);
						continue;
					}
				}
				break;
			case 6:
				System.out.println(sf.toString());
				break;
			case 7:
				key = 7;
				break;
			}
			// scanner.close();

		}

	}

}
