package org.metaframework.web.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author sven
 * Created on 2023/7/15 5:43 PM
 */
@Data
@Entity(name = "user")
public class UserEntity {
    @Id
    private Long id;

    private String username;

    private Integer age;
}
