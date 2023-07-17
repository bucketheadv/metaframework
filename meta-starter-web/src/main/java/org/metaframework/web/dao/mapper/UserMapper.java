package org.metaframework.web.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.metaframework.web.dao.model.UserEntity;

/**
 * @author sven
 * Created on 2023/7/15 5:45 PM
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user where id = #{id}")
    UserEntity findById(@Param("id") Long id);
}
