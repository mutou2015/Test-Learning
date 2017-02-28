package com.mmz.test.token;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class Test_TokenController {
	
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/SaveDataController/show")
	    @Token(save=true)
	    public String saveData(HttpServletRequest request,HttpServletResponse response,String task_id){
	        
	        Map map = new HashMap();
	        System.out.println(task_id);
	        try {
	           // map = saveDataService.queryByTaskId(task_id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            map.put("task_id", task_id);
	            map.put("savetype", "");
	            map.put("memoryCodes", "1");
	            map.put("tablename", "");
	            map.put("trowkey", "");
	            map.put("columns", "");
	            map.put("indextablename", "");
	            map.put("irowkey", "");
	            map.put("icolumns", "");
	        } finally {
	            request.setAttribute("map", map);
	            return "savedata/index";
	        }
	    }
	 
	 @RequestMapping("/SaveDataController/saveData")
	    @ResponseBody
	    @Token(remove=true)
	    public void saveData(HttpServletRequest request,HttpServletResponse response,
	                         String tablename,String trowkey,String columns,
	                         String indextablename,String irowkey,String icolumns,
	                         String task_id,String savetype,String memoryCodes){
	        System.out.println(task_id);
	        //saveDataService.saveData(task_id,savetype,memoryCodes,tablename, trowkey, columns, indextablename, irowkey, icolumns);
	    }

}
