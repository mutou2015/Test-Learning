package com.mmz.test.webservice;

import javax.jws.WebService;

@WebService
public class HelloWorldImpl implements HelloWorld {



	public String sayHello(String some) {
		// TODO Auto-generated method stub
		return some;
	}

}
