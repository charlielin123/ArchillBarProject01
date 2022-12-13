package com.AchillBar.base.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.AchillBar.base.model.Order;

public interface OrderDao extends JpaRepository<Order, Long> {

    @Query(value = "from Order where m_id=?1 order by o_id desc")
    public List<Order> findByM_id(Integer m_id);

    @Query(value = "from Order where o_id=?1")
    public List<Order> findByO_id(Long o_id);

}
