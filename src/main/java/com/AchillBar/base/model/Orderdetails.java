package com.AchillBar.base.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Orderdetails")
public class Orderdetails {

    // 主鍵 無意義
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    // 產品號碼
    @Column(name = "p_id")
    private Integer p_id;
    // 單價
    @Column(name = "unit")
    private Integer unit;
    // 數量
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name="status")
    private Integer status=0;



    // 外鍵連結產品
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_id", updatable = false, insertable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "o_id")
    @JsonBackReference
    private Order order;

    public Orderdetails(Long pk, Long o_id, Integer p_id, Integer unit, Integer quantity, Integer subtotal,
            Product product) {
        this.pk = pk;
        // this.o_id = o_id;
        this.p_id = p_id;
        this.unit = unit;
        this.quantity = quantity;
        this.product = product;
    }

    public Orderdetails() {
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    // public Long getO_id() {
    // return o_id;
    // }

    // public void setO_id(Long o_id) {
    // this.o_id = o_id;
    // }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
