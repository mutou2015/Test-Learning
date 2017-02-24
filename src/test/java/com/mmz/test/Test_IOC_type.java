package com.mmz.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mmz.entity.Person;



public class Test_IOC_type {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		Person person=(Person) ctx.getBean("person");
		person.getAge();
	}

}
