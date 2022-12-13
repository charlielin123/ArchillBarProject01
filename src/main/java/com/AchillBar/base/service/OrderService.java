package com.AchillBar.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AchillBar.base.model.Order;
import com.AchillBar.base.model.dao.BookingDao;
import com.AchillBar.base.model.dao.OrderDao;

@Service
public class OrderService {

    @Autowired
    OrderDao oDao;
    @Autowired
    BookingDao bDao;

    public Order insert(Order order) {
        return oDao.save(order);
    }

    public void deleteoId(Long id) {
        oDao.deleteById(id);
    }

}
