package com.notebook_dao.dao;

import com.notebook_domain.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/28 15:58
 */
public interface IModifyDao {
    @Update("update user set username=#{username} where uid=#{userId}")
    public abstract void updateUsername(User user) throws Exception;

    @Update("update user set password=#{password} ,salt=#{salt} where uid=#{userId}")
    public abstract void updatePassword(User user) throws Exception;

    @Delete("delete from user where uid=#{uid}")
    void deleteAccount(Integer uid) throws Exception;
}
