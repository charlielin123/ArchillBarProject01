package com.AchillBar.base.model.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.AchillBar.base.model.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

    @Query("from Comment where p_id=?1")
    public List<Comment> findByP_id(Integer p_id);

    @Query(value = "select AVG(score) from Comment where p_id=?1", nativeQuery = true)
    public Double getAvgScoreByPid(Integer p_id);

    @Query("from Comment where com_id=?1")
    public Comment findByC_id(Long C_id);

    @Modifying
    @Query("delete from Comment where com_id=?1")
    public void deleteById(Long com_id);

    // @Query(value = "from Comment where comment like %?1%")
    public Page<Comment> findByCommentLike(String key, Pageable pageable);
}
