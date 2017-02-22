package com.mmz.test;

public class Test_CallBack {

	public static void main(String[] args) {
		D d=new D();
		// 创建c有一定的扩展性,只要实现了c接口，都可以被注入进来在一定的时候被调用
		C c=new F();
		d.d(c);

	}

}

interface C{
	void c();
}

class D{
	void d(C c){
		
		c.c();
	}
}
class F implements C{

	public void c() {
		System.out.println("--");
		
	}	
}
