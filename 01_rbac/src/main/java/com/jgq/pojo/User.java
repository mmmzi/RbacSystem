package com.jgq.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer uid;

    private String uname;

    private String pwd;

    private Integer rid;
}
