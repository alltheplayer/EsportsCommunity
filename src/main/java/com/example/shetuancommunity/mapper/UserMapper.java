package com.example.shetuancommunity.mapper;
/*
created on 2022 4/25
 */

import com.example.shetuancommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {
    @Insert("insert into foruser (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
     void insert(User user);
}
