package com.notebook_web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notebook_domain.domain.ResultInfo;
import com.notebook_domain.domain.User;
import com.notebook_service.service.ILoginService;
import com.notebook_service.service.IModifyService;
import com.notebook_utils.utils.JedisUtil;
import com.notebook_utils.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/28 15:10
 */
@Controller
@CrossOrigin
@RequestMapping("/modify")
public class ModifyController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IModifyService modifyService;

    /**
     * 修改用户昵称
     * @param info
     * @return
     * @throws Exception
     */
    @RequestMapping("/modifyUsername.do")
    @ResponseBody
    public ResultInfo modifyUsername(@RequestBody String info,HttpSession session)throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String email = jsonNode.findPath("email").asText();
        String password = jsonNode.findPath("password").asText();
        String username = jsonNode.findPath("username").asText();
        User user = loginService.loginByEmailAndPassword(email, password);

        if (user != null) {
            user.setUsername(username);
            modifyService.modifyUsername(user);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("修改成功！");
            user = loginService.loginByEmailAndPassword(email, password);
            session.setAttribute("SESSION_user",user);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("邮箱或密码错误！");
        }
        return resultInfo;
    }

    /**
     * 修改密码
     * @param info
     * @return
     * @throws Exception
     */
    @RequestMapping("/modifyPassword.do")
    @ResponseBody
    public ResultInfo modifyPassword(@RequestBody String info,HttpSession session) throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String email = jsonNode.findPath("email").asText();
        String password = jsonNode.findPath("password").asText();
        String newPassword = jsonNode.findPath("newPassword").asText();
        User user = loginService.loginByEmailAndPassword(email, password);
        if (user != null) {
            user.setPassword(newPassword);
            modifyService.modifyPassword(user);
            user = loginService.loginByEmailAndPassword(email, newPassword);
            //TODOq
            session.setAttribute("SESSION_user",user);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("修改成功！");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("邮箱或密码错误！");
        }
        return resultInfo;
    }

    /**
     * 注销账号
     * @param info
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteAccount.do")
    @ResponseBody
    public ResultInfo deleteAccount(@RequestBody String info,HttpSession session) throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String email = jsonNode.findPath("email").asText();
        String password = jsonNode.findPath("password").asText();
        String verificationCode = jsonNode.findPath("verificationCode").asText();
        User user = loginService.loginByEmailAndPassword(email, password);
        if (user==null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("密码错误！");
            return resultInfo;
        }
        if (modifyService.checkVerificationCode(email,verificationCode)){
            //TODOq 注销逻辑待续...
            modifyService.deleteAccount(user.getUserId());
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("注销成功！");
            session.removeAttribute("SESSION_user");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误！");
        }
        return resultInfo;
    }

    @RequestMapping("/logout.do")
    @ResponseBody
    public ResultInfo logout(@RequestBody String info, HttpSession session) throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String email = jsonNode.findPath("email").asText();
        Jedis jedis = JedisUtil.getJedis();
        jedis.del("SESSION_user_" + email);
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("退出成功！");
        return resultInfo;
    }
}
