package com.AchillBar.base.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Tags")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "t_id")
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Integer t_id;

    @Column(name = "tag")
    private String tag;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<Product> product = new HashSet<Product>(0);

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Integer getT_id() {
        return this.t_id;
    }

    public Tags() {
        // TODO Auto-generated constructor stub
    }

    public String getTag() {
        return tag;
    }

    public Set<Product> getProduct() {
        return this.product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
