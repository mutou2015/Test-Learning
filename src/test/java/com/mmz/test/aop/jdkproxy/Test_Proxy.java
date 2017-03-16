package com.mmz.test.aop.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test_Proxy implements InvocationHandler{

	private Subject subject;
	
	
	
	public Test_Proxy(Subject subject) {
		super();
		this.subject = subject;
	}

	public static void main(String[] args) {
		Subject sb = new RealSubject();
		// 真实对象作为成员变量被赋值
		Test_Proxy tp = new Test_Proxy(sb);
		// classloader给InvocationHandler的classloader，真实对象的接口数组，InvocationHandler
		Subject proxySb = (Subject) Proxy.newProxyInstance(tp.getClass().getClassLoader(), sb.getClass().getInterfaces(), tp);
		proxySb.aa();

	}
	/**
	 * @param proxy 代理对象
	 * @param method 真实对象被调用，或者说被增强的真实方法
	 * @param args 被调用方法的参数
	 * */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("before");
		method.invoke(subject, args);
		System.out.println(subject+"----"+proxy.getClass());
		System.out.println("after");
		return null;
	}

}
