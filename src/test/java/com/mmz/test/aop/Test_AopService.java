package com.mmz.test.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring-test.xml"})  
@Service(value="testAopService")
public class Test_AopService {
	
	// 不知什么原因，junit测试aop不成功
	@Test
	public void test(){
		System.out.println("the method is invoked");
	}
	
	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-test.xml");
		 Test_AopService tx = (Test_AopService) ctx.getBean("testAopService");
		 tx.test();
	}

}
