package com.jgq.controller;

import com.jgq.pojo.TreeResult;
import com.jgq.pojo.User;
import com.jgq.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MenuController {

    //声明业务层属性
    @Autowired
    private MenuService menuService;

    /**
     * 声明单元方法:动态记载当前用户的菜单信息
     */
    @RequestMapping("menuInfo")
    @ResponseBody
    public List<TreeResult> menuInfo(@RequestParam(defaultValue = "0") String id, HttpSession session) {
        //获取session中当前登录的用户的Rid
        User user = (User) session.getAttribute("user");
        Integer rid = user.getRid();
        //获取当前用户的子菜单信息
        List<TreeResult> menus = menuService.selMenuInfoByRidPidService(rid, id);
        //响应结果
        return menus;
    }
}