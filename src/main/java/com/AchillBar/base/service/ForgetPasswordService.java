package com.AchillBar.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service

public class ForgetPasswordService {
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String consignee) {
        // 生成亂數字串6碼
        String theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        for (int m = 0; m < 6; m++) {
            int index = (int) (theAlphaNumericS.length() * Math.random());
            builder.append(theAlphaNumericS.charAt(index));
        }
        String randomString = builder.toString();
        // 將亂數字串寄出
        SimpleMailMessage message = new SimpleMailMessage();
        // 設定寄件人
        message.setFrom("charlietest39@outlook.com");
        // 設定收件人
        message.setTo(consignee);
        // 設定信件主旨
        message.setSubject("找回ACB密碼");
        // 設定信件內容
        message.setText("驗證碼:" + randomString);
        try {
            mailSender.send(message);
            System.out.println("信件送出");
            return randomString;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "信件送出失敗";
        }
    }

}
