package com.AchillBar.base.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.transaction.annotation.Transactional;

import com.AchillBar.base.model.Order;
import com.AchillBar.base.model.Orderdetails;

public interface OrderDetailDao extends JpaRepositoryImplementation<Orderdetails, Long>{
    @Query(value = "from Orderdetails where o_id = ?1")
    public List<Orderdetails> findDetailByOrderId(Long orderId);
    
    @Transactional
    @Modifying
    @Query(value = "update Orderdetails od set od.status =?2 where od.pk=?1")
    public void editDetailStatusByPk(Long pk,Integer status);


    
    @Query(value = "DELETE FROM Orderdetails"
            + "      WHERE o_id=?1 ",nativeQuery = true)
    public List<Orderdetails> deleteOrderIdds(Long o_id);
    
    @Query("From Orderdetails where o_id = ?1 and p_id = ?2")
    public Orderdetails findByOIdAndPId(long o_id,Integer p_id);
    
    @Query(value = "select *\r\n"
            + " from Orderdetails\r\n"
            + "where pk=?1 ",nativeQuery = true)
    public List<Orderdetails> deleteDetailsProD(Long pk);
    
}
