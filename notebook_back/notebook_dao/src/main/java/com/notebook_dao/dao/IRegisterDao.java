package com.notebook_dao.dao;

import com.notebook_domain.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/23 0:00
 */
public interface IRegisterDao {
    @Select("select uid from user where email=#{email}")
    public abstract Integer findUidByEmail(String email) throws Exception;

    @Insert("insert into user(username,password,email,gender,birthday,created,status,salt) values(#{username},#{password},#{email},#{gender},#{birthday},#{created},#{status},#{salt})")
    public abstract void registerUser(User user) throws Exception;
}
