package com.AchillBar.base.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// 登出時需要做的事寫在這裡，如session.invalidate()
public class LogoutBean {
   
	private static Logger log = LoggerFactory.getLogger(LogoutBean.class);
	HttpSession session;
	
	public LogoutBean(HttpSession session) {
		this.session = session;
	}

	public LogoutBean() {
	}

	public HttpSession getSession() {
		return session;
	}
	
	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Integer getLogout() {
		memberModel mb = (memberModel)session.getAttribute("LoginOK");
		String memberName = "";
		Integer memberId =null;
		if (mb != null) {
			memberName = mb.getMemberName();
			memberId = mb.getM_id();
		} else {
			memberName = "(未知)";
			memberId = null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("使用者:" + memberId + memberName + "已於 " + sdf.format(new Date())  + " 登出...");
		session.invalidate();
		return 0;
	}
}
