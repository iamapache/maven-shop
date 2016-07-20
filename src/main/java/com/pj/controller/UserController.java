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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pj.bean.User;
import com.pj.service.UserService;
//@RestController
@Controller
@RequestMapping("/user")
public class UserController {
	final private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userServiceImpl;
	
	@ModelAttribute("user")        //① 暴露表单引用对象为模型数据  
    public User getUser() {  
        return new User();  
    } 
	
	
	@RequestMapping("/registPage")
	public String registPage(){
		return "regist";
	}
	
	@RequestMapping("/findByName")
	public void selectByUsername(String username,HttpServletResponse response) throws IOException{
		User existUser = userServiceImpl.selectByUsername(username);
		logger.info(" 参数:{}", username);
		response.setContentType("text/html; charset=UTF-8");
		if(existUser !=null){
			response.getWriter().println(" <font color='red'>用户名已存在</font>");
		}
	}
	
	@RequestMapping("/regist")
	public String regist(@Validated @ModelAttribute("user")User user,BindingResult br,Model model){
		if(br.hasErrors()) {
			//如果有错误直接跳转到add视图
			return "regist";
		}
		 int i = userServiceImpl.insert(user);
		 model.addAttribute("AllErrors", br.getAllErrors());
		logger.info(" 参数:{}", i);
		return "login";
	}

	@RequestMapping("/login")
	public String login(){
		return "login";
	}
}
