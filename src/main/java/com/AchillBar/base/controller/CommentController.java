package com.AchillBar.base.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.Comment;
import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.model.dao.CommentDao;
import com.AchillBar.base.service.CommentService;

@RestController
@RequestMapping("/comment")
@SessionAttributes({ "LoginOK" })
public class CommentController {

    @Autowired
    private CommentService cService;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    CommentDao cDao;

    @GetMapping("/p/{p_id}")
    public List<Comment> findByPid(@PathVariable Integer p_id) {
        return cDao.findByP_id(p_id);
    }

    @GetMapping("/c/{com_id}")
    public Comment findByCid(@PathVariable Long com_id) {
        return cDao.findByC_id(com_id);
    }

    @PostMapping("/postEditComment")
    public String postEditMessage(@RequestBody Comment comm, Model model) {
        memberModel mb = (memberModel) model.getAttribute("LoginOK");
        Integer m_id = mb.getM_id();
        Integer cm_id = comm.getM_id();
        if (m_id.equals(cm_id)) {
            Long com_id = comm.getCom_id();
            Comment res = cDao.findByC_id(com_id);
            if (res != null) {
                res.setComment(comm.getComment());
                res.setScore(comm.getScore());
                res.setUpdateDate(new Date());
                cService.insert(res);
                return "修改成功";
            } else {
                return "查無資料";
            }

        } else {
            return "會員錯誤";
        }

    }

    @PostMapping("/add")
    public String add(@RequestBody Comment comm, Model model) {
        // 取得會員ID
        memberModel mb = (memberModel) model.getAttribute("LoginOK");
        if (mb != null) {
            Integer mid = mb.getM_id();
            comm.setM_id(mid);
            cDao.save(comm);
            return "新增評論成功";
        } else {
            return "請先登入會員";
        }

    }

    @GetMapping("/delete/{com_id}")
    public String deleteMessage(@PathVariable Long com_id, Model model) {

        memberModel mb = (memberModel) model.getAttribute("LoginOK");
        Comment res = cDao.findByC_id(com_id);
        Integer m_id = mb.getM_id();
        if (res != null) {
            Integer cm_id = res.getM_id();
            if (m_id == cm_id) {
                cService.deleteById(com_id);
                return "刪除成功";
            } else {
                return "會員錯誤";
            }
        } else {
            return "查無評論";
        }
    }

    @GetMapping("/findAllcomment")
    public Map<String, List<Comment>> findAllMember() {
        List<Comment> commList = cService.findAll();
        HashMap<String, List<Comment>> res = new HashMap<>();
        res.put("data", commList);
        return res;
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody Comment comm, Model model) {
        memberModel mb = (memberModel) model.getAttribute("LoginOK");
        Integer m_id = mb.getM_id();
        String text = comm.getComment();
        Long com_id = comm.getCom_id();
        Comment res = cDao.findByC_id(com_id);
        Integer p_id;
        String date;
        SimpleDateFormat outputSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        if (res != null) {
            p_id = res.getP_id();
            date = outputSimpleDateFormat.format(new Date());
        } else {
            p_id = comm.getP_id();
            date = outputSimpleDateFormat.format(new Date());

        }
        // 將亂數字串寄出
        SimpleMailMessage message = new SimpleMailMessage();
        // 設定寄件人
        message.setFrom("charlietest39@outlook.com");
        // 設定收件人
        message.setTo("zxc124236571@gmail.com");
        // 設定信件主旨
        message.setSubject("會員:" + m_id + "對產品:" + p_id + "時間:" + date + "留言");
        // 設定信件內容
        message.setText("內容:" + text);
        try {
            mailSender.send(message);
            System.out.println("信件送出");
            return "信件送出";
        } catch (Exception e) {
            System.out.println("信件送出失敗");
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GetMapping("/page")
    public Page<Comment> orderpage(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber, Model model) {
        Page<Comment> page = cService.findByPage(pageNumber);
        return page;

    }

    @GetMapping("/findAllLike/{pageNumber}/{key}")
    public Page<Comment> findByNameLike(@PathVariable String key, @PathVariable Integer pageNumber) {
        Page<Comment> page = cService.findByCommentLike("%" + key + "%", pageNumber);
        return page;
    }

    @GetMapping("/findAllLike/{pageNumber}/")
    public Page<Comment> findByEmpty(@PathVariable Integer pageNumber) {
        Page<Comment> page = cService.findByCommentLike("", pageNumber);
        return page;

    }

}
