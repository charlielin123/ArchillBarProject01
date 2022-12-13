package com.AchillBar.base.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.service.memberService;
import com.AchillBar.base.util.GlobalService;

@Controller
@SessionAttributes({ "LoginOK" })
public class memberController {

	@Autowired
	private memberService mService;

	@GetMapping("/member/signup")
	public String signupMember(Model model) {
		return "/member/signup.jsp";
	}

	@PostMapping("/member/insert")
	public String insertMember(
			@RequestParam("memberName") String name,
			@RequestParam("password") String password,
			@RequestParam("sex") String sex,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
			@RequestParam("photo") MultipartFile photo) {

		memberModel m1 = new memberModel();

		try {
			m1.setMemberName(name);
			m1.setPassword(GlobalService.encryptString(password));
			m1.setSex(sex);
			m1.setPhone(phone);
			m1.setEmail(email);
			m1.setBirthday(birthday);
			m1.setPhoto(photo.getBytes());
			m1.setAdmin(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mService.insert(m1);
		return "member/signupSuccess.jsp";
	}

	// 信箱資料驗證
	@GetMapping("/checkemail")
	@ResponseBody
	public String checkEmail(@RequestParam String email) {
		String regex = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
		Pattern p = Pattern.compile(regex);
		if (email != "" && email != null) {
			if (p.matcher(email).find()) {
				memberModel mb = mService.findByEmail(email);

				if (mb != null) {
					return "此信箱已被註冊";
				} else {
					return "OK";
				}
			} else {
				return "信箱格式錯誤";
			}
		} else {
			return "信箱不可為空";
		}
	}

	@GetMapping("/member/isLongin")
	@ResponseBody
	public memberModel insertMember2(Model model) {
		if (model.getAttribute("LoginOK") == null) {
			return null;
		}

		memberModel member = (memberModel) model.getAttribute("LoginOK");
		member.setPassword("null");
		return member;
	}

	@GetMapping("/member/viewmember")
	public String showMemberData() {
		return "member/viewMember.jsp";
	}

	@GetMapping("/viewPhoto/{id}")
	public ResponseEntity<byte[]> viewPhoto(@PathVariable("id") Integer id) {
		memberModel mb = mService.findMemberById(id);
		byte[] photo = mb.getPhoto();
		// 變更contentType
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		// 要回傳的物件,headers,HttpStatus回應
		return new ResponseEntity<byte[]>(photo, headers, HttpStatus.OK);
	}

	@GetMapping("/member/editmember")
	public String editMemberData() {
		return "member/editMember.jsp";
	}

	@GetMapping("/member/deletemember/{id}")
	public String deleteMemberData(@PathVariable("id") Integer id, HttpSession session, SessionStatus status) {
		mService.deleteById(id);
		// 帳號刪除後順便登出移除session
		status.setComplete();
		session.invalidate();
		return "index.jsp";
	}

	@PostMapping("/member/update/{id}")
	public String updateMember(
			@PathVariable("id") Integer id,
			@RequestParam("memberName") String name,
			@RequestParam("sex") String sex,
			@RequestParam("phone") String phone,
			@RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
			@RequestParam("photo") MultipartFile photo,
			Model model) {

		memberModel m1 = mService.findMemberById(id);
		try {
			m1.setMemberName(name);
			m1.setSex(sex);
			m1.setPhone(phone);
			m1.setBirthday(birthday);
			m1.setUpdateDate(new Date());
			if (!(photo.isEmpty()) && photo.getSize() != 0) {
				m1.setPhoto(photo.getBytes());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		mService.insert(m1);
		// 更新session中的值
		model.addAttribute("LoginOK", m1);
		return "member/updateSuccess.jsp";
	}

}
