package com.jgq.service;

import java.util.List;

public interface UrlService {
    /**
     * 根据Rid查询用户的url地址信息
     */
    List<String> selUrlInfoByRidService(Integer rid);
}
