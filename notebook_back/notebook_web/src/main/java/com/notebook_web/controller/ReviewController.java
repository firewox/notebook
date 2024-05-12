package com.notebook_web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notebook_domain.domain.LabelNotebook;
import com.notebook_domain.domain.Notebook;
import com.notebook_service.service.IEbbinghausService;
import com.notebook_service.service.IEditorService;
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
 * @Date:Create：in 2022/4/3 11:53
 */
@Controller
@CrossOrigin
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private IEbbinghausService ebbinghausService;

    @Autowired
    private IEditorService editorService;

    @RequestMapping("/ebbinghausReview.do")
    @ResponseBody
    public List ebbinghausReview(@RequestBody String info) throws Exception{
        JsonNode jsonNode = ObjectMapperUtils.readTree(info);
        Integer lnid = jsonNode.findPath("lnid").asInt();
        Integer uid = jsonNode.findPath("uid").asInt();
        Integer lid = jsonNode.findPath("lid").asInt();
        Integer grade = jsonNode.findPath("grade").asInt();
        ebbinghausService.ebbinghausReview(lnid,grade);
        List<LabelNotebook> labelNotebook = ebbinghausService.findLabelNotebooksByLidAndUid(uid, lid, 0);
        List<LabelNotebook> labelNotebookList = new ArrayList<>();
        for (LabelNotebook ln : labelNotebook) {
            Notebook n = editorService.findNotebookByNid(ln.getNid(), 0);
            if (n!=null){
                ln.setLabelNotebookName(n.getNotebookName());
                ln.setLabelNotebookContent(n.getNotebookContent());
                labelNotebookList.add(ln);
            }
        }
        return labelNotebookList;
    }

}
