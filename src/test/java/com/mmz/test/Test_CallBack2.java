package com.mmz.test;

import org.junit.Test;

import com.mmz.test.entiy.Noimpl;

public class Test_CallBack2 {
	
	public void get(Noimpl nip){
		nip.run();
	}

	@Test
	public void test() {
		
		Test_CallBack2 tc2= new Test_CallBack2();
		tc2.get(new Noimpl<String>() {
			
			public String run() {
				tc2_1();
				return "";
				
			}
		});
	}
	
	
	protected  void tc2_1(){
		System.out.println("被调用");
	}
}


