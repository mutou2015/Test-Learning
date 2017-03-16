package com.mmz.test.webservice;

import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;





public class MyHeader extends AbstractSoapInterceptor {

	private static String nameURI="http://localhost:8080/spring_learning/webservice/webservice/HelloWorld?wsdl";
	
	public MyHeader(){   
        super(Phase.WRITE);   
    }   
	
	public void handleMessage(SoapMessage message) throws Fault {
		 String spPassword="test";   
         String spName="123456";   
            
         QName qname=new QName("RequestSOAPHeader");   
         Document doc=DOMUtils.createDocument();   
         //自定义节点
         Element spId=doc.createElement("username");   
         spId.setTextContent(spName);   
         //自定义节点
         Element spPass=doc.createElement("password");   
         spPass.setTextContent(spPassword);   
            
         Element root=doc.createElementNS(nameURI, "header");   
         root.appendChild(spId);   
         root.appendChild(spPass);   
            
         SoapHeader head=new SoapHeader(qname,root);   
         List<Header> headers=message.getHeaders();   
         headers.add(head);   
         System.out.println(">>>>>添加header<<<<<<<");
     }   
		
	

	
}
