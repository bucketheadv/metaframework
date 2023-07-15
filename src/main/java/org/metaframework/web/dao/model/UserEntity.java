package org.metaframework.web.dao.model;

import lombok.Data;

/**
 * @author sven
 * Created on 2023/7/15 5:43 PM
 */
@Data
public class UserEntity {
    private Long id;

    private String username;

    private Integer age;
}
