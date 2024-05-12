package com.notebook_web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notebook_domain.domain.Category;
import com.notebook_domain.domain.ResultInfo;
import com.notebook_domain.domain.User;
import com.notebook_service.service.IEditorService;
import com.notebook_service.service.IModifyService;
import com.notebook_service.service.IRegisterService;
import com.notebook_utils.utils.ObjectMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/22 23:55
 */
@Controller
@CrossOrigin
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IRegisterService registerService;

    @Autowired
    private IModifyService modifyService;

    @Autowired
    private IEditorService editorService;

    @RequestMapping("/checkEmailExist.do")
    @ResponseBody
    public ResultInfo checkEmailExist(@RequestBody String info) throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String email = jsonNode.findPath("email").asText();
        JsonNode deleteAccount = jsonNode.findPath("deleteAccount");
        if (!Boolean.parseBoolean(deleteAccount.asText())){//注册的部分
            if (StringUtils.isNotEmpty(email)){
                if (registerService.findUidByEmail(email)!=null){
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMsg("邮箱已被注册!");
                    return resultInfo;
                }
                registerService.sendVerificationCode(email);
                resultInfo.setFlag(true);
                resultInfo.setErrorMsg("邮箱验证码5分钟内有效");
            }
        }else{//注销的部分
            if (StringUtils.isNotEmpty(email)){
                if (registerService.findUidByEmail(email)==null){
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMsg("此邮箱尚未注册!");
                    return resultInfo;
                }
                if (StringUtils.isNotEmpty(email)){
                    resultInfo.setErrorMsg("邮箱验证码5分钟内有效！");
                    modifyService.sendVerificationCode(email);
                    resultInfo.setFlag(true);
                }
            }
        }
        return resultInfo;
    }


    @RequestMapping("/registerUser.do")
    @ResponseBody
    public ResultInfo registerUser(@RequestBody User user) throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        //System.out.println("\n\n\nuser==="+user);
        //校验邮箱验证码
        if (registerService.checkVerificationCode(user.getEmail(),user.getVerificationCode())){
            registerService.registerUser(user);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("注册成功！");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误！");
            return resultInfo;
        }
        //用户注册成功后，初始化一个默认笔记本
        Integer uid = registerService.findUidByEmail(user.getEmail());
        editorService.createNewCategoryByUid(uid,"默认笔记本");
        //TODOq 初始化默认笔记后，再在其下面生成一个默认笔记
        Category c = editorService.findOneByCategoryNameAndUid(uid,"默认笔记本");
        editorService.createNewNotebookByCid(c.getCategoryId(),"默认笔记");
        return resultInfo;
    }

}
