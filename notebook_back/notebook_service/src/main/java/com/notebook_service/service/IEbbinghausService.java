package com.notebook_service.service;

import com.notebook_domain.domain.LabelNotebook;

import java.util.Date;
import java.util.List;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/4/3 9:55
 */

public interface IEbbinghausService {
    /**
     * 根据labelId，删除label_notebook中的记录
     * @param lid
     * @throws Exception
     */
    public abstract void deleteLabelNotebookByLid(Integer lid) throws Exception;

    /**
     * 根据notebookId删除label_notebook中对应的记录
     * @param notebookId
     * @throws Exception
     */
    public abstract void deleteLabelNotebookByNid(Integer notebookId) throws Exception;

    /**
     * 根据userId查找label_notebook数据
     * @param userId
     * @return
     * @throws Exception
     */
    public abstract List<LabelNotebook> findLabelNotebookByUid(Integer userId) throws Exception;

    /**
     * 根据uid和lid查找label_notebook中的记录
     * @param uid
     * @param lid
     * @param finished
     * @return
     * @throws Exception
     */
    public abstract List findLabelNotebooksByLidAndUid(Integer uid, Integer lid, Integer finished) throws Exception;

    /**
     * 根据uid lid nid 查询一个label_notebook表中的记录
     * @param uid
     * @param lid
     * @param nid
     * @param finished
     * @return
     * @throws Exception
     */
    public abstract LabelNotebook findOneLabelNotebookByUidAndLidAndNid(Integer uid, Integer lid, Integer nid, Integer finished) throws Exception;

    /**
     * 根据lnid查询label_notebook中的记录
     * @param lnid
     * @param finished
     * @return
     * @throws Exception
     */
    public abstract LabelNotebook findLabelNotebookByLnid(Integer lnid, Integer finished) throws Exception;

    /**\
     * 根据lnid删除label_notebook中的记录
     * @param lnid
     * @throws Exception
     */
    public abstract void deleteLabelNotebookByLnid(Integer lnid) throws Exception;

    /**
     * SM-2(艾宾浩斯遗忘记忆算法):
     * EF (easiness factor) is a rating for how difficult the card is.
     * Grade: 1 Set reps to 0 and interval to 1, keep current EF
     *        3 Set interval to 1, lower the EF, reps + 1
     *        5 Reps + 1, interval is calculated using EF, increasing in time.
     * @param lnid
     * @param grade
     * @throws Exception
     */
    public abstract void ebbinghausReview(Integer lnid, Integer grade) throws Exception;

    /**
     * 根据lnid更新label_notebook中记录
     * @param lnid
     * @param created
     * @param interval
     * @param repetition
     * @param easinessFactor
     * @param finished
     * @throws Exception
     */
    public abstract void updateLabelNotebookAllFieldByLnid(Integer lnid, Date created,Integer interval,Integer repetition,Double easinessFactor,Integer finished) throws Exception;

    /**
     * 彻底完成记忆，使该记录的finished=1
     * @param lnid
     * @param repetition
     * @param finished
     * @throws Exception
     */
    public abstract void finishedLabelNotebook(Integer lnid,Integer repetition,Integer finished) throws Exception;
}
