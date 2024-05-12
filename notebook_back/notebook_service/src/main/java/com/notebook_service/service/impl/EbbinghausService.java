package com.notebook_service.service.impl;

import com.notebook_dao.dao.IEditorDao;
import com.notebook_domain.domain.LabelNotebook;
import com.notebook_service.service.IEbbinghausService;
import com.notebook_utils.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/4/3 9:59
 */
@Service
@Transactional
public class EbbinghausService implements IEbbinghausService {

    @Autowired
    private IEditorDao editorDao;

    @Override
    public void deleteLabelNotebookByLid(Integer lid) throws Exception {
        editorDao.deleteLabelNotebookByLid(lid);
    }

    @Override
    public void deleteLabelNotebookByNid(Integer notebookId) throws Exception {
        editorDao.deleteLabelNotebookByNid(notebookId);
    }

    @Override
    public List<LabelNotebook> findLabelNotebookByUid(Integer userId) throws Exception {
        List<LabelNotebook> list = editorDao.findLabelNotebookByUid(userId);
        List<LabelNotebook> labelNotebookList = new ArrayList<>();
        for (LabelNotebook ln : list) {
            if (ln.getFinished()==0){
                int day = DateUtil.getDayBetweenTwoDate(ln.getCreated(), DateUtil.today().getTime());
                if (day>=ln.getInterval()){
                    labelNotebookList.add(ln);
                }
            }
        }
        return labelNotebookList;
    }

    @Override
    public List findLabelNotebooksByLidAndUid(Integer uid, Integer lid, Integer finished) throws Exception {
        List<LabelNotebook> list = editorDao.findLabelNotebooksByLidAndUid(uid, lid, finished);
        List<LabelNotebook> labelNotebookList = new ArrayList<>();
        for (LabelNotebook ln : list) {
            if (finished==0){
                int day = DateUtil.getDayBetweenTwoDate(ln.getCreated(), DateUtil.today().getTime());
                if (day>=ln.getInterval()){
                    labelNotebookList.add(ln);
                }
            }
        }
        return labelNotebookList;
    }

    @Override
    public LabelNotebook findOneLabelNotebookByUidAndLidAndNid(Integer uid, Integer lid, Integer nid, Integer finished) throws Exception {
        return editorDao.findOneLabelNotebookByUidAndLidAndNid(uid,lid,nid,finished);
    }

    @Override
    public LabelNotebook findLabelNotebookByLnid(Integer lnid, Integer finished) throws Exception {
        LabelNotebook ln = editorDao.findLabelNotebookByLnid(lnid, finished);
        if (finished==0){
            int day = DateUtil.getDayBetweenTwoDate(ln.getCreated(), DateUtil.today().getTime());
            if (day>=ln.getInterval()){
                return ln;
            }
        }
        return null;
    }

    @Override
    public void deleteLabelNotebookByLnid(Integer lnid) throws Exception {
        editorDao.deleteLabelNotebookByLnid(lnid);
    }

    @Override
    public void ebbinghausReview(Integer lnid, Integer grade) throws Exception {
        LabelNotebook ln = findLabelNotebookByLnid(lnid,0);
        Double newEF = ln.getEasinessFactor();
        if (grade==1){
            ln.setInterval(1);
            ln.setRepetition(0);
        }else{
            newEF = newEF + (0.1-(5-grade)*(0.08+(5-grade)*0.02));
            newEF = Math.round(newEF * 100.0)/100.0; // force java to round number down to 2 decimals
            ln.setRepetition(ln.getRepetition()+1);
            if(grade==3){
                ln.setInterval(1);
            }else if (grade==5){
                switch (ln.getRepetition()){
                    case 1:
                        ln.setInterval(1);
                        break;
                    case 2:
                        ln.setInterval(6);
                        break;
                    default:
                        //ln.setInterval((int)(Math.round((ln.getInterval()-1)*ln.getEasinessFactor())));
                        ln.setInterval((int)(Math.round((ln.getInterval())*ln.getEasinessFactor())));
                        break;
                }
            }
            if (newEF<1.3){// 1.3 is the minimum EF
                ln.setEasinessFactor(1.3);
            }else{
                ln.setEasinessFactor(newEF);
            }
        }
        ln.setCreated(DateUtil.today().getTime());
        //当 repetition>9 and easiness_factor>=3.5 判断该笔记已经彻底完成记忆
        if (ln.getRepetition()>9 && ln.getEasinessFactor()>=3.5){
            finishedLabelNotebook(ln.getLnid(),ln.getRepetition(),1);
        }else{
            //TODOq 根据lnid，更新label_notebook记录
            updateLabelNotebookAllFieldByLnid(ln.getLnid(),ln.getCreated(),ln.getInterval(),ln.getRepetition(),ln.getEasinessFactor(),0);
        }
    }

    @Override
    public void updateLabelNotebookAllFieldByLnid(Integer lnid, Date created, Integer interval, Integer repetition, Double easinessFactor, Integer finished) throws Exception {
        editorDao.updateLabelNotebookAllFieldByLnid(lnid,created,interval,repetition,easinessFactor,finished);
    }

    @Override
    public void finishedLabelNotebook(Integer lnid, Integer repetition,Integer finished) throws Exception {
        editorDao.finishedLabelNotebook(lnid,repetition,finished);
    }


}
