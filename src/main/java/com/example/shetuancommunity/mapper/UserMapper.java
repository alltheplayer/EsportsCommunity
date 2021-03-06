package com.example.shetuancommunity.mapper;
/*
created on 2022 4/25
 */

import com.example.shetuancommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into foruser (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
     void insert(User user);


    @Select("select * from foruser where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from foruser where id=#{id}")
    User findById(@Param("id") Integer id);
}
