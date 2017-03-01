package com.mmz.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 测试Number
 * */
public class Test_Type {
	
	public static void main(String[] args) {
		Integer a=1;
		System.out.println(a instanceof Number); //true
		
		Double b=1.1;
		System.out.println(b instanceof Number); //true
		 
//		double c=1;
//		System.out.println(c instanceof Object);  instanceof左边必须是引用类型，也就是对象，基本类型不是
		
		List<Integer> d=new ArrayList<Integer>();
		System.out.println(d instanceof Collection);
		
		Set<Integer> f = new TreeSet<Integer>();
		System.out.println(f instanceof Collection);  // set也是collection
		
		Map<String,String> e=new HashMap<String,String>();
		System.out.println(e instanceof Collection);  // false Map不是collection的实现
		
		
	}

}
