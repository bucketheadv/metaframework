package org.metaframework.web.controller;

import org.metaframework.web.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sven
 * Created on 2023/7/15 3:54 PM
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UserMapper userMapper;
    @Value("${my.name}")
    private String value;

    @GetMapping("/test")
    public Object test(Long id) {
        return userMapper.findById(id);
    }

    @GetMapping("/ok")
    public Object ok() {
        return "OK" + value;
    }
}
