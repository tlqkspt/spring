package com.company.app.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.app.user.UserVO;
import com.company.app.user.service.UserService;

@Controller
public class LoginController {
	
	@Autowired UserService userService;
	
	//로그인폼 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)	//@RequestMapping("loginForm")
	public String loginForm() {
		return "user/login";
	}
	
	//로그인처리
	@RequestMapping(value = "login", method = RequestMethod.POST)  //@RequestMapping("login")
	public String login(@ModelAttribute("user") UserVO vo, HttpSession session) {
		//@ModelAttribute("user") user라고 바꿔줌
		//model.addAttribute("userVO", vo); 를 자동으로해준다
		UserVO user = userService.login(vo);
		if(user == null) {
			return "user/login";
		} else {
			//세션에 저장 목록으로 페이지이동
			session.setAttribute("user", user);
			session.setAttribute("id", user.getId());
			return "redirect:getBoardMap";
		}
	}
	
	//로그아웃처리
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();	//세션무효화
		return "redirect:login";
	}
}
