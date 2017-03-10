package com.mmz.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Test_somePaths {
	
	public static void main(String[] args) {
		Test_somePaths muDemo = new Test_somePaths();
        try {
            muDemo.showURL();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void showURL() throws IOException {

        // 第一种：获取类加载的根路径   C:\Users\Administrator\git\Test-Learning\target\test-classes
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  C:\Users\Administrator\git\Test-Learning\target\test-classes\com\mmz\test
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println(f2);

        // 第二种：获取项目路径    C:\Users\Administrator\git\Test-Learning
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);


        // 第三种：  file:/C:/Users/Administrator/git/Test-Learning/target/test-classes/
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(xmlpath);


        // 第四种： C:\Users\Administrator\git\Test-Learning
        System.out.println(System.getProperty("user.dir"));
        /*
         * 结果： C:\Documents and Settings\Administrator\workspace\projectName
         * 获取当前工程路径
         */

        // 第五种：  获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));

    }

}
