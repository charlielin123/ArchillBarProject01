package com.AchillBar.base.model.dao;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AchillBar.base.model.Tags;

public interface TagsDao extends JpaRepository<Tags, Integer> {

        public Tags findByTag(@Param("tag") String tag);

        @Query(value = "from Tags where tag in ?1")
        public Set<Tags> findByTags(@Param("tag") List<String> tag);

        @Query(value = "select t.*,p.* from Tags as t inner join " +
                        "product_tags as p on t.t_id=p.t_id " +
                        "where p.p_id =?1", nativeQuery = true)
        public Set<Tags> findTidByPid(Integer pId);

        @Query(value = "select p.p_id " +
                        "from Tags as t inner join product_tags as p " +
                        "on t.t_id=p.t_id where t.tag in (:tags) " +
                        "group by p.p_id " +
                        "having count(t.tag)=:num", nativeQuery = true)
        public Set<Integer> findPidByTagname(@Param("tags") List<String> tags, @Param("num") Integer num);

        @Transactional
        @Modifying
        @Query(value = "delete from product_tags where t_id=?1",nativeQuery = true)
        public void deleteTag(Integer t_id);

        @Transactional
        @Modifying
        @Query(value = "delete from Tags where t_id not in ( select p.t_id from product_tags p)",nativeQuery = true)
        public void refreshTagPool();

}