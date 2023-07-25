package com.jgq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    private Integer mid;

    private String mname;

    private String murl;

    private String parentid;

    private String isparent;

    private String mdsec;
}
