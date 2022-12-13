package com.AchillBar.base.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.Comment;
import com.AchillBar.base.model.dao.BookingDao;
import com.AchillBar.base.model.dao.CommentDao;
import com.AchillBar.base.model.dao.OrderDetailDao;
import com.AchillBar.base.model.dao.memberDao;
import com.AchillBar.base.service.CommentService;
import com.AchillBar.base.service.bookingService;

@Controller
@RequestMapping("/bak")
@SessionAttributes({ "LoginOK" })
public class BakController {

    @Autowired
    BookingDao bDao;

    @Autowired
    bookingService bService;

    @Autowired
    OrderDetailDao odDao;

    @Autowired
    memberDao mdao;

    @Autowired
    CommentDao cDao;

    @Autowired
    CommentService cService;

    @ResponseBody
    @GetMapping("/check")
    public List<Map<String, Object>> getCheck(@RequestParam("tableNum") Integer tableNum,
            @RequestParam("date") String date) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<Map<String, Object>> check = bDao.getCheckByDateAndTable(tableNum, sdf.parse(date));
            return check;

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return null;
    }

    @GetMapping("/comment/delete/{com_id}")
    public String delByManger(@PathVariable Long com_id) {

        Comment res = cDao.findByC_id(com_id);

        if (res != null) {
            cService.deleteById(com_id);
            return "redirect:/bak/comm";
        } else {
            return "redirect:/bak/comm";
        }
    }

}
