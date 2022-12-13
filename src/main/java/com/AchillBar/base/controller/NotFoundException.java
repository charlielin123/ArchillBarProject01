package com.AchillBar.base.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotFoundException implements ErrorController{
    
    private static final String PATH = "/error";
      
    @RequestMapping(value = PATH)
    public ModelAndView error() {
        System.out.println("全局異常處理404");
        ModelMap mmp = new ModelMap();
        mmp.addAttribute("ex", "此網頁不存在");
        return new ModelAndView("errors.jsp",mmp);
    }
      
}
