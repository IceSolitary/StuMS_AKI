package practice4.service;

import java.io.Serializable;

public class Student implements Serializable {
	private String id;
	private String name;
	private int eng;
	private int math;
	private int comp;
	private int sum;

	public Student(String id, String name, int eng, int math, int comp) {
		this.id = id;
		this.name = name;
		this.eng = eng;
		this.math = math;
		this.comp = comp;
		sum();
	}

	public Student(Student stu) {
		this.id = stu.id;
		this.name = new String(stu.name);
		this.eng = stu.eng;
		this.math = stu.math;
		this.comp = stu.comp;
		sum();
	}

	public void setID(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEng(int eng) {
		this.eng = eng;
		sum();
	}

	public void setMath(int math) {
		this.math = math;
		sum();
	}

	public void setComp(int comp) {
		this.comp = comp;
		sum();
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMath() {
		return math;
	}

	public int getEng() {
		return eng;
	}

	public int getComp() {
		return comp;
	}

	public int getSum() {
		return sum;
	}

	void sum() {
		this.sum = eng + math + comp;
	}

	@Override
	public String toString() {
		return getID() + "\t" + getName() + "\t" + getEng() + "\t" + getMath() + "\t" + getComp() + "\t" + getSum();
	}

	@Override
	public boolean equals(Object x) {
		if (this.getClass() != x.getClass()) {
			return false;
		}
		Student b = (Student) x;
		return (this.getID().equals(b.getID()));
	}

	public int compare(Student a) {
		if (this.getSum() > a.getSum()) {
			return 1;
		} else if (this.getSum() == a.getSum()) {
			return 0;
		} else {
			return -1;
		}
	}
}
