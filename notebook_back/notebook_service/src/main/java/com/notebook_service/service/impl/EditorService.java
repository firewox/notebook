package com.notebook_service.service.impl;

import com.notebook_dao.dao.IEditorDao;
import com.notebook_domain.domain.*;
import com.notebook_service.service.IEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/3/25 23:56
 */
@Service
@Transactional
public class EditorService implements IEditorService {

    @Autowired
    private IEditorDao editorDao;

    @Override
    public void createNewCategoryByUid(Integer uid, String categoryName) throws Exception{
        editorDao.createNewCategoryByUid(uid,categoryName);
    }

    @Override
    public List<Category> findCategoriesByUid(Integer uid) throws Exception{
        return editorDao.findCategoriesByUid(uid);
    }

    @Override
    public Category findOneByCategoryNameAndUid(Integer uid,String newCategoryName) throws Exception {
        return editorDao.findOneByCategoryNameAndUid(uid,newCategoryName);
    }

    @Override
    public List<Notebook> findNotebooksByCid(Integer cid,Integer trashStatus) throws Exception {
        return editorDao.findNotebooksByCid(cid,trashStatus);
    }

    @Override
    public void createNewNotebookByCid(Integer cid, String notebookName) throws Exception {
        editorDao.createNewNotebookByCid(cid,notebookName);
    }

    @Override
    public Notebook findNotebookByNid(Integer nid,Integer trashStatus) throws Exception {
        return editorDao.findNotebookByNid(nid,trashStatus);
    }

    @Override
    public Notebook findOneByNotebookNameAndCid(Integer cid,String notebookName,Integer trashStatus) throws Exception {
        return editorDao.findOneByNotebookNameAndCid(cid,notebookName,trashStatus);
    }

    @Override
    public void saveNotebookContent(Integer nid, Integer cid, String notebookContent,Integer trashStatus) throws Exception {
        editorDao.saveNotebookContent(nid,cid,notebookContent,trashStatus);
    }

    @Override
    public List<Label> findLabelsByUid(Integer userId) throws Exception {
        return editorDao.findLabelsByUid(userId);
    }

    @Override
    public Label findOneByLabelNameAndUid(Integer userId, String labelName) throws Exception {
        return editorDao.findOneByLabelNameAndUid(userId,labelName);
    }

    @Override
    public void createNewLabelByUid(Integer userId, String labelName) throws Exception {
        editorDao.createNewLabelByUid(userId,labelName);
    }

    @Override
    public void updateLabelNameByLidAndUid(Integer lid, Integer uid, String labelName) throws Exception {
        editorDao.updateLabelNameByLidAndUid(lid,uid,labelName);
    }


    @Override
    public void deleteLabelByLidAndUid(Integer lid, Integer uid) throws Exception {
        editorDao.deleteLabelByLidAndUid(lid,uid);
    }


    @Override
    public void updateNotebookTrashStatusByNid(Integer notebookId,Integer trashStatus) throws Exception {
        editorDao.updateNotebookTrashStatusByNid(notebookId,trashStatus);
    }

    @Override
    public void addNotebookToTrashStationByNidAndUid(Integer userId,Integer notebookId, Date createdTime) throws Exception {
        editorDao.addNotebookToTrashStationByNidAndUid(userId,notebookId,createdTime);
    }

    @Override
    public List<TrashStation> findTrashStationByUid(Integer userId) throws Exception {
        return editorDao.findTrashStationByUid(userId);
    }

    @Override
    public Label findLabelByLid(Integer lid) throws Exception {
        return editorDao.findLabelByLid(lid);
    }

    @Override
    public TrashStation findTrashStationByTsid(Integer tsid) throws Exception {
        return editorDao.findTrashStationByTsid(tsid);
    }

    @Override
    public void deleteNotebookByNid(Integer nid) throws Exception {
        editorDao.deleteNotebookByNid(nid);
    }

    @Override
    public void deleteTrashStationByTsid(Integer tsid) throws Exception {
        editorDao.deleteTrashStationByTsid(tsid);
    }

    @Override
    public void addLabelToNotebook(Integer uid, Integer lid, Integer nid,Date created) throws Exception {
        editorDao.addLabelToNotebook(uid,lid,nid,created);
    }

}
