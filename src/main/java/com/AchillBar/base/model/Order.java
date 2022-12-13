package com.AchillBar.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "[Order]")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long o_id;

    @Column(name = "b_id")
    private Long b_id;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate", columnDefinition = "datetime")
    // 更新日期
    private Date updateDate;

    @ManyToOne()
    @JoinColumn(name = "b_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Booking booking;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Orderdetails> orderdetails = new ArrayList<Orderdetails>();

    @PrePersist
    public void onCreate() {
        if (updateDate == null) {

            updateDate = new Date();
        }

    }

    @Transient
    private Integer total;

    @PreUpdate
    void onUpdate() {
        updateDate = new Date();

    }

    public Order() {
    }

    public Long getO_id() {
        return this.o_id;
    }

    public void setO_id(Long o_id) {
        this.o_id = o_id;
    }

    public Long getB_id() {
        return this.b_id;
    }

    public void setB_id(Long b_id) {
        this.b_id = b_id;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Booking getBooking() {
        return this.booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<Orderdetails> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<Orderdetails> orderdetails) {
        this.orderdetails = orderdetails;
    }

    public Integer getTotal() {
        int total = 0;
        for (Orderdetails od : orderdetails) {
            total += od.getQuantity() * od.getUnit();
        }
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
