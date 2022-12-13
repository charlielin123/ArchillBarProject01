package com.AchillBar.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.Booking;
import com.AchillBar.base.model.Order;
import com.AchillBar.base.model.Orderdetails;
import com.AchillBar.base.model.ShopCart;
import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.model.dao.BookingDao;
import com.AchillBar.base.model.dao.OrderDao;
import com.AchillBar.base.model.dao.OrderDetailDao;
import com.AchillBar.base.model.dao.ProductDao;
import com.AchillBar.base.model.dao.ShopCartDao;
import com.AchillBar.base.service.OrderService;

@Controller
@RequestMapping("/order")
@SessionAttributes({ "LoginOK" })
public class OrderController {
    @Autowired
    BookingDao bDao;

    @Autowired
    ProductDao pDao;

    @Autowired
    OrderDao oDao;

    @Autowired
    ShopCartDao scDao;
    @Autowired
    OrderDetailDao odDao;

    @Autowired
    OrderService oService;

    // @ResponseBody
    // @PostMapping("/submit")
    // public Order submit(@RequestBody Order req) {

    // return oDao.save(req);

    // }

    @ResponseBody
    @Transactional
    @GetMapping("/submit/{b_id}")
    public Order submitByBid(@PathVariable Long b_id, Model model) {
        memberModel member = (memberModel) model.getAttribute("LoginOK");

        if (member == null) {
            return null;
        }
        List<ShopCart> sc = scDao.findByM_id(member.getM_id());
        Order od = new Order();
        List<Orderdetails> odds = new ArrayList<Orderdetails>();
        od.setB_id(b_id);
        for (int i = 0; i < sc.size(); i++) {
            Orderdetails odd = new Orderdetails();
            odd.setP_id(sc.get(i).getP_id());
            odd.setQuantity(sc.get(i).getQuantity());
            odd.setUnit(sc.get(i).getUnit());
            odd.setOrder(od);
            odds.add(odd);
        }
        od.setOrderdetails(odds);
        if (od.getOrderdetails().size() == 0) {
            return null;
        }
        scDao.deleteByM_id(member.getM_id());

        return oService.insert(od);
    }

    @ResponseBody
    @GetMapping("/getOrders")
    public List<Booking> memberOrder(Model model) {
        if (model.getAttribute("LoginOK") != null) {
            memberModel md = (memberModel) model.getAttribute("LoginOK");

            return bDao.findByM_id(md.getM_id());
        }
        return null;
    }

}
