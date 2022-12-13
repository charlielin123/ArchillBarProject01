package com.AchillBar.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class ShopCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 產品號碼
    @Column(name = "p_id")
    private Integer p_id;

    // 單價
    @Column(name = "unit")
    private Integer unit;

    // 數量
    @Column(name = "quantity")
    private Integer quantity;

    private Integer m_id;

    @Transient
    private String p_name;

    @Transient
    private Integer subTotal;

    @PrePersist
    public void preset() {
        if (this.quantity == null) {
            this.quantity = 1;
        }
    }

    public ShopCart() {
    }

    public ShopCart(Long id, Integer p_id, Integer unit, Integer quantity, Integer m_id, String p_name,
            Integer subTotal) {
        this.id = id;
        this.p_id = p_id;
        this.unit = unit;
        this.quantity = quantity;
        this.m_id = m_id;
        this.p_name = p_name;
        this.subTotal = subTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setSubTotal(Integer total) {
        this.subTotal = total;
    }

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public Integer getSubTotal() {
        if (this.quantity != null) {
            return this.quantity * this.unit;
        }
        return subTotal;
    }

}
