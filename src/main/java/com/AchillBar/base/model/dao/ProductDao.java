package com.AchillBar.base.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.AchillBar.base.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {


    @Query(value = "select p_id,p_name,component,price,[type]\r\n"
            + "from Product",nativeQuery = true)
    public List<Map<String,Object>> findDetailsproduct();
    


    
}