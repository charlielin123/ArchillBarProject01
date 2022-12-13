package com.AchillBar.base.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.Booking;
import com.AchillBar.base.model.dao.BookingDao;
import com.AchillBar.base.service.bookingService;

@RestController
@RequestMapping("bak/booking")
@SessionAttributes({ "LoginOK" })
public class BakBookingCotroller {

    @Autowired
    BookingDao bDao;
    @Autowired
    bookingService bService;

    @PostMapping("/book")
    public boolean book(@RequestBody Map<String, Object> book) {
        Integer mId = Integer.parseInt((String) book.get("m_id"));
        String phone = (String) book.get("phone");

        // Integer ppl = Integer.parseInt((String) book.get("ppl"));
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = (Date) sdf.parse((String) book.get("date"));
            List<String> tableList = (List<String>) book.get("tableList");
            for (int i = 0; i < tableList.size(); i++) {
                Booking booking = new Booking();
                booking.setBookDate(date);
                booking.setPhoneNum(phone);
                booking.setM_id(mId);
                // booking.setNumOfPpl(ppl);
                Integer num = Integer.parseInt(tableList.get(i));
                if (bDao.findByTableNumAndDate(num, date).size() != 0) {
                    return false;
                }

                booking.setTableNum(num);
                bDao.save(booking);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @GetMapping("/all")
    @ResponseBody
    public Map<String, List<Booking>> allbooking() {
        List<Booking> all = bDao.findAll();

        HashMap<String, List<Booking>> rs = new HashMap<>();
        rs.put("data", all);

        return rs;
    }

    @GetMapping("/delect/{id}")
    @ResponseBody
    public String deletebyid(@PathVariable Long id) {
        try {
            bDao.deleteById(id);

        } catch (Exception e) {
            return "刪除失敗";
        }
        return "刪除成功";
    }

    @GetMapping(value = "/date/{date}")
    public Map<Integer, Booking> getMethodName(@PathVariable String date) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<Integer, Booking> res = new HashMap<>();
        try {
            List<Booking> bookList = bDao.findByBookDate(sdf.parse(date));
            for (Booking booking : bookList) {
                res.put(booking.getTableNum(), booking);
            }

            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/changeStatus")
    public boolean changeBookStatus(@RequestParam Long b_id, Integer status) {
        try {
            bDao.editBookingStatusById(b_id, status);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("/page")
    public Page<Booking> orderpage(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber) {
        Page<Booking> page = bService.findByPage(pageNumber);
        return page;

    }

    @GetMapping("/pastpage")
    public Page<Booking> pastpage(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber) {
        Page<Booking> page = bService.findOrderPage(pageNumber);
        return page;

    }
}
