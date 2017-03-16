package com.mmz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class Login {
	private Integer flag=0;
	
	@RequestMapping("/login")
	public Integer login(){
		this.flag++;
		return flag;
	}

}
