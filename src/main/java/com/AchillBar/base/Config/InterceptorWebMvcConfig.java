package com.AchillBar.base.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.AchillBar.base.interceptor.AdminCheck;
import com.AchillBar.base.interceptor.DoubleLoginInterceptor;
import com.AchillBar.base.interceptor.LoginInterceptor;


@Configuration
public class InterceptorWebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private LoginInterceptor loginInterceptor;
	@Autowired
	private DoubleLoginInterceptor dloginInterceptor;
	@Autowired
	private AdminCheck admincheck;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    //攔截做登入
		registry.addInterceptor(loginInterceptor).addPathPatterns("/member/**", "/booking/**","/myOrder/**")
				.excludePathPatterns("/member/signup", "/member/insert");

		registry.addInterceptor(dloginInterceptor).addPathPatterns("/login","/login2");

		registry.addInterceptor(admincheck).addPathPatterns("/bak/**");
	}
	
	
	
}
