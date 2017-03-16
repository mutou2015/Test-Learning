package com.mmz.test.webservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class MainTestClient {

	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

	       factory.setServiceClass(HelloWorld.class);

	    factory.setAddress("http://localhost:8080/spring_learning/webservice/webservice/HelloWorld?wsdl");
	  
	   
	    HelloWorld client = (HelloWorld) factory.create();

	       String response = client.sayHello("hello,tsinghua!");

	       System.out.println("Response:" + response);

	}

}
