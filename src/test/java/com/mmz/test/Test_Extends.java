package com.mmz.test;

public class Test_Extends {

	public static void main(String[] args) {
		a aa=new a();
		b bb=new b();
		System.out.println(bb instanceof Object);
		System.out.println(Object.class.isAssignableFrom(aa.getClass()));
	}

}
class a{
	
}
class b extends a{
	
}
