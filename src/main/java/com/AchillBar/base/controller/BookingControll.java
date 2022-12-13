package com.AchillBar.base.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.Booking;
import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.model.dao.BookingDao;

@RestController
@RequestMapping("booking")
@SessionAttributes({ "LoginOK" })
public class BookingControll {

    @Autowired
    BookingDao bDao;

    @ResponseBody
    @Transactional
    @PostMapping("/book")
    public Map<String, Object> newBooking2(@RequestBody Map<String, Object> book, Model model) {
        Map<String, Object> res = new HashMap<>();
        Integer mId = 0;
        memberModel md = (memberModel) model.getAttribute("LoginOK");
        mId = md.getM_id();
        if (mId == 0) {
            res.put("error", "未登入");
            return res;
        }

        String phone = (String) book.get("phone");
        Integer ppl = Integer.parseInt((String) book.get("ppl"));
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = (Date) sdf.parse((String) book.get("date"));
            if (checkBookNum(date, mId) + ppl > 8) {
                res.put("error", "人數超過，請洽店家");
                return res;

            }

            List<String> tableList = (List<String>) book.get("tableList");
            for (int i = 0; i < tableList.size(); i++) {
                Booking booking = new Booking();
                booking.setBookDate(date);
                booking.setPhoneNum(phone);
                booking.setM_id(mId);
                booking.setNumOfPpl(ppl);
                Integer num = Integer.parseInt(tableList.get(i));
                if (bDao.findByTableNumAndDate(num, date).size() != 0) {
                    res.put("error", "此位置已被搶走，請換位置試試");
                    return res;

                }

                booking.setTableNum(num);
                bDao.save(booking);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            res.put("error", "訂位失敗，請重試看看");
            return res;

        }

        res.put("success", "訂位成功");
        return res;

    }

    @ResponseBody
    @GetMapping("/bookdate")
    public List<Booking> getBookingByPid(Model model) {
        if (model.getAttribute("LoginOK") != null) {
            memberModel md = (memberModel) model.getAttribute("LoginOK");
            return bDao.findByMidAfterToday(md.getM_id());
        }
        return null;
    }

    @ResponseBody
    @GetMapping("/status/date/{date}")
    public List<Map<String, Object>> getBookingByDate(@PathVariable String date) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> res = new ArrayList<>();
        try {
            List<Booking> bookList = bDao.findByBookDate(sdf.parse(date));
            for (Booking book : bookList) {
                Map<String, Object> map = new HashMap<>();
                map.put("table", book.getTableNum());
                res.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return res;
    }

    public Integer checkBookNum(Date date, Integer m_id) {
        List<Booking> pre = bDao.findByMidAndDate(m_id, date);

        if (pre.size() == 0) {
            return 0;
        }
        Integer num = 0;
        for (int i = 0; i < pre.size(); i++) {

            int tablenum = pre.get(i).getTableNum();
            if (tablenum <= 10) {
                num += 1;
            } else if (tablenum <= 12) {
                num += 4;
            } else {
                num += 2;
            }

        }

        return num;
    }
}
