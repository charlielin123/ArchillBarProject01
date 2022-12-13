package com.AchillBar.base.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 訂位ID
    private Long b_id;

    // 會員ID
    private Integer m_id;

    // 訂位電話
    private String phoneNum;

    // 桌號
    private Integer tableNum;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate", columnDefinition = "datetime")
    // 更新日期
    private Date updateDate;

    // 訂位日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookDate;

    // 人數
    private Integer numOfPpl;

    // 是否入座
    private Integer status=1;

    // 訂單資料
    @OneToMany(mappedBy = "booking", cascade = { CascadeType.ALL })
    @JsonManagedReference
    private List<Order> order;

    // 訂位的會員
    @OneToOne
    @JoinColumn(name = "m_id", insertable = false, updatable = false)
    private memberModel member;

    @PrePersist
    public void onCreate() {
        if (updateDate == null) {

            updateDate = new Date();
        }

    }

    @PreUpdate
    void onUpdate() {
        updateDate = new Date();

    }

    public Booking(Long b_id, Integer m_id, String phoneNum, Date updateDate, Integer numOfPpl, List<Order> order) {
        this.b_id = b_id;
        this.m_id = m_id;
        this.phoneNum = phoneNum;
        this.updateDate = updateDate;
        this.numOfPpl = numOfPpl;
        this.order = order;
    }

    public Booking() {
    }

    public Long getB_id() {
        return this.b_id;
    }

    public void setB_id(Long b_id) {
        this.b_id = b_id;
    }

    public Integer getM_id() {
        return this.m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getNumOfPpl() {
        return this.numOfPpl;
    }

    public void setNumOfPpl(Integer numOfPpl) {
        this.numOfPpl = numOfPpl;
    }

    public List<Order> getOrder() {
        return this.order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }



    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public memberModel getMember() {
        return member;
    }

    public void setMember(memberModel member) {
        this.member = member;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
