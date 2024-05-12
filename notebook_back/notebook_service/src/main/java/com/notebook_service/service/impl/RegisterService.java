package com.notebook_service.service.impl;

import com.notebook_dao.dao.IRegisterDao;
import com.notebook_domain.domain.User;
import com.notebook_service.service.IRegisterService;
import com.notebook_utils.utils.CodecUtils;
import com.notebook_utils.utils.DateUtil;
import com.notebook_utils.utils.JedisUtil;
import com.notebook_utils.utils.MailUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;


/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/22 23:58
 */
@Service
@Transactional
public class RegisterService implements IRegisterService {
    @Autowired
    private IRegisterDao registerDao;
    @Override
    public Integer findUidByEmail(String email) throws Exception {
        return registerDao.findUidByEmail(email);
    }

    @Override
    public boolean checkVerificationCode(String email,String verificationCode) throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        String verificationCodeTrue = jedis.get(email);
        return StringUtils.equalsIgnoreCase(verificationCodeTrue,verificationCode);
    }

    @Override
    public void sendVerificationCode(String email) {
        String verificationCode = MailUtils.getCheckCode();
        Jedis jedis = JedisUtil.getJedis();
        jedis.set(email,verificationCode);
        jedis.expire(email,300);
        String content = "【notebook】您的验证码是"+verificationCode+"。如非本人操作，请忽略此邮件。";
        MailUtils.sendMail(email,content,"【notebook】激活邮件");
    }

    @Override
    public void registerUser(User user) throws Exception {
        //1.生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        //2.加盐加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));
        //3.新增用户
        user.setUserId(null);
        user.setCreated(DateUtil.today().getTime());
        user.setStatus(1);
        registerDao.registerUser(user);
    }
}
