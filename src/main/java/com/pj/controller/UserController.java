package com.pj.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pj.bean.User;
import com.pj.service.UserService;
import com.pj.service.impl.UserServiceImpl;


@Controller
@RequestMapping("/user")
public class UserController {
	final private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/registPage")
	public String registPage(){
		return "regist";
	}
	
	@RequestMapping("/findByName")
	public void selectByUsername(String username,HttpServletResponse response) throws IOException{
		User existUser = userService.selectByUsername(username);
		logger.info(" 参数:{}", username);
		response.setContentType("text/html; charset=UTF-8");
		if(existUser !=null){
			response.getWriter().println("<font color='red'>用户名已存在</font>");
		}
	}
	
	@RequestMapping("/regist")
	public void regist(@Validated User user,BindingResult br){
		logger.info(" 参数:{}", userService.insert(user));
	}

}
