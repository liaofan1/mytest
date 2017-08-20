package cn.itheima.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	// 实现登录页面跳转
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		return "user/login";	
	}
	
	// 实现用户登录
	@RequestMapping("/login.do")
	public String login(String username,String userpwd,HttpSession session){
		// 1.如果用户名称，密码不为空，就认为是合法的用户
		if (username!=null && userpwd!=null) {
			session.setAttribute("user", username);
		}else{
			return "user/login";
		}
		return "redirect:/queryItem.do";	
	}

}
