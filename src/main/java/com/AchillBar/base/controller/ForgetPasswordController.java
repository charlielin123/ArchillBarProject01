package com.AchillBar.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.service.ForgetPasswordService;
import com.AchillBar.base.service.memberService;
import com.AchillBar.base.util.GlobalService;

@Controller
@SessionAttributes({"forgetPassword","memberForget"})
public class ForgetPasswordController {
    @Autowired
    private ForgetPasswordService fgpService;
    @Autowired
    private memberService mService;
    
    @GetMapping("/forgetpassword")
    public String fgpPage(Model model) {
        return "fgp/verify.jsp";
    }
    
    
    @PostMapping("/sendVerify")
    @ResponseBody
    public Map<String, String> sendVerifyEmail(@RequestParam("email") String email,Model model) {
        memberModel mb = mService.findByEmail(email);
        HashMap<String, String> map = new HashMap<>();
        if(mb!=null) {
            model.addAttribute("memberForget",mb);
            try {
                String verifyString = fgpService.sendEmail(email);
                if(verifyString.equals("信件送出失敗")) {
                    map.put("sendFail", verifyString);
                }else {
                    model.addAttribute("forgetPassword", verifyString);
                    map.put("sendOK", "信件寄出成功");    
                }
                System.out.println(verifyString);
                return map;     
            } catch (Exception e) {
                map.put("unexpectError", e.getMessage());
                return map;
            }
        }else {
            map.put("emailError", "查無此信箱");
            return map;
        }
        
    }
    
    @PostMapping("/checkString")
    @ResponseBody
    public Map<String,String> checkRandomString(@RequestParam String randomString,
                                                Model model) {
        String rString = (String) model.getAttribute("forgetPassword");
        HashMap<String, String> map = new HashMap<>();
        //比對認證
        if(randomString.equals(rString)) {
            map.put("OK", "認證成功");
        }else {
            map.put("Fail", "認證失敗");   
        }  
        return map;       
    }
    
    @GetMapping("/setNewPassword")
    public String setPasswordPage(Model model) {
        System.out.println(model.getAttribute("memberForget"));
        return "fgp/setNewPassword.jsp";
    }
    
    @PostMapping("/setNewPasword")
    public String setNewpassword(@RequestParam("newpassword") String password,
                                Model model,
                                HttpSession httpSession,
                                SessionStatus status) {
        try {
            //更新資料庫
            memberModel mb = (memberModel) model.getAttribute("memberForget");
            mb.setPassword(GlobalService.encryptString(password));
            mService.insert(mb);
            System.out.println("更新密碼成功");
            //清除session
            status.setComplete();
            httpSession.invalidate();
            return "index.jsp";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.jsp";
        }
    }
}
