package com.mmz.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;

import com.mmz.dao.PersonDao;
import com.mmz.entity.Person;
import com.mmz.service.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring-mvc.xml","classpath:spring.xml","classpath:spring-mybatis.xml"})  
public class Test_transaction_propagation {
	@Autowired
	private TransactionService transactionService;
	
	@Test
	public void test_requires_new() {
		
		transactionService.test_requires_new();
	}
	
	@Test
	public void test_requires_new2() {
		
		transactionService.test_requires_new2();
	}
	
	@Test
	public void test_requires_new3() {
		
		transactionService.test_requires_new3();
	}
	
	@Test
	public void test_required_supports() {
		
		transactionService.test_required_supports();
	}
	
	@Test
	public void test_required_supports2() {
		
		transactionService.test_required_supports2();
	}
	
	@Test
	public void test_requires_new_nested() {
		
		transactionService.test_requires_new_nested();
	}
	
	@Test
	public void test_requires_new_nested2() {
		
		transactionService.test_requires_new_nested2();
	}
	
	@Test
	public void test_mandatory() {
		
		transactionService.test_mandatory();
	}

	@Test
	public void test_not_supported() {
		
		transactionService.test_not_supported();
	}
	
}
