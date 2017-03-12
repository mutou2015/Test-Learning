package com.mmz.test.aop;

import org.springframework.stereotype.Component;

@Component(value="testXmlAop")
public class Test_XmlAop {
	
	public void before(){
		System.out.println("before method invoke========================================");
	}
	
	public void after(){
		System.out.println("after method invoke");
	}

}
