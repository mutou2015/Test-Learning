package com.mmz.test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

public class Test_filepath {

	public static void main(String[] args) throws IOException {
		Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources("com");
		 URL url = dirs.nextElement();

		System.out.println(URLDecoder.decode(url.getFile(), "UTF-8"));

		System.out.println("com.mmz.test.Person".split(".")[0]);// 不行，得用\\.转义
	}

}
