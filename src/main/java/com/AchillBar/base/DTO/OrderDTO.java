package com.AchillBar.base.DTO;

import java.util.Date;

import com.AchillBar.base.model.Booking;
import com.AchillBar.base.model.Order;
import com.AchillBar.base.model.memberModel;

public class OrderDTO {

    Long b_id;
    Date updateDate;
    Integer m_id;
    String m_name;
    Long o_id;
    Date bookDate;
    
    public OrderDTO(Booking Book ,Order order,memberModel member) {
        setB_id(Book.getB_id());
        setUpdateDate(Book.getUpdateDate());
        setM_id(member.getM_id());
        setM_name(member.getMemberName());
        setO_id(order.getO_id());
        setBookDate(Book.getBookDate());       
    }
    
    public Long getB_id() {
        return b_id;
    }

    public void setB_id(Long b_id) {
        this.b_id = b_id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

   

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public Long getO_id() {
        return o_id;
    }

    public void setO_id(Long o_id) {
        this.o_id = o_id;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public OrderDTO() {
    }

    
    
}
