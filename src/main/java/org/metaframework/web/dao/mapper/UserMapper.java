package org.metaframework.web.dao.mapper;

import org.metaframework.web.dao.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sven
 * Created on 2023/7/15 5:45 PM
 */
@Repository
public interface UserMapper extends JpaRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long> {
}
