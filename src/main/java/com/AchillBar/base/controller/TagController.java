package com.AchillBar.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.AchillBar.base.model.dao.TagsDao;

@RestController
public class TagController {
    @Autowired
    TagsDao tDao;

}
