package org.chobit.core;

import org.chobit.apt.ToJson;

/**
 * 注解测试类
 *
 * @author robin
 */
@ToJson
public class Person {


	private String name;

	private int age;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
