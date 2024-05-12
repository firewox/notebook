package com.notebook_service.service;

import com.notebook_domain.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/3/25 23:52
 */
public interface IEditorService {
    /**
     * 根据用户uid创建一个新的笔记本
     * @param uid
     * @param categoryName
     * @throws Exception
     */
    public abstract void createNewCategoryByUid(Integer uid, String categoryName) throws Exception;

    /**
     * 根据uid查询这个用户的所有笔记本
     * @param uid
     * @return
     */
    public abstract List<Category> findCategoriesByUid(Integer uid) throws Exception;

    /**
     * 根据用户uid和笔记本名字查询笔记本，用来判断新建笔记本名称是否有重复
     * 有重复就在名称后+*
     * @param uid
     * @param newCategoryName
     * @return
     * @throws Exception
     */
    public abstract Category findOneByCategoryNameAndUid(Integer uid,String newCategoryName) throws Exception;

    /**
     * 根据categoryId找到其下面对应的笔记们notebooks
     * @param cid
     * @return
     * @throws Exception
     */
    public abstract List<Notebook> findNotebooksByCid(Integer cid,Integer trashStatus) throws Exception;

    /**
     * 根据categoryId新建其分类下的notebook
     * @param cid
     * @param notebookName
     * @throws Exception
     */
    public abstract void createNewNotebookByCid(Integer cid, String notebookName) throws Exception;

    /**
     * 根据notebookId查询notebook
     * @param nid
     * @return
     * @throws Exception
     */
    public abstract Notebook findNotebookByNid(Integer nid,Integer trashStatus) throws Exception;

    /**
     * 根据笔记名字和笔记本cid查询笔记，用来判断新建笔记名称是否有重复
     * 有重复就在名称后+*
     * @param cid
     * @param notebookName
     * @return
     * @throws Exception
     */
    public abstract Notebook findOneByNotebookNameAndCid(Integer cid,String notebookName,Integer trashStatus) throws Exception;

    /**
     * 根据nid、cid保存notebookContent
     * @param nid
     * @param cid
     * @param notebookContent
     * @throws Exception
     */
    public abstract void saveNotebookContent(Integer nid, Integer cid, String notebookContent,Integer trashStatus) throws Exception;

    /**
     * 根据用户userId查询该用户所有的label
     * @param userId
     * @return
     * @throws Exception
     */
    public abstract List<Label> findLabelsByUid(Integer userId) throws Exception;

    /**
     * 根据标签名字和用户userId查询标签，用来判断新建标签名称是否有重复
     * 有重复就在名称后+*
     * @param userId
     * @param labelName
     * @return
     * @throws Exception
     */
    public abstract Label findOneByLabelNameAndUid(Integer userId, String labelName) throws Exception;

    /**
     * 根据用户userId和labelName新建一个新的标签
     * @param userId
     * @param labelName
     * @throws Exception
     */
    public abstract void createNewLabelByUid(Integer userId, String labelName) throws Exception;

    /**
     * 根据lid和uid修改对应的标签的名字labelName
     * @param lid
     * @param uid
     * @param labelName
     * @throws Exception
     */
    public abstract void updateLabelNameByLidAndUid(Integer lid, Integer uid, String labelName) throws Exception;

    /**
     * 根据labelId，删除label_notebook中的记录
     * @param lid
     * @throws Exception
     */
//    public abstract void deleteLabelNotebookByLid(Integer lid) throws Exception;

    /**
     * 根据labelId和userId，删除label中的记录
     * @param lid
     * @param uid
     * @throws Exception
     */
    public abstract void deleteLabelByLidAndUid(Integer lid, Integer uid) throws Exception;

    /**
     * 根据notebookId删除label_notebook中对应的记录
     * @param notebookId
     * @throws Exception
     */
//    public abstract void deleteLabelNotebookByNid(Integer notebookId) throws Exception;

    /**
     * 根据notebookId，将notebook中对应的记录的trash_status设置为trashStatus
     * 表示该记录预删除，进入到垃圾回收中心trash_station
     * @param notebookId
     * @throws Exception
     */
    public abstract void updateNotebookTrashStatusByNid(Integer notebookId,Integer trashStatus) throws Exception;

    /**
     * 将notebook中trash_status=1的记录添加到垃圾回收中心trash_station
     * @param notebookId
     * @param createdTime
     * @throws Exception
     */
    public abstract void addNotebookToTrashStationByNidAndUid(Integer userId,Integer notebookId, Date createdTime) throws Exception;

    /**
     * 根据用户userId查询垃圾回收中心trash_station的记录
     * @param userId
     * @return
     * @throws Exception
     */
    public abstract List<TrashStation> findTrashStationByUid(Integer userId) throws Exception;

    /**
     * 根据userId查找label_notebook数据
     * @param userId
     * @return
     * @throws Exception
     */
//    public abstract List<LabelNotebook> findLabelNotebookByUid(Integer userId) throws Exception;

    /**
     * 根据lid查询对应的label
     * @param lid
     * @return
     * @throws Exception
     */
    public abstract Label findLabelByLid(Integer lid) throws Exception;

    /**
     * 根据tsid找到trash_station中对应的数据
     * @param tsid
     * @return
     * @throws Exception
     */
    public abstract TrashStation findTrashStationByTsid(Integer tsid) throws Exception;

    /**
     * 根据nid删除notebook中对应的记录
     * @param nid
     * @throws Exception
     */
    public abstract void deleteNotebookByNid(Integer nid) throws Exception;

    /**
     * 根据tsid删除trash_station中对应的记录
     * @param tsid
     * @throws Exception
     */
    public abstract void deleteTrashStationByTsid(Integer tsid) throws Exception;

    /**
     * 根据uid lid nid 向label_notebook表中插入记录
     * @param uid
     * @param lid
     * @param nid
     * @param created
     * @throws Exception
     */
    public abstract void addLabelToNotebook(Integer uid, Integer lid, Integer nid,Date created) throws Exception;

    /**
     * 根据uid和lid查找label_notebook中的记录
     * @param uid
     * @param lid
     * @param finished
     * @return
     * @throws Exception
     */
//    public abstract List findLabelNotebooksByLidAndUid(Integer uid, Integer lid, Integer finished) throws Exception;

    /**
     * 根据uid lid nid 查询一个label_notebook表中的记录
     * @param uid
     * @param lid
     * @param nid
     * @param finished
     * @return
     * @throws Exception
     */
//    public abstract LabelNotebook findOneLabelNotebookByUidAndLidAndNid(Integer uid, Integer lid, Integer nid, Integer finished) throws Exception;

    /**
     * 根据lnid查询label_notebook中的记录
     * @param lnid
     * @param finished
     * @return
     * @throws Exception
     */
//    public abstract LabelNotebook findLabelNotebookByLnid(Integer lnid, Integer finished) throws Exception;

    /**\
     * 根据lnid删除label_notebook中的记录
     * @param lnid
     * @throws Exception
     */
//    public abstract void deleteLabelNotebookByLnid(Integer lnid) throws Exception;
}
