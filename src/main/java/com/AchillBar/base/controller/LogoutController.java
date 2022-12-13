package com.AchillBar.base.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/logout")
@SessionAttributes({"LoginOK"}) 
public class LogoutController {

	@GetMapping
	public String logout(HttpSession session,SessionStatus status) {
		// 登出時執行下列兩敘述
		status.setComplete();		// 移除@SessionAttributes({"LoginOK"}) 標示的屬性物件
		session.invalidate();		// 此敘述不能省略		
		return "redirect:/";		// 跳轉回http://localhost:8080/Context_Path/
	}
}
