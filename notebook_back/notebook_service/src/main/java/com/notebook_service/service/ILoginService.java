package com.notebook_service.service;

import com.notebook_domain.domain.User;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/26 12:26
 */
public interface ILoginService {
    public abstract User loginByEmailAndPassword(String email,String password) throws Exception;
}
