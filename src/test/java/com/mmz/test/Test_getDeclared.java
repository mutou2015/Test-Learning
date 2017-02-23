package com.mmz.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * 测试反射获取set方法，调用方法对属性进行赋值
 * */
public class Test_getDeclared {

	public static void main(String[] args) throws Exception {
		
		Class g=Class.forName("com.mmz.test.G");
		Method method=g.getDeclaredMethod("setE", E.class);
		E e=new E();
		G newg=(G) g.newInstance();
		method.setAccessible(true);
		method.invoke(newg, e);
		System.out.println(newg.getE().i);
		System.out.println("---------------------");
		
		Field field=g.getDeclaredField("e");
		field.setAccessible(true);
		field.set(newg, e);
		System.out.println(newg.getE().i);
	}

}

class E{
	public int i=0;
}
class G{
	private E e;

	public E getE() {
		return e;
	}

//	public void setE(E e) {
//		this.e = e;
//	}
	
	
	/**
	 * 通过getDeclaredMethods方法输出的是自身的public、protected、private方法
	 * 当方法为私有，必须method.setAccessible(true);
	 * Class com.mmz.test.Test_getDeclared can not access a member of class com.mmz.test.G with modifiers "private"
	 * 调用getMethods方法输出的是自身的public方法和父类Object的public方法。
	 * */
	private void setE(E e){
		this.e = e;
	}
	
}
