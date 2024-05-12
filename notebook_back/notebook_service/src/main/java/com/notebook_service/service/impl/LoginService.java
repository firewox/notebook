package com.notebook_service.service.impl;

import com.notebook_dao.dao.ILoginDao;
import com.notebook_domain.domain.User;
import com.notebook_service.service.ILoginService;
import com.notebook_utils.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/26 12:28
 */
@Service
@Transactional
public class LoginService implements ILoginService {
    @Autowired
    private ILoginDao loginDao;

    @Override
    public User loginByEmailAndPassword(String email, String password) throws Exception {
        User user = loginDao.findUserByEmail(email);
        if (user == null) {
            return null;
        }
        //获取盐，对用户输入的密码加盐加密
        password = CodecUtils.md5Hex(password, user.getSalt());
        //和数据库中的密码进行比较
        if (StringUtils.equals(password, user.getPassword())){
            return user;
        }
        return null;
    }
}
