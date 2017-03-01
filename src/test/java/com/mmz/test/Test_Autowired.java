package com.mmz.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:spring-test.xml"})  
public class Test_Autowired {
	
	@Autowired
	private compon c;
	
	@Test
	public void test1(){
		System.out.println(this.getClass().getClassLoader().getResource(""));
		
		c.getList().add("asd");
	}

}


@Component
class compon{
	
	
	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	
}
