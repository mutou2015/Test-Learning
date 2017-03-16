<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.js" ></script> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
<script >  
  
  function login(){
  	$.ajax({
  		url:"localhost:8080/spring_learning/login",
  		type:"post",
  		success:function(data){
  			if(data==2){
  				websocket.send("down");
  				$("#wel").hide();
  			}
  			else{
  				$("#wel").show();
  			}
  		}
  	});
  }
  $(function(){
	  login();
	  var websocket = null;  
      
      //判断当前浏览器是否支持WebSocket  
      if('WebSocket' in window){  
          websocket = new WebSocket("ws://localhost:8080/spring_learning/websocket");  
      }  
      else{  
          alert('Not support websocket')  
      }
      //连接发生错误的回调方法  
      websocket.onerror = function(){  
          setMessageInnerHTML("error");  
      };  
         
      //连接成功建立的回调方法  
      websocket.onopen = function(event){  
          setMessageInnerHTML("open");  
      }  
         
      //接收到消息的回调方法  
      websocket.onmessage = function(event){  
          setMessageInnerHTML(event.data);  
      }  
         
      //连接关闭的回调方法  
      websocket.onclose = function(){  
          setMessageInnerHTML("close");  
      }  
         
      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。  
      window.onbeforeunload = function(){  
          websocket.close();  
      }  
         
      //将消息显示在网页上  
      function setMessageInnerHTML(innerHTML){  
          document.getElementById('message').innerHTML += innerHTML + '<br/>';  
      }  
         
      //关闭连接  
      function closeWebSocket(){  
          websocket.close();  
      }  
         
      //发送消息  
      function send(){  
          var message = document.getElementById('text').value;  
          websocket.send(message);  
      }  
  });
      
  </script>  

</body>
</html>