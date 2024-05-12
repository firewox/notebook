package com.notebook_web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notebook_domain.domain.*;
import com.notebook_service.service.IEbbinghausService;
import com.notebook_service.service.IEditorService;
import com.notebook_service.service.ILoginService;
import com.notebook_utils.utils.JedisUtil;
import com.notebook_utils.utils.ObjectMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/26 10:47
 */
@Controller
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @Autowired
    private IEditorService editorService;

    @Autowired
    private IEbbinghausService ebbinghausService;

    @RequestMapping("/loginUser.do")
    @ResponseBody
    public ResultInfo loginUser(@RequestBody String info,HttpSession session) throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String email = jsonNode.findPath("email").asText();
        String password = jsonNode.findPath("password").asText();
        User user = loginService.loginByEmailAndPassword(email, password);
        if (user==null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("密码输入错误！");
        }else{
            String userJson = ObjectMapperUtils.mapper.writeValueAsString(user);
            Jedis jedis = JedisUtil.getJedis();
            jedis.set("SESSION_user_"+email,userJson);
            jedis.expire("SESSION_user_"+email,60*60*24);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("登录成功！");
            resultInfo.setData(user);
        }
        return resultInfo;
    }

    @RequestMapping("/indexHtml.do")
    @ResponseBody
    public ResultInfo indexHtml(@RequestBody String info, HttpSession session) throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String email = jsonNode.findPath("email").asText();
        User user = null;
        if (!StringUtils.equalsIgnoreCase("null",email)){
            Jedis jedis = JedisUtil.getJedis();
            String userJson = jedis.get("SESSION_user_" + email);
            if (userJson != null){
                user = ObjectMapperUtils.mapper.readValue(userJson, User.class);
            }
        }
        if (user != null) {
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("欢迎回来！");
            user.setCategories(editorService.findCategoriesByUid(user.getUserId()));
            user.setLabels(editorService.findLabelsByUid(user.getUserId()));
            //查找trash_station垃圾回收中心数据
            List<TrashStation> trashStation = editorService.findTrashStationByUid(user.getUserId());
            List<TrashStation> trashStationList = new ArrayList<TrashStation>();
            trashStation.forEach((ls)->{
                try {
                    Notebook n = editorService.findNotebookByNid(ls.getNid(),1);
                    ls.setNotebookName(n.getNotebookName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                trashStationList.add(ls);
            });
            user.setTrashStation(trashStationList);
            resultInfo.setData(user);
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("请登录！");
        }
        return resultInfo;
    }
}
