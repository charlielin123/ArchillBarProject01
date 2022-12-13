package com.AchillBar.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AchillBar.base.model.Booking;
import com.AchillBar.base.model.dao.BookingDao;
import com.AchillBar.base.model.dao.OrderDao;
import com.AchillBar.base.model.dao.OrderDetailDao;
import com.AchillBar.base.model.dao.ProductDao;
import com.AchillBar.base.service.bookingService;

@Controller
public class pageController {

	@Autowired
	OrderDetailDao odDao;

	@Autowired
	OrderDao oDao;

	@Autowired
	BookingDao bDao;
	@Autowired
	ProductDao pDao;
	@Autowired
	bookingService bService;

	@GetMapping("/")
	public String homePage() {
		return "index.jsp";
	}

	@GetMapping("/menu")
	public String menuPage() {
		return "menu/menu.html";
	}

	@GetMapping("/menu/adds")
	public String addMutiPro() {
		return "dep/addMenu.html";
	}

	@GetMapping("/booking")
	public String bookingPage() {
		return "member/booking.html";
	}

	@GetMapping("/bak/comm")
	public String comm() {
		return "WMS/comment.html";
	}

	@GetMapping("/myOrder")
	public String myOrder() {
		return "member/orderRecord.html";
	}

	@GetMapping("/bak/pro")
	public String bakPro() {
		return "WMS/product_backed.html";
	}

	@GetMapping("/bak/member")
	public String bakmember() {
		return "WMS/member_backed.html";
	}

	@GetMapping("/bak/ordertest")
	public String ordertest() {
		return "WMS/ordertest.html";
	}

	@GetMapping("/bak/manger")
	public String dlp01() {
		return "WMS/Manger.html";
	}

	@GetMapping("/bak/pastorder")
	public String pastorder() {
		return "WMS/pastorder.html";
	}

	@GetMapping("/bak/bookingbacked")
	public String bookingbacked() {
		return "WMS/bookingbacked.html";
	}

	@GetMapping("/bak/deletebid/{id}")
	public String delectBookingId(@PathVariable Long id) {
		bDao.deleteById(id);
		return "redirect:/ordertest";
	}

	@GetMapping("/bak/bookingForMember")
	public String bookingFormember() {
		return "/WMS/booking.html";
	}

	@ResponseBody
	@GetMapping("/findname/{pageNumber}/{name}")
	public Page<Booking> findByName(@PathVariable String name, @PathVariable Integer pageNumber) {
		return bService.findBynamePage(name, pageNumber);
	}

	@ResponseBody
	@GetMapping("/findname/{pageNumber}/")
	public Page<Booking> findByName(@PathVariable Integer pageNumber) {
		return bService.findByPage(pageNumber);
	}
}
