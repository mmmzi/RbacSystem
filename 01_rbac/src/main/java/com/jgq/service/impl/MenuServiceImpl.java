package com.jgq.service.impl;

import com.jgq.mapper.MenuMapper;
import com.jgq.pojo.Menu;
import com.jgq.pojo.TreeResult;
import com.jgq.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 通过角色id和父级菜单id来获取所有子菜单
     */
    @Override
    public List<TreeResult> selMenuInfoByRidPidService(Integer rid, String pid) {
        //从数据库中获取菜单信息
        List<Menu> menus = menuMapper.selMenuInfoByRidPidMapper(rid, pid);
        //将List<Menu>转换为List<TreeResult>
        List<TreeResult> list = new ArrayList<>();
        for (Menu m : menus) {
            TreeResult result = new TreeResult();
            result.setId(m.getMid());
            result.setText(m.getMname());
            //判断节点是否为父节点
            result.setState("1".equals(m.getIsparent()) ? "closed" : "open");
            //给TreeResult的attributes属性赋值
            //创建Map集合
            HashMap<String, Object> map = new HashMap<>();
            //存储自定义数据给map
            map.put("isparent", m.getIsparent());
            map.put("url", m.getMurl());
            result.setAttributes(map);
            list.add(result);
        }
        return list;
    }
}
