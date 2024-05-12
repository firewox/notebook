package com.notebook_service.service;

import com.notebook_domain.domain.User;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/28 15:48
 */
public interface IModifyService {
    /**
     * 修改用户昵称
     * @param user
     * @throws Exception
     */
    public abstract void modifyUsername(User user) throws Exception;

    /**
     * 修改密码
     * @param user
     * @throws Exception
     */
    public abstract void modifyPassword(User user) throws Exception;

    /**
     * 发送注销邮件验证码
     * @param email
     * @throws Exception
     */
    public abstract void sendVerificationCode(String email) throws Exception;

    /**
     * 校验注销验证码
     * @param email
     * @param verificationCode
     * @return
     * @throws Exception
     */
    public abstract boolean checkVerificationCode(String email, String verificationCode) throws Exception;

    /**
     * 注销账号
     * @param uid
     * @throws Exception
     */
    public abstract void deleteAccount(Integer uid) throws Exception;
}
