package com.mmz.test.webservice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage>{

	public AuthInterceptor() {
		super(Phase.PRE_INVOKE);
		// TODO Auto-generated constructor stub
	}

	public void handleMessage(SoapMessage message) throws Fault {
			List<Header> headers = message.getHeaders();
		
		// 如果没有Header
		if (headers == null || headers.size() < 1) {
			throw new Fault(new IllegalArgumentException("用户名或密码错误!"));
		}
		Header firstHeader = headers.get(0);
		Element ele = (Element) firstHeader.getObject();

		NodeList usernameList = ele.getElementsByTagName("username");
		NodeList passwordList = ele.getElementsByTagName("password");

		if (usernameList.getLength() != 1) {
			throw new Fault(new IllegalArgumentException("用户名或密码错误!"));
		}

		if (passwordList.getLength() != 1) {
			throw new Fault(new IllegalArgumentException("用户名或密码错误!"));
		}

		// 获取元素的文本内容
		String username = usernameList.item(0).getTextContent();
		String password = passwordList.item(0).getTextContent();

		if (!username.equals("test") || !password.equals("123456")) {
			throw new Fault(new IllegalArgumentException("用户名或密码错误!"));
		}
		
		//ServletInputStream sis=null;
       /* try {  
            HttpServletRequest request = (HttpServletRequest) msg.get(  
                    AbstractHTTPDestination.HTTP_REQUEST);//这句可以获取到request  
            request.setCharacterEncoding("UTF-8");
            BufferedReader reader = request.getReader(); 
            String input = null; 
            StringBuffer requstBody = new StringBuffer(""); 
            while ((input = reader.readLine()) != null) { 
            	requstBody.append(input); 
            }
            System.out.println(requstBody);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  */
        
        InputStream sis = message.getContent(InputStream.class);  
        
         
        
        
        final int BUFFER_SIZE = 8 * 1024;  
        byte[] buffer = new byte[BUFFER_SIZE];  
        int length = 0;  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
      
        do {  
            try {
            	
				length = sis.read(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            if (length > 0) {  
                baos.write(buffer, 0, length);  
            }  
        } while (length * 2 == BUFFER_SIZE);  
      
        String bodyData="";
		try {
			bodyData = new String(baos.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println(bodyData);
	}

	

}
