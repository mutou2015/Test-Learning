package com.mmz.dao;

import org.springframework.stereotype.Component;

import com.mmz.entity.Person;

@Component
public interface PersonDao {
	
	public void insert(Person person);
	
	public Person get(String name);

}
