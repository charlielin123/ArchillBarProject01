package com.AchillBar.base.model.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.AchillBar.base.model.Menu;

public interface MenuDao extends JpaRepositoryImplementation<Menu, Integer> {

    public List<Menu> findByType(String type);

    @Query(value = "from Menu where p_id in ?1")
    public Set<Menu> findByP_ids(Set<Integer> ids);

}
