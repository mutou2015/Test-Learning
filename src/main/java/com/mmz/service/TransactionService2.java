package com.mmz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mmz.dao.PersonDao;
import com.mmz.entity.Person;

@Service

public class TransactionService2 {
	
	@Autowired
	private PersonDao persondao;
	
	public Person get(String name){
		
		Person person= persondao.get(name);
		
		return person;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void transactionService2_insert(){
		Person person=new Person();
		person.setAge(1);
		person.setName("service2");
		persondao.insert(person);
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void transactionService2_insert2(){
		Person person=new Person();
		person.setAge(1);
		person.setName("service22");
		persondao.insert(person);
		
	}

}
