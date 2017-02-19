package com.mmz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mmz.dao.PersonDao;
import com.mmz.entity.Person;

@Service

public class TransactionService {
	@Autowired
	private PersonDao persondao;
	
	@Autowired
	private TransactionService2 transactionService2;
	
	/**
	 * test_requires_new():Propagation.REQUIRED; 
	 * transactionService2_insert()：Propagation.REQUIRES_NEW; 
	 * transactionService_insert():REQUIRED
	 * 
	 * transactionService2_insert()抛出异常，test_requires_new()捕获，transactionService_insert()接着执行
	 * transactionService_insert()插入成功
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void test_requires_new(){
		try{
			transactionService2.transactionService2_insert();
		}
		catch(RuntimeException e){
			
		}
		transactionService_insert();
		
	}
	
	/**
	 * test_requires_new2():Propagation.REQUIRED; 
	 * transactionService2_insert()：Propagation.REQUIRES_NEW; 
	 * transactionService_insert():REQUIRED
	 * 
	 * test_requires_new2()执行，创建事务
	 * transactionService2_insert()执行，挂起当前事务，创建新事务，抛出异常，回滚
	 * "Resuming suspended transaction after completion of inner transaction" test_requires_new2()事务恢复,接到异常回滚
	 * transactionService_insert()不能执行
	 * 
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void test_requires_new2(){
		
		transactionService2.transactionService2_insert();

		transactionService_insert();
		
	}
	
	/**
	 * test_requires_new3():Propagation.REQUIRED; 
	 * transactionService2_insert()：Propagation.REQUIRES_NEW; 
	 * transactionService_insert():Propagation.REQUIRES_NEW
	 * 
	 * test_requires_new3()执行，创建事务
	 * transactionService_insert()执行，没有创建事务，也没有加入事务，而是天然的在当前事务中运行！
	 * transactionService2_insert()执行 挂起当前事务，创建新事务，抛出异常，回滚
	 * "Resuming suspended transaction after completion of inner transaction" test_requires_new3()事务恢复,接到异常回滚
	 * transactionService_insert()执行的插入并没有提交，被回滚
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void test_requires_new3(){
		
		transactionService_insert();
		transactionService2.transactionService2_insert();
	}
	
	/**
	 * test_required_supports():Propagation.SUPPORTS; 
	 * transactionService2_insert()：Propagation.SUPPORTS; 
	 *
	 * 
	 * test_required_supports()执行，因为是SUPPORTS，没有创建事务
	 * transactionService2_insert()执行 并没有以事务执行，抛出异常，不回滚
	 * 程序中止
	 * transactionService2_insert()执行的插入成功
	 * 
	 * */
	@Transactional(propagation=Propagation.SUPPORTS)
	public void test_required_supports(){
		transactionService2.transactionService2_insert();
	}
	
	/**
	 * test_required_supports2():Propagation.REQUIRED; 
	 * transactionService2_insert()：Propagation.SUPPORTS; 
	 *
	 * 
	 * test_required_supports2()执行，创建事务
	 * transactionService2_insert()执行 加入事务
	 * 程序中止
	 * transactionService2_insert()执行的插入成功
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void test_required_supports2(){
		transactionService2.transactionService2_insert();
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void transactionService_insert(){
		Person person=new Person();
		person.setAge(1);
		person.setName("service");
		persondao.insert(person);
	}
	
	

}
