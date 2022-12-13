package com.AchillBar.base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AchillBar.base.model.Comment;
import com.AchillBar.base.model.dao.CommentDao;

@Transactional
@Service
public class CommentService {

    @Autowired
    CommentDao cDao;

    public Double getAvgScoreByPid(Integer id) {

        return cDao.getAvgScoreByPid(id);

    }

    public Comment insert(Comment msg) {
        return cDao.save(msg);
    }

    public Comment findById(Long id) {
        Optional<Comment> optional = cDao.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    public void deleteById(Long com_id) {
        cDao.deleteById(com_id);

    }

    public List<Comment> findAll() {
        return cDao.findAll();
    }

    public Page<Comment> findByPage(Integer pageNumber) {
        Pageable pgb = PageRequest.of(pageNumber - 1, 10, Sort.Direction.DESC, "createDate");
        Page<Comment> page = cDao.findAll(pgb);
        return page;
    }

    public Page<Comment> findByCommentLike(String key, Integer pageNumber) {
        Pageable pgb = PageRequest.of(pageNumber - 1, 10, Sort.Direction.DESC, "createDate");
        Page<Comment> page = cDao.findByCommentLike(key, pgb);
        return page;
    }

}
