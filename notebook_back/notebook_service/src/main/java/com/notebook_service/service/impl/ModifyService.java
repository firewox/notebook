package com.notebook_service.service.impl;

import com.notebook_dao.dao.IEditorDao;
import com.notebook_dao.dao.IModifyDao;
import com.notebook_domain.domain.Category;
import com.notebook_domain.domain.User;
import com.notebook_service.service.IModifyService;
import com.notebook_utils.utils.CodecUtils;
import com.notebook_utils.utils.JedisUtil;
import com.notebook_utils.utils.MailUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;


/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/28 15:49
 */
@Service
@Transactional
public class ModifyService implements IModifyService {

    @Autowired
    private IModifyDao modifyDao;

    @Autowired
    private IEditorDao editorDao;

    @Override
    public void modifyUsername(User user) throws Exception {
        modifyDao.updateUsername(user);
    }

    @Override
    public void modifyPassword(User user) throws Exception {
        //1.生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        //2.加盐加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));
        modifyDao.updatePassword(user);
    }

    @Override
    public void sendVerificationCode(String email) throws Exception {
        String verificationCode = MailUtils.getCheckCode();
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("DELETE_ACCOUNT_"+email,verificationCode);
        jedis.expire(email,300);
        String content = "【notebook】您好，您正在注销notebook账号，注销验证码是"+verificationCode+"。如非本人操作，请忽略本邮件。";
        MailUtils.sendMail(email,content,"【notebook】注销账号邮件");
    }

    @Override
    public boolean checkVerificationCode(String email, String verificationCode) throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        String verificationCodeTrue = jedis.get("DELETE_ACCOUNT_"+email);
        return StringUtils.equalsIgnoreCase(verificationCodeTrue,verificationCode);
    }

    @Override
    public void deleteAccount(Integer uid) throws Exception {
        editorDao.deleteTrashStationByUid(uid);
        editorDao.deleteLabelNotebookByUid(uid);
        editorDao.deleteLabelByUid(uid);
        List<Category> categories = editorDao.findCategoriesByUid(uid);
        for (Category c : categories) {
            editorDao.deleteNotebookByCid(c.getCategoryId());
        }
        editorDao.deleteCategoryByUid(uid);
        modifyDao.deleteAccount(uid);
    }
}
