package com.AchillBar.base.model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.AchillBar.base.model.ShopCart;

public interface ShopCartDao extends JpaRepository<ShopCart, Long> {

    @Query(value = "from ShopCart where m_id=?1")
    public List<ShopCart> findByM_id(Integer m_id);

    @Query(value = "from ShopCart where (m_id=?1 and p_id=?2)")
    public ShopCart findByM_idAndP_id(Integer m_id, Integer p_id);

    @Transactional
    @Modifying
    @Query(value = "delete ShopCart where m_id=?1")
    public void deleteByM_id(Integer m_id);
}
