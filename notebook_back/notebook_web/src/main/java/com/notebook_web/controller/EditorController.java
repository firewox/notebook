package com.notebook_web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notebook_domain.domain.*;
import com.notebook_service.service.IEbbinghausService;
import com.notebook_service.service.IEditorService;
import com.notebook_service.service.IRegisterService;
import com.notebook_utils.utils.DateUtil;
import com.notebook_utils.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/3/4 10:17
 */
@Controller
@CrossOrigin
@RequestMapping("/editor")
public class EditorController {

    @Autowired
    private IEditorService editorService;

    @Autowired
    private IRegisterService registerService;

    @Autowired
    private IEbbinghausService ebbinghausService;

    @RequestMapping("/saveContent.do")
    @ResponseBody
    public ResultInfo saveContent(@RequestBody String info) throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer nid = jsonNode.findPath("notebookId").asInt();
        Integer cid = jsonNode.findPath("categoryId").asInt();
        String notebookContent = jsonNode.findPath("notebookContent").asText();
        Notebook n = editorService.findNotebookByNid(nid, 0);
        if (n!=null){
            editorService.saveNotebookContent(nid,cid,notebookContent,0);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("保存成功");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("保存失败！");
        }
        return resultInfo;
    }

    @RequestMapping("/createNewCategory.do")
    @ResponseBody
    public List createNewCategory(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String email = jsonNode.findPath("email").asText();
        String newCategoryName = jsonNode.findPath("newCategoryName").asText();
        Integer uid = registerService.findUidByEmail(email);
        Category c;
        while (true){
            c = editorService.findOneByCategoryNameAndUid(uid,newCategoryName);
            if (c!=null){
                newCategoryName = c.getCategoryName()+"*";
            }else{
                break;
            }
        }
        editorService.createNewCategoryByUid(uid,newCategoryName);
        editorService.createNewNotebookByCid(editorService.findOneByCategoryNameAndUid(uid,newCategoryName).getCategoryId(),"默认笔记");
        List<Category> list = editorService.findCategoriesByUid(uid);//TODOq 待定
        return list;
    }

    @RequestMapping("/findNotebooksByCid.do")
    @ResponseBody
    public List findNotebooksByCid(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String categoryId = jsonNode.findPath("categoryId").asText();
        List<Notebook> list = editorService.findNotebooksByCid(Integer.parseInt(categoryId),0);
        return list;
    }

    @RequestMapping("/findNotebookByNid.do")
    @ResponseBody
    public Notebook findNotebookByNid(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String nid = jsonNode.findPath("notebookId").asText();
        return editorService.findNotebookByNid(Integer.parseInt(nid),0);
    }

    @RequestMapping("/createNewNotebook.do")
    @ResponseBody
    public List createNewNotebook(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        String cid = jsonNode.findPath("categoryId").asText();
        String newNotebookName = jsonNode.findPath("newNotebookName").asText();
        Integer uid = jsonNode.findPath("uid").asInt();
        Notebook n;
        while (true){
            n = editorService.findOneByNotebookNameAndCid(Integer.parseInt(cid),newNotebookName,0);
            if (n!=null){
                newNotebookName = n.getNotebookName()+"*";
            }else{
                break;
            }
        }
        editorService.createNewNotebookByCid(Integer.parseInt(cid),newNotebookName);
        if (uid == null){
            return editorService.findNotebooksByCid(Integer.parseInt(cid),0);
        }
        return editorService.findCategoriesByUid(uid);//TODOq 待定
    }

    @RequestMapping("/createNewLabel.do")
    @ResponseBody
    public List createNewLabel(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer userId = jsonNode.findPath("userId").asInt();
        String newLabelName = jsonNode.findPath("newLabelName").asText();
        Label l;
        while (true){
            l = editorService.findOneByLabelNameAndUid(userId,newLabelName);
            if (l!=null){
                newLabelName = l.getLabelName() + "*";
            }else {
                break;
            }
        }
        editorService.createNewLabelByUid(userId,newLabelName);
        return editorService.findLabelsByUid(userId);
    }

    @RequestMapping("/updateLabelName.do")
    @ResponseBody
    public List updateLabelName(@RequestBody String info) throws Exception{
        ResultInfo resultInfo = new ResultInfo();
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer lid = jsonNode.findPath("lid").asInt();
        Integer uid = jsonNode.findPath("uid").asInt();
        String labelName = jsonNode.findPath("labelName").asText();
        Label l;
        while (true){
            l = editorService.findOneByLabelNameAndUid(uid,labelName);
            if (l!=null && l.getLabelId()!= lid){
                labelName = l.getLabelName() + "*";
            }else {
                break;
            }
        }
        editorService.updateLabelNameByLidAndUid(lid,uid,labelName);
        return editorService.findLabelsByUid(uid);
    }

    @RequestMapping("/deleteLabel.do")
    @ResponseBody
    public List deleteLabel(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer lid = jsonNode.findPath("lid").asInt();
        Integer uid = jsonNode.findPath("uid").asInt();
        String labelName = jsonNode.findPath("labelName").asText();
        ebbinghausService.deleteLabelNotebookByLid(lid);
        editorService.deleteLabelByLidAndUid(lid,uid);
        return editorService.findLabelsByUid(uid);
    }

    @RequestMapping("/deleteNotebook.do")
    @ResponseBody
    public User deleteNotebook(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer notebookId = jsonNode.findPath("notebookId").asInt();
        Integer userId = jsonNode.findPath("userId").asInt();
        Integer categoryId = jsonNode.findPath("categoryId").asInt();
        ebbinghausService.deleteLabelNotebookByNid(notebookId);
        editorService.updateNotebookTrashStatusByNid(notebookId,1);
        editorService.addNotebookToTrashStationByNidAndUid(userId,notebookId, DateUtil.today().getTime());
        //TODOq 删除笔记后，修改笔记notebooks，修改艾宾浩斯学习列表，修改垃圾回收中心trashStation
        List<Notebook> notebookList = editorService.findNotebooksByCid(categoryId, 0);
        List<TrashStation> trashStation = editorService.findTrashStationByUid(userId);
        List<TrashStation> trashStationList = new ArrayList<>();
        trashStation.forEach(tsl->{
            Notebook n;
            try {
                n = editorService.findNotebookByNid(tsl.getNid(), 1);
                tsl.setNotebookName(n.getNotebookName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            trashStationList.add(tsl);
        });
        User user = new User();
        user.setNotebooks(notebookList);
        user.setTrashStation(trashStationList);
        return user;
    }

    @RequestMapping("/deleteTrashStationById.do")
    @ResponseBody
    public List deleteTrashStationById(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer tsid = jsonNode.findPath("tsid").asInt();
        Integer userId = jsonNode.findPath("userId").asInt();
        TrashStation trashStation = editorService.findTrashStationByTsid(tsid);
        ebbinghausService.deleteLabelNotebookByNid(trashStation.getNid());
        editorService.deleteTrashStationByTsid(tsid);
        editorService.deleteNotebookByNid(trashStation.getNid());
        //查找trash_station垃圾回收中心数据
        List<TrashStation> list = editorService.findTrashStationByUid(userId);
        List<TrashStation> trashStationList = new ArrayList<TrashStation>();
        setTrashStationNotebookName(list, trashStationList);
        return trashStationList;
    }

    private void setTrashStationNotebookName(List<TrashStation> list, List<TrashStation> trashStationList) {
        list.forEach((ls)->{
            try {
                Notebook n = editorService.findNotebookByNid(ls.getNid(),1);
                ls.setNotebookName(n.getNotebookName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            trashStationList.add(ls);
        });
    }

    @RequestMapping("/retrieveNotebookFromTrashStation.do")
    @ResponseBody
    public User retrieveNotebookFromTrashStation(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer tsid = jsonNode.findPath("tsid").asInt();
        Integer userId = jsonNode.findPath("userId").asInt();
        Integer categoryId = jsonNode.findPath("categoryId").asInt();
        TrashStation ts = editorService.findTrashStationByTsid(tsid);
        editorService.updateNotebookTrashStatusByNid(ts.getNid(),0);
        editorService.deleteTrashStationByTsid(tsid);

        User user = new User();
        //查找trash_station垃圾回收中心数据
        List<TrashStation> trashStation = editorService.findTrashStationByUid(userId);
        List<TrashStation> trashStationList = new ArrayList<TrashStation>();
        setTrashStationNotebookName(trashStation, trashStationList);
        user.setTrashStation(trashStationList);
        //查找notebook
        List<Notebook> notebookList = null;
        if (categoryId>0){
            notebookList = editorService.findNotebooksByCid(categoryId, 0);
        }
        user.setNotebooks(notebookList);
        return user;
    }

    @RequestMapping("/addLabelToNotebook.do")
    @ResponseBody
    public ResultInfo addLabelToNotebook(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer uid = jsonNode.findPath("uid").asInt();
        Integer lid = jsonNode.findPath("lid").asInt();
        Integer nid = jsonNode.findPath("nid").asInt();
        LabelNotebook ln = ebbinghausService.findOneLabelNotebookByUidAndLidAndNid(uid,lid,nid,0);
        ResultInfo resultInfo = new ResultInfo();
        if (ln==null){
            editorService.addLabelToNotebook(uid,lid,nid,DateUtil.today().getTime());
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("添加标签成功！");
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("已添加过该标签！");
        }
        return resultInfo;
    }

    @RequestMapping("/findLabelNotebooksByLidAndUid.do")
    @ResponseBody
    public List findLabelNotebooksByLidAndUid(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer uid = jsonNode.findPath("uid").asInt();
        Integer lid = jsonNode.findPath("lid").asInt();
        List<LabelNotebook> labelNotebook = ebbinghausService.findLabelNotebooksByLidAndUid(uid, lid, 0);
        List<LabelNotebook> labelNotebookList = new ArrayList<>();
        labelNotebook.forEach((lt)->{
            try {
                Notebook n = editorService.findNotebookByNid(lt.getNid(), 0);
                if (n!=null){
                    lt.setLabelNotebookName(n.getNotebookName());
                    lt.setLabelNotebookContent(n.getNotebookContent());
                }
                Label l = editorService.findLabelByLid(lt.getLid());
                if (l!=null){
                    lt.setLabelName(l.getLabelName());
                }
                labelNotebookList.add(lt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return labelNotebookList;
    }

    @RequestMapping("/findLabelNotebookByLnid.do")
    @ResponseBody
    public LabelNotebook findLabelNotebookByLnid(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer lnid = jsonNode.findPath("lnid").asInt();
        LabelNotebook ln = ebbinghausService.findLabelNotebookByLnid(lnid, 0);
        Notebook n = editorService.findNotebookByNid(ln.getNid(),0);
        ln.setLabelNotebookName(n.getNotebookName());
        ln.setLabelNotebookContent(n.getNotebookContent());
        return ln;
    }

    @RequestMapping("/deleteLabelNotebook.do")
    @ResponseBody
    public User deleteLabelNotebook(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer lnid = jsonNode.findPath("lnid").asInt();
        Integer uid = jsonNode.findPath("uid").asInt();
        Integer lid = jsonNode.findPath("lid").asInt();
        ebbinghausService.deleteLabelNotebookByLnid(lnid);
        List<LabelNotebook> labelNotebook = ebbinghausService.findLabelNotebooksByLidAndUid(uid, lid, 0);
        List<LabelNotebook> labelNotebookList = new ArrayList<>();
        labelNotebook.forEach((lt)->{
            try {
                Notebook n = editorService.findNotebookByNid(lt.getNid(), 0);
                if (n!=null){
                    lt.setLabelNotebookName(n.getNotebookName());
                    lt.setLabelNotebookContent(n.getNotebookContent());
                    labelNotebookList.add(lt);
                }
            } catch (Exception e) {
            }
        });
        User u = new User();
        u.setLabelNotebooks(labelNotebookList);
        return u;
    }
}
