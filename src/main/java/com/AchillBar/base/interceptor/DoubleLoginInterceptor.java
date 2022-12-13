package com.AchillBar.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.AchillBar.base.model.memberModel;

@Component
public class DoubleLoginInterceptor implements HandlerInterceptor {
	
	String servletPath;
	String contextPath;
	String requestURI;
	
	//返回true程序繼續運行；false阻擋後續程序運行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		servletPath = request.getServletPath();  
		contextPath = request.getContextPath();
		requestURI  = request.getRequestURI();
		
		if(checkLogin(request)) {
			//已經登入，不能再進入登入畫面
			response.sendRedirect(contextPath);
			return false;
		}else {
			//尚未登入，進入登入畫面
			return true;
		}
		
	}
	
	// 判斷Session物件內是否含有識別字串為LoginOK的屬性物件，如果有，表示已經登入，否則尚未登入
	private boolean checkLogin(HttpServletRequest req) {
				HttpSession session = req.getSession();
				
				memberModel loginToken = (memberModel) session.getAttribute("LoginOK");
				if (loginToken == null) {
					return false;
				} else {
					return true;
				}
	}

}
