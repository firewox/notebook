package com.notebook_service.service;

import com.notebook_domain.domain.User;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/22 23:58
 */
public interface IRegisterService {
    /**
     * 通过email查询用户uid，判断邮箱是否被注册
     * @param email
     * @return
     */
    public abstract Integer findUidByEmail(String email) throws Exception;

    /**
     * 校验用户输入的验证码和redis中的是否相同
     * @param verificationCode
     * @return
     * @throws Exception
     */
    public abstract boolean checkVerificationCode(String email,String verificationCode) throws Exception;

    /**
     * 给邮箱发送验证码
     * @param email uid
     * @throws Exception
     */
    public abstract void sendVerificationCode(String email) throws Exception;

    /**
     * 注册用户
     * @param user
     */
    public abstract void registerUser(User user) throws Exception;
}
