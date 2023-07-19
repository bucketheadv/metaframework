package org.metaframework.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.metaframework.web.dao.mapper.UserMapper;
import org.metaframework.web.prop.BlackListProperties;
import org.metaframework.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sven
 * Created on 2023/7/15 3:54 PM
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UserMapper userMapper;
    @Value("${my.name}")
    private String value;
    @Autowired
    private BlackListProperties blackListProperties;
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public Object test(Long id) {
        return userMapper.findById(id);
    }

    @GetMapping("/ok")
    public Object ok(String name) {
        return "OK:" + value + ":" + userService.sayHello(name) + ":" + blackListProperties.getUsers();
    }
}
