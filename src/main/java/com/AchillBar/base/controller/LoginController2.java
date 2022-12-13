package com.AchillBar.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.LoginBean;
import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.service.memberService;
import com.AchillBar.base.util.GlobalService;
import com.AchillBar.base.validator.LoginBeanValidator;

@Controller
@RequestMapping("/login2")
@SessionAttributes({ "LoginOK" })
public class LoginController2 {

	String loginForm = "member/loginForm.jsp";
	String loginOut = "member/logout.jsp";

	memberService memberService;

	@Autowired
	public LoginController2(memberService memberService) {
		this.memberService = memberService;
	}

	// 檢查COOKIE是否有登入資訊
	@ResponseBody
	@GetMapping
	public Map<String, Object> login00(HttpServletRequest request, Model model,
			@CookieValue(value = "email", required = false) String email,
			@CookieValue(value = "password", required = false) String password,
			@CookieValue(value = "rememberMe", required = false) Boolean rm) {

		Map<String, Object> res = new HashMap<>();
		if (email == null)
			email = "";

		if (password == null) {
			password = "";
		} else {
			// password = GlobalService.decryptString(GlobalService.KEY, password);
		}

		if (rm != null) {
			rm = Boolean.valueOf(rm);
		} else {
			rm = false;
		}

		memberModel mb = memberService.findByEmailAndPassword(email, password);
		if (mb != null) {
			model.addAttribute("LoginOK", mb);
			mb.setPassword("password");
			res.put("Member", mb);
			return res;
		}
		res.put("error", "沒有紀錄");
		return res;

	}

	@ResponseBody
	@PostMapping
	public Map<String, Object> checkAccount(@RequestBody LoginBean bean,
			BindingResult result, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> res = new HashMap<>();
		LoginBeanValidator validator = new LoginBeanValidator();
		validator.validate(bean, result);
		if (result.hasErrors()) {
			res.put("error", "信箱&密碼不可為空");
			return res;
		}
		String password = bean.getPassword();
		memberModel mb = null;
		try {
			// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
			mb = memberService.findByEmailAndPassword(bean.getEmail(), GlobalService.encryptString(password));

			if (mb != null) {
				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
				model.addAttribute("LoginOK", mb);

			} else {
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
				result.rejectValue("invalidCredentials", "", "該帳號不存在或密碼錯誤");
				res.put("error", "該帳號不存在或密碼錯誤");
				return res;
			}
		} catch (RuntimeException ex) {
			result.rejectValue("invalidCredentials", "", ex.getMessage());
			ex.printStackTrace();
			res.put("invalidCredentials", ex.getMessage());
			return res;
		}


		 // 判斷是否為管理員
		 if (mb.getAdmin()) {
		 	// 存cookie
		 	processCookies(bean, request, response);
		 	mb.setPassword("password");
		 	res.put("Member", mb);
		 	res.put("admin", true);
		 	return res;
		 	// 在這裡加上後台管理試圖的路徑
		 } else {
		 	processCookies(bean, request, response);
		 	mb.setPassword("password");
		 	res.put("Member", mb);
		 	res.put("admin", false);
		 	return res;
		 }
	}

	private void processCookies(LoginBean bean, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		String email = bean.getEmail();
		String password = bean.getPassword();

		// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
		if (bean.isRememberMe()) {
			cookieUser = new Cookie("email", email);
			cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
			cookieUser.setPath(request.getContextPath());
			
			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
		} else { // 使用者沒有對 RememberMe 打勾
			cookieUser = new Cookie("email", email);
			cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
		}
		// System.out.println("===============================");
		// System.out.println("LOGIN"+request.getContextPath());
		// System.out.println("===============================");
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);

	}
}
