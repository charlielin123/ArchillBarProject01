package com.AchillBar.base.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Menu")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "p_id")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Integer p_id;

    @Column(name = "p_name")
    private String p_name;
    @Column(name = "type")
    private String type;
    @Column(name = "component")
    private String component;
    @Column(name = "info")
    private String info;
    @Column(name = "price")
    private Integer price;
    @Column(name = "image")
    private String image;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate", columnDefinition = "datetime")
    private Date updateDate;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", columnDefinition = "datetime", updatable = false, nullable = false)
    private Date createDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_tags", joinColumns = {
            @JoinColumn(name = "p_id", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "t_id", nullable = false, updatable = false) })
    private Set<Tags> tags = new HashSet<Tags>();

    
    private Double score;

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @PrePersist
    public void onCreate() {
        if (createDate == null) {

            createDate = new Date();
        }

    }

    @PreUpdate
    void onUpdate() {
        updateDate = new Date();

    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Menu() {
        // TODO Auto-generated constructor stub
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = new Date();

    }

    public Set<Tags> getTags() {
        return tags;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }

}
