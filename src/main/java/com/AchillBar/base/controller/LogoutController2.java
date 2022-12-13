package com.AchillBar.base.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/logout2")
@SessionAttributes({"LoginOK"}) 
public class LogoutController2 {

	@GetMapping
	public String logout(@PathParam("uri") String uri, HttpSession session,SessionStatus status,HttpServletRequest request, HttpServletResponse response) {
		// 清除Cookie
		Cookie cookieUser = new Cookie("email", null);
		Cookie cookiePassword = new Cookie("password", null);
		Cookie cookieRememberMe = new Cookie("rm",null);
		cookieUser.setMaxAge(0);
		cookiePassword.setMaxAge(0);
		cookieRememberMe.setMaxAge(0);
		cookieUser.setPath(request.getContextPath());
		cookiePassword.setPath(request.getContextPath());
		cookieRememberMe.setPath(request.getContextPath());
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
		System.out.println("===============================");
		System.out.println("LOGOUT"+request.getContextPath());
		System.out.println("redirect:/"+uri);
		System.out.println("===============================");
		// 登出時執行下列兩敘述
		status.setComplete();		// 移除@SessionAttributes({"LoginOK"}) 標示的屬性物件
		session.invalidate();		// 此敘述不能省略		




		return "redirect:"+uri;		// 跳轉回http://localhost:8080/Context_Path/
	}
}
