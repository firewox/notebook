package com.notebook_dao.dao;

import com.notebook_domain.domain.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/26 12:30
 */
public interface ILoginDao {
    @Select("select * from user where email=#{email}")
    @Results({
            @Result(id = true,property = "userId",column = "uid"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "created",column = "created"),
            @Result(property = "status",column = "status"),
            @Result(property = "salt",column = "salt")
    })
    User findUserByEmail(String email) throws Exception;
}
