package com.jgq.service.impl;

import com.jgq.mapper.UrlMapper;
import com.jgq.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlMapper urlMapper;

    /**
     * 根据rid获取url信息
     */
    @Override
    public List<String> selUrlInfoByRidService(Integer rid) {
        return urlMapper.selUrlInfoByRidMapper(rid);
    }
}
