package com.jgq.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface UrlMapper {
    //根据rid获取url地址信息
    @Select("select urlAddress from t_url where urlid in(select urlid from r_url where rid=#{rid})")
    List<String> selUrlInfoByRidMapper(@Param("rid") Integer rid);
}