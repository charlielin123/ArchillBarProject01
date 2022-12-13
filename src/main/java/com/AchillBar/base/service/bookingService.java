package com.AchillBar.base.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AchillBar.base.model.Booking;
import com.AchillBar.base.model.dao.BookingDao;

import antlr.collections.List;

@Service
public class bookingService {
    @Autowired
    BookingDao bDao;

    public void deleteoId(Long id) {
        bDao.deleteById(id);
    }

    public Page<Booking> findByPage(Integer pageNumber) {
        Pageable pgb = PageRequest.of(pageNumber - 1, 3, Sort.Direction.ASC, "bookDate");
        Page<Booking> page = bDao.orderpage(pgb);
        return page;
    }

    public Page<Booking> findOrderPage(Integer pageNumber) {
        Pageable pgb = PageRequest.of(pageNumber - 1, 3, Sort.Direction.DESC, "bookDate");
        Page<Booking> page = bDao.pastorder(pgb);
        return page;
    }

    public Page<Booking> findBynamePage(String name, Integer pageNumber) {
        Pageable pgb = PageRequest.of(pageNumber - 1, 3, Sort.Direction.DESC, "bookDate");
        Page<Booking> page = bDao.findnameLike(name, pgb);
        return page;
    }

}
