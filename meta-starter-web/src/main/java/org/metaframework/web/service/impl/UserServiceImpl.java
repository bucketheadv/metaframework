package org.metaframework.web.service.impl;

import org.metaframework.web.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author sven
 * Created on 2023/7/16 3:27 PM
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
}
