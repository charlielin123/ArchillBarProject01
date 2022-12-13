package com.AchillBar.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AchillBar.base.model.Menu;
import com.AchillBar.base.model.Tags;
import com.AchillBar.base.model.dao.MenuDao;
import com.AchillBar.base.model.dao.TagsDao;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuDao mDao;

    @Autowired
    private TagsDao tdao;

    @GetMapping("/all")
    public Map<String, List<Menu>> all2() {
        Map<String, List<Menu>> res = new HashMap<>();
        res.put("appetizer", mDao.findByType("appetizer"));
        res.put("main", mDao.findByType("main"));
        res.put("soup", mDao.findByType("soup"));
        res.put("dessert", mDao.findByType("dessert"));
        res.put("fried", mDao.findByType("fried"));
        res.put("classicAlcoho", mDao.findByType("classicAlcoho"));
        res.put("originalAlcoho", mDao.findByType("originalAlcoho"));
        res.put("beerAlcoho", mDao.findByType("beerAlcoho"));
        res.put("softDrink", mDao.findByType("softDrink"));
        res.put("custMenu", mDao.findByType("custMenu"));

        return res;
    }

    @GetMapping("/{id}")
    public Menu finById(@PathVariable Integer id) {
        Optional<Menu> op = mDao.findById(id);
        if (op.isPresent()) {
            return op.get();

        }
        return null;
    }

    @GetMapping("/tag/all")
    public List<Tags> allTag() {
        return tdao.findAll();
    }

    @PostMapping("/tags")
    public Set<Menu> findByTags(@RequestBody List<String> tags) {
        Set<Integer> t1 = tdao.findPidByTagname(tags, tags.size());
        // return t1;
        return mDao.findByP_ids(t1);
    }
}
