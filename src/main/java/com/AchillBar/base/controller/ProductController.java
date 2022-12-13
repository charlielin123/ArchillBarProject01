package com.AchillBar.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

import com.AchillBar.base.model.Product;
import com.AchillBar.base.model.Tags;
import com.AchillBar.base.model.dao.ProductDao;
import com.AchillBar.base.model.dao.TagsDao;
import com.AchillBar.base.service.CommentService;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    ProductDao pDao;
    @Autowired
    TagsDao tDao;

    @Autowired
    CommentService cs;

    @PostMapping("/add")
    public Product insertProduct(@RequestBody Product pro) {
        Set<Tags> tags = pro.getTags();
        Map<Tags, Tags> map = new HashMap<>();

        tDao.findTidByPid(pro.getP_id());

        for (Tags tag : tags) {
            String tagName = tag.getTag();
            if (tDao.findByTag(tagName) != null) {
                map.put(tag, tDao.findByTag(tagName));

            }

        }
        map.forEach((key, value) -> {
            tags.remove(key);
            tags.add(value);

        });
        Product res=pDao.save(pro);
        tDao.refreshTagPool();
        return res;
        

    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {

        pDao.deleteById(id);
    }

    @GetMapping("/all")
    public List<Product> all() {
        return pDao.findAll();
    }

    @GetMapping("/all2")
    public Map<String, List<Product>> all2() {
        Map<String, List<Product>> res = new HashMap<>();
        res.put("data", pDao.findAll());
        
        return res;
    }

    @GetMapping("/allTag")
    public List<Tags> allTag() {

        return tDao.findAll();
    }

    @GetMapping("/{id}")
    public Product findByPid(@PathVariable Integer id) {
        Optional<Product> op = pDao.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        return new Product();
    }

    @PostMapping("/add/muti")
    public List<Product> insertProducts(@RequestBody List<Product> pros) {
        List<Product> res = new ArrayList<>();
        for (int i = 0; i < pros.size(); i++) {
            res.add(pDao.save(pros.get(i)));
        }

        return res;

    }

    @GetMapping("/tag/delete/{id}")
    public boolean name(@PathVariable Integer id) {
        try {
            tDao.deleteTag(id);

        } catch (Exception e) {
            tDao.deleteById(id);
            return true;
        }
        return false;
    }

    // 隨機注入TAG
    @GetMapping("/tag/create")
    public boolean crtateTag() {
        String[] constellation = { "牡羊座", "金牛座", "雙子座", "巨蟹座", "獅子座", "處女座", "天秤座", "天蠍座", "射手座", "摩羯座", "水瓶座", "雙魚座" };
        String[] MBTI = { "INTJ", "INTP", "ENTJ", "ENTP", "INTJ", "INFP", "ENFJ", "ENFP", "ISTJ", "ISFJ", "ESTJ",
                "ESFJ", "ISTP", "ISFP", "ESTP", "ESFP" };
        String[] haha = { "紳士", "淑女", "性感", "成熟" };
        String[] haha2 = { "失戀", "狂歡", "一個人靜靜", "放鬆" };

        try {

            for (int i = 0; i < pDao.findAll().size(); i++) {
                Product pro = pDao.findAll().get(i);
                Set<Tags> tags = new HashSet<>();
                for (int j = 0; j < (int) (Math.random() * 2) + 1; j++) {
                    Tags t1 = new Tags();
                    t1.setTag(constellation[(int) (Math.random() * 12)]);
                    tags.add(t1);

                }
                for (int j = 0; j < (int) (Math.random() * 2) + 1; j++) {
                    Tags t2 = new Tags();
                    t2.setTag(MBTI[(int) (Math.random() * 16)]);
                    tags.add(t2);
                }

                Tags t3 = new Tags();
                t3.setTag(haha[(int) (Math.random() * 4)]);
                tags.add(t3);

                Tags t4 = new Tags();
                t4.setTag(haha2[(int) (Math.random() * 4)]);
                tags.add(t4);
                Map<Tags, Tags> map = new HashMap<>();
        
                for (Tags tag : tags) {
                    String tagName = tag.getTag();
                    if (tDao.findByTag(tagName) != null) {
                        map.put(tag, tDao.findByTag(tagName));
        
                    }
        
                }
                map.forEach((key, value) -> {
                    tags.remove(key);
                    tags.add(value);
        
                });
                pro.setTags(tags);
                pDao.save(pro);

            }

        } catch (Exception e) {
            return false;
        }
        return true;

    }
    
    
}
