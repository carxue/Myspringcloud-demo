package com.cloud.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -4662536295881297237L;
	private String name;
	private Integer age;

	public User() {
		super();
	}

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
}
