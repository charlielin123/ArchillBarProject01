package com.AchillBar.base.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.service.memberService;
import com.AchillBar.base.util.GlobalService;

@Controller
@SessionAttributes({ "LoginOK" })
public class PasswordController {
    @Autowired
    private memberService memberService;

    @GetMapping("/updatepassword")
    public String changePasswordPage() {
        return "member/changePassword.jsp";
    }

    @PostMapping("/updatepassword/{id}")
    public String changePassword(
            @PathVariable("id") Integer id,
            @RequestParam("memberName") String name,
            @RequestParam("oldpassword") String oldpassword,
            @RequestParam("newpassword") String newpassword,
            @RequestParam("sex") String sex,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
            @RequestParam("regDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date regDate,
            Model model) {
        memberModel temp = memberService.findMemberById(id);
        String tempPassword = GlobalService.decryptString(GlobalService.KEY, temp.getPassword());
        if (tempPassword.equals(oldpassword)) {
            temp.setPassword(GlobalService.encryptString(newpassword));
            memberService.insert(temp);
            // 更新session中的值
            model.addAttribute("LoginOK", temp);
            return "member/updateSuccess.jsp";
        } else {
            return "member/updatefail.jsp";
        }

    }

}
