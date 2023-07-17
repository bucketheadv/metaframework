package org.metaframework.apollo.filter;

import com.google.common.collect.Sets;
import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;

import java.util.Set;

/**
 * @author sven
 * Created on 2023/7/16 2:54 AM
 */
public class MetaApolloFilter implements AutoConfigurationImportFilter {
    private static final Set<String> SKIP_SET = Sets.newHashSet(
            "com.ctrip.framework.apollo.spring.boot.ApolloAutoConfiguration"
    );

    @Override
    public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
        boolean[] matches = new boolean[autoConfigurationClasses.length];
        for (int i = 0; i < autoConfigurationClasses.length; i++) {
            matches[i] = !SKIP_SET.contains(autoConfigurationClasses[i]);
        }
        return matches;
    }
}
