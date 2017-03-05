package com.mmz.test;

import org.junit.Test;

public class Test_generic<T> {
	
	/**
	 * 测试泛型以及可变参数
	 * */
	public void out(T...args){
		 for (T t : args) {
	            System.out.println(t);
	        }
	}
	
	@Test
	public  void test(){
		Test_generic<Integer> tg= new Test_generic<Integer>();
		tg.out(1,2,3);
	}
	
	public static void main(String[] args) {
		new Test_generic<Integer>().out(1,2,3);
	}

}



