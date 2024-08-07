package org.chobit.core;


import org.chobit.apt.ToJsonString;

@ToJsonString
public class Student {


	private String name;


	private String no;


	private int age;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", no='" + no + '\'' +
				", age=" + age +
				'}';
	}
}
