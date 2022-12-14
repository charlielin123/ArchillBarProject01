package com.AchillBar.base.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.Booking;
import com.AchillBar.base.model.Order;
import com.AchillBar.base.model.Orderdetails;
import com.AchillBar.base.model.Product;
import com.AchillBar.base.model.dao.BookingDao;
import com.AchillBar.base.model.dao.OrderDao;
import com.AchillBar.base.model.dao.OrderDetailDao;
import com.AchillBar.base.model.dao.ProductDao;
import com.AchillBar.base.model.dao.ShopCartDao;
import com.AchillBar.base.service.OrderService;
import com.AchillBar.base.service.bookingService;

@Controller
@RequestMapping("/bak/order")
@SessionAttributes({ "LoginOK" })
public class BakOrderCotroller {

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

    @Autowired
    bookingService bserivce;

    @ResponseBody
    @GetMapping("/all")
    public List<Order> allOrder() {
        return oDao.findAll();
    }

    @GetMapping("/all2")
    @ResponseBody
    public List<Map<String, Object>> allOrder2() {
        List<Booking> all = bDao.findAll();
        List<Map<String, Object>> res = new ArrayList<>();
        for (int i = all.size() - 1; i >= 0; i--) {
            List<Order> odL = all.get(i).getOrder();
            for (int x = odL.size() - 1; x >= 0; x--) {
                Map<String, Object> map = new HashMap<>();
                map.put("BId", all.get(i).getB_id());
                map.put("BookDate", all.get(i).getBookDate());
                map.put("UpdateDate", all.get(i).getUpdateDate());
                map.put("Order", odL.get(x).getO_id());
                map.put("total", odL.get(x).getTotal());
                map.put("member", all.get(i).getM_id());
                map.put("name", all.get(i).getMember().getMemberName());
                // map.put("Checked", all.get(i).getChecked());
                res.add(map);
            }
        }
        return res;
    }

    // ??????????????????(orderId)
    @GetMapping("/{id}")
    @ResponseBody
    public Order findOderByOderId(@PathVariable Long id) {
        Optional<Order> op = oDao.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        return new Order();
    }

    @ResponseBody
    @GetMapping("/findOdBooking/{id}")
    public List<Booking> OdBook(@PathVariable Long id) {
        List<Booking> findbk = bDao.findByb_id(id);
        return findbk;
    }

    @ResponseBody
    @GetMapping("/findOdDteails/{id}")
    public List<Orderdetails> OdDteails(@PathVariable Long id) {
        // ??????orderId??????????????????
        List<Orderdetails> result = odDao.findDetailByOrderId(id);
        return result;
    }

    @ResponseBody
    @GetMapping("/deleteorder/{id}")
    public String delete(@PathVariable Long id, HttpServletResponse response) {
        oDao.deleteById(id);
        try {
            response.sendRedirect("/bak/ordertest");
            return "????????????";
        } catch (IOException e) {

            e.printStackTrace();
            return "????????????";
        }

    }

    @ResponseBody
    @PostMapping("/addProductDs2")
    public String findorall(@RequestBody Map<String, String> file, Model model) {
        try {
            // ?????????????????????
            long o_id = Long.parseLong(file.get("o_id"));
            Optional<Order> op = oDao.findById(o_id);
            if (op.isPresent()) {
                Order order = op.get();
                List<Orderdetails> orderdetails = order.getOrderdetails();
                // ?????????????????????
                Integer p_id = Integer.parseInt(file.get("p_id"));
                // ??????????????????
                int qt = Integer.parseInt(file.get("quantity"));
                // ??????detail???????????????????????????????????????
                Orderdetails detail = odDao.findByOIdAndPId(o_id, p_id);
                if (detail != null) {
                    return "???????????????????????????????????????????????????";
                } else {
                    Orderdetails newOd = new Orderdetails();
                    Optional<Product> op2 = pDao.findById(p_id);
                    if (op2.isPresent()) {
                        Product product = op2.get();
                        newOd.setP_id(p_id);
                        newOd.setUnit(product.getPrice());
                        newOd.setQuantity(qt);
                        newOd.setOrder(order);
                        orderdetails.add(newOd);
                        order.setOrderdetails(orderdetails);
                        oDao.save(order);
                        return "????????????";
                    } else {
                        return "????????????";
                    }

                }

            } else {
                return "????????????";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "????????????";
        }

    }

    // ????????????????????????????????????
    @PostMapping("/updateProductDs2")
    @ResponseBody
    public String updateDetailProduct(@RequestBody Map<String, String> file) {
        // ??????????????????PK
        long pk = Long.parseLong(file.get("pk"));
        // ????????????
        int qt = Integer.parseInt(file.get("quantity"));

        Optional<Orderdetails> option = odDao.findById(pk);
        if (option.isPresent()) {
            Orderdetails detail = option.get();
            detail.setQuantity(qt);
            try {
                odDao.save(detail);
                return "????????????";
            } catch (Exception e) {
                e.printStackTrace();
                return "????????????";
            }
        } else {
            return "??????????????????";
        }
    }

    @ResponseBody
    @GetMapping("/delOrderDsProduct/{pk}")
    public String delOrderDsPro(@PathVariable Long pk) {
        Optional<Orderdetails> detail = odDao.findById(pk);
        if (detail.isPresent()) {
            Orderdetails res = detail.get();
            // ???pk???????????????
            Long o_id = res.getOrder().getO_id();
            List<Orderdetails> r1 = odDao.findDetailByOrderId(o_id);
            if (r1.size() <= 1) {
                return "????????????";
            }
            odDao.deleteById(pk);
            return "?????????";
        } else {
            return "????????????";
        }

    }

    @ResponseBody
    @GetMapping(value = "/detail/status")
    public boolean changeDetailStatus(@RequestParam Long pk, Integer status) {
        try {
            odDao.editDetailStatusByPk(pk, status);
            System.out.println(pk);
            System.out.println(status);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @ResponseBody
    @GetMapping(value = "/BookingChecked/{id}")
    public String bookchecked(@PathVariable Long id, HttpServletResponse response) {
        try {
            Booking book = bDao.findcheckedByBid(id);
            book.setStatus(3);
            bDao.save(book);
            response.sendRedirect("/bak/ordertest");
            return "????????????";
        } catch (Exception e) {
            e.printStackTrace();
            return "??????";
        }
    }

    @GetMapping("/findOrderDteails/{id}")
    public String OdDteails(@PathVariable Long id, Model model) {
        // ??????orderId??????????????????
        List<Booking> bookList = bDao.findByb_id(oDao.findById(id).get().getB_id());
        Booking book = bookList.get(0);
        model.addAttribute("b_status", book.getStatus());
        System.out.println(book.getStatus());

        List<Orderdetails> result = odDao.findDetailByOrderId(id);
        model.addAttribute("detail", result);
        List<Map<String, Object>> presult = pDao.findDetailsproduct();
        model.addAttribute("product", presult);
        return "WMS/detail.jsp";
    }

    @ResponseBody
    @GetMapping("/groupbyBookDate")
    public List<Map<String, Date>> orderdate() {
        return bDao.groupbybookdate();
    }

    @ResponseBody
    @GetMapping("/findbyBookDate/{BookDate}")
    public List<Booking> findByBookDate(@PathVariable("BookDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return bDao.findbydate(date);
    }
}
