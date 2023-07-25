package com.jgq.service;

import com.jgq.pojo.TreeResult;
import java.util.List;

public interface MenuService {
    /**
     * 查询当前用户的子菜单信息
     */
    List<TreeResult> selMenuInfoByRidPidService(Integer rid, String pid);
}
