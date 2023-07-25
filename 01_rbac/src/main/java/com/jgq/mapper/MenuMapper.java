package com.jgq.mapper;

import com.jgq.pojo.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {
    //根据角色ID和上级菜单的ID获取子菜单信息
    @Select("select * from t_menu where mid in (select mid from r_menu where rid=#{rid}) and parentid=#{pid}")
    List<Menu> selMenuInfoByRidPidMapper(@Param("rid") Integer rid, @Param("pid") String pid);
}
