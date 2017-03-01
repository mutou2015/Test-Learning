package com.mmz.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.mmz.entity.Person;

public class Test_reflect_constructor {
	
	public static void main(String[] args) {
		try {
			Class clz=Class.forName("com.mmz.entity.Person");
			for(Constructor method:clz.getConstructors()){
				Person p=(Person)method.newInstance(null);
				System.out.println(p);
			}
				
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
