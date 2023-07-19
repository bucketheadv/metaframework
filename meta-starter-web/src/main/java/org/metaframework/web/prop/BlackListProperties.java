package org.metaframework.web.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @author sven
 * Created on 2023/7/16 2:45 PM
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "blacklist")
public class BlackListProperties {
    private Set<String> users;
}
