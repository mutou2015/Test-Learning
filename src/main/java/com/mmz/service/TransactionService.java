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
	 * transactionService2_insert()抛出异常
	 * 程序中止
	 * transactionService2_insert()执行的插入失败
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void test_required_supports2(){
		transactionService2.transactionService2_insert();
	}
	
	/**
	 * test_requires_new_nested():Propagation.REQUIRED; 
	 * transactionService2_insert()：Propagation.REQUIRES_NEW; 
	 *
	 * 
	 * test_requires_new_nested()执行，创建事务
	 * transactionService2_insert()执行 挂起当前事务 创建新事务，执行完毕提交
	 * transactionService_insert();执行
	 * test_requires_new_nested()继续执行，抛出异常回滚
	 * 程序中止
	 * transactionService_insert()执行的插入失败，transactionService2_insert()执行的插入成功
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void test_requires_new_nested(){
		transactionService2.transactionService2_insert();
		transactionService_insert();
		throw new RuntimeException();
	}
	
	/**
	 * test_requires_new_nested():Propagation.REQUIRED; 
	 * transactionService2_insert()：Propagation.NESTED; 
	 *
	 * 
	 * test_requires_new_nested()执行，创建事务
	 * transactionService2_insert()执行 挂起当前事务 创建回滚点，创建子(嵌套)事务(Creating nested transaction)，执行完毕，释放回滚点
	 * transactionService_insert();执行
	 * test_requires_new_nested()继续执行，抛出异常回滚
	 * 程序中止
	 * transactionService_insert()，transactionService2_insert执行的插入都失败，因为nested不会立刻提交，而是和父事务一起提交和回滚
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void test_requires_new_nested2(){
		transactionService2.transactionService2_insert();
		transactionService_insert();
		throw new RuntimeException();
	}
	
	/**
	 * test_requires_new_nested():Propagation.SUPPORTS; 
	 * transactionService2_insert()：Propagation.MANDATORY; 
	 * test_requires_new_nested()执行，没有创建事务
	 * transactionService2_insert() mandatory必须在事务中运行，否则抛出异常，要注意的是，supports即使抛出异常，如果没在事务中执行，异常之前
	 * 的操作仍然会提交，但是mandatory不会，在执行之前检测到不在事务中，则立即报错！
	 * transactionService_insert() 继续抛异常
	 * 
	 * 程序中止
	 * transactionService2_insert()插入显然不会成功
	 * 
	 * */
	@Transactional(propagation=Propagation.SUPPORTS)
	public void test_mandatory(){
		transactionService2.transactionService2_insert();
		
	}
	
	/**
	 * test_not_supported():Propagation.REQUIRED; 
	 * transactionService2_insert()：Propagation.NOT_SUPPORTED; 
	 * transactionService2_insert2(); Propagation.REQUIRED; 
	 * test_requires_new_nested()执行，创建事务
	 * transactionService2_insert2(); 加入事务
	 * transactionService2_insert(); 执行，挂起当前事务，切换为自动提交
	 * test_not_supported() 恢复事务，执行抛异常，回滚
	 * 程序中止
	 * transactionService2_insert()()插入成功，transactionService2_insert2()因为在主事务中，被回滚
	 * 
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void test_not_supported(){
		transactionService2.transactionService2_insert2();
		transactionService2.transactionService2_insert();
		throw new RuntimeException();
		
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void transactionService_insert(){
		Person person=new Person();
		person.setAge(1);
		person.setName("service");
		persondao.insert(person);
	}
	
	

}
