package com.jgq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * EasyUi前端组件Tree的返回类
 * 统一后端传给前端的数据格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeResult {

    private Integer id;

    private String text;

    private String state;

    //存储自定义数据
    private Map<String, Object> attributes;
}
