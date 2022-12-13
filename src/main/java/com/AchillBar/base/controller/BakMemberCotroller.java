package com.AchillBar.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.model.dao.memberDao;
import com.AchillBar.base.service.memberService;

@RestController
@SessionAttributes({ "LoginOK" })
@RequestMapping("/bak/member")
public class BakMemberCotroller {
	@Autowired
	memberService mService;
	@Autowired
	memberDao mdao;

	// 只找客戶會員名單
	@GetMapping("/findAllmember/{pageNumber}")
	public Page<memberModel> findAllMember(@PathVariable Integer pageNumber) {
		Page<memberModel> memberList = mService.findAllCustomer(pageNumber);
		return memberList;
	}

	// 刪除後臺管理會員
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable Integer id) {
		memberModel mb = mService.findMemberById(id);
		if (mb != null) {
			try {
				mService.deleteById(id);
				return "刪除成功";
			} catch (Exception e) {
				e.printStackTrace();
				return "刪除失敗";
			}
		}
		return "查無此會員";
	}

	@GetMapping("/phone")
	public List<Map<String, Object>> findMemberByPhone(@RequestParam("phone") String phone) {
		List<memberModel> mList = mdao.findByPhone(phone);
		List<Map<String, Object>> res = new ArrayList<>();
		for (memberModel memberModel : mList) {
			Map<String, Object> map = new HashMap<>();
			map.put("m_id", memberModel.getM_id());
			map.put("memberName", memberModel.getMemberName());
			res.add(map);
		}

		return res;
	}

}
