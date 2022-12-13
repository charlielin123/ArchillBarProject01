package com.AchillBar.base.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.AchillBar.base.model.Product;
import com.AchillBar.base.model.ShopCart;
import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.model.dao.ProductDao;
import com.AchillBar.base.model.dao.ShopCartDao;

@SessionAttributes({ "LoginOK" })
@RestController
public class ShopCartController {

    @Autowired
    ShopCartDao scD;

    @Autowired
    ProductDao pDao;

    @PostMapping("/shopCart")
    public ShopCart name(@RequestBody ShopCart sc, Model model) {
        if (model.getAttribute("LoginOK") != null) {
            memberModel member = (memberModel) model.getAttribute("LoginOK");
            sc.setM_id((member.getM_id()));
            ShopCart old = scD.findByM_idAndP_id(member.getM_id(), sc.getP_id());
            if (old != null) {
                old.setQuantity(old.getQuantity() + 1);
                return scD.save(old);
            }

            int i = (getPro(sc.getP_id()).getPrice());
            System.out.println(i);
            sc.setUnit(i);
            return scD.save(sc);
        }

        return null;
    }

    @GetMapping("/shopCart")
    public List<ShopCart> getCartByM_id(Model model) {
        if (model.getAttribute("LoginOK") != null) {
            memberModel member = (memberModel) model.getAttribute("LoginOK");
            List<ShopCart> res = scD.findByM_id(member.getM_id());
            for (ShopCart shopCart : res) {

                String pName = "無此商品";
                if (getPro(shopCart.getP_id()) != null) {
                    pName = getPro(shopCart.getP_id()).getP_name();

                }

                shopCart.setP_name(pName);
            }

            return res;
        }

        return null;
    }

    @GetMapping("/shopCart/reduceOne/{id}")
    public ShopCart reduceOne(@PathVariable Long id) {
        Optional<ShopCart> op = scD.findById(id);
        if (op.isPresent()) {
            ShopCart sc = op.get();
            sc.setQuantity(sc.getQuantity() - 1);
            return scD.save(sc);

        }

        return null;
    }

    @GetMapping("/shopCart/plusOne/{id}")
    public ShopCart pulsOne(@PathVariable Long id) {
        Optional<ShopCart> op = scD.findById(id);
        if (op.isPresent()) {
            ShopCart sc = op.get();
            sc.setQuantity(sc.getQuantity() + 1);
            return scD.save(sc);

        }

        return null;
    }

    @GetMapping("/shopCart/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        try {
            scD.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Product getPro(Integer p_id) {
        Optional<Product> op = pDao.findById(p_id);
        if (op.isPresent()) {
            return op.get();
        }

        return null;
    }

}
