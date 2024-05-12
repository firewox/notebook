package com.notebook_dao.dao;

import com.notebook_domain.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/3/25 23:58
 */
public interface IEditorDao {

    @Insert("INSERT INTO category(uid,category_name) VALUES(#{uid},#{categoryName})")
    void createNewCategoryByUid(@Param("uid") Integer uid, @Param("categoryName") String categoryName) throws Exception;

    @Select("select * from category where uid=#{uid}")
    @Results({
            @Result(id = true,property = "categoryId",column = "cid"),
            @Result(property = "userId",column = "uid"),
            @Result(property = "categoryName",column = "category_name")
    })
    List<Category> findCategoriesByUid(Integer uid) throws Exception;

    @Select("select * from category where uid=#{uid} and category_name=#{newCategoryName}")
    @Results({
            @Result(id = true,property = "categoryId",column = "cid"),
            @Result(property = "userId",column = "uid"),
            @Result(property = "categoryName",column = "category_name")
    })
    Category findOneByCategoryNameAndUid(@Param("uid") Integer uid,@Param("newCategoryName") String newCategoryName) throws Exception;

    @Select("select * from notebook where cid=#{cid} and trash_status=#{trashStatus}")
    @Results({
            @Result(id = true,property = "notebookId",column = "nid"),
            @Result(property = "categoryId",column = "cid"),
            @Result(property = "notebookName",column = "notebook_name"),
            @Result(property = "notebookContent",column = "notebook_content"),
            @Result(property = "trashStatus",column = "trash_status")
    })
    List<Notebook> findNotebooksByCid(@Param("cid") Integer cid,@Param("trashStatus") Integer trashStatus) throws Exception;

    @Insert("insert into notebook(cid,notebook_name) values(#{cid},#{notebookName})")
    void createNewNotebookByCid(@Param("cid") Integer cid, @Param("notebookName") String notebookName) throws Exception;

    @Select("select * from notebook where nid = #{nid} and trash_status=#{trashStatus}")
    @Results({
            @Result(id = true,property = "notebookId",column = "nid"),
            @Result(property = "categoryId",column = "cid"),
            @Result(property = "notebookName",column = "notebook_name"),
            @Result(property = "notebookContent",column = "notebook_content"),
            @Result(property = "trashStatus",column = "trash_status"),
    })
    Notebook findNotebookByNid(@Param("nid") Integer nid, @Param("trashStatus") Integer trashStatus) throws Exception;

    @Select("select * from notebook where notebook_name = #{notebookName} and cid=#{cid} and trash_status=#{trashStatus}")
    @Results({
            @Result(id = true,property = "notebookId",column = "nid"),
            @Result(property = "categoryId",column = "cid"),
            @Result(property = "notebookName",column = "notebook_name"),
            @Result(property = "notebookContent",column = "notebook_content"),
            @Result(property = "trashStatus",column = "trash_status"),
    })
    Notebook findOneByNotebookNameAndCid(@Param("cid") Integer cid,@Param("notebookName") String notebookName, @Param("trashStatus") Integer trashStatus) throws Exception;

    @Update("update notebook set notebook_content=#{notebookContent} where nid=#{nid} and cid=#{cid} and trash_status=#{trashStatus}")
    void saveNotebookContent(@Param("nid") Integer nid, @Param("cid") Integer cid, @Param("notebookContent") String notebookContent, @Param("trashStatus") Integer trashStatus) throws Exception;

    @Select("select * from label where uid=#{userId}")
    @Results({
            @Result(id = true,property = "labelId",column = "lid"),
            @Result(property = "userId",column = "uid"),
            @Result(property = "labelName",column = "label_name"),
    })
    List<Label> findLabelsByUid(Integer userId) throws Exception;

    @Select("select * from label where uid=#{userId} and label_name=#{labelName}")
    @Results({
            @Result(id = true,property = "labelId",column = "lid"),
            @Result(property = "userId",column = "uid"),
            @Result(property = "labelName",column = "label_name"),
    })
    Label findOneByLabelNameAndUid(@Param("userId") Integer userId, @Param("labelName") String labelName) throws Exception;

    @Insert("insert into label(uid,label_name) values(#{userId},#{labelName})")
    void createNewLabelByUid(@Param("userId") Integer userId, @Param("labelName") String labelName) throws Exception;

    @Update("update label set label_name=#{labelName} where lid=#{lid} and uid=#{uid}")
    void updateLabelNameByLidAndUid(@Param("lid") Integer lid, @Param("uid") Integer uid, @Param("labelName") String labelName) throws Exception;

    @Delete("DELETE FROM label_notebook WHERE lid=#{lid}")
    void deleteLabelNotebookByLid(Integer lid) throws Exception;

    @Delete("delete from label where lid=#{lid} and uid=#{uid}")
    void deleteLabelByLidAndUid(@Param("lid") Integer lid, @Param("uid") Integer uid) throws Exception;

    @Delete("delete from label_notebook where nid=#{notebookId}")
    void deleteLabelNotebookByNid(Integer notebookId) throws Exception;

    @Update("update notebook set trash_status=#{trashStatus} where nid=#{notebookId}")
    void updateNotebookTrashStatusByNid(@Param("notebookId") Integer notebookId,@Param("trashStatus") Integer trashStatus) throws Exception;

    @Insert("insert into trash_station(uid,nid,created) values(#{userId},#{notebookId},#{createdTime})")
    void addNotebookToTrashStationByNidAndUid(@Param("userId")Integer userId,@Param("notebookId") Integer notebookId, @Param("createdTime") Date createdTime) throws Exception;

    @Select("select * from trash_station where uid=#{userId}")
    @Results({
            @Result(id = true,property = "tsid",column = "tsid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "nid",column = "nid"),
            @Result(property = "created",column = "created"),
    })
    List<TrashStation> findTrashStationByUid(Integer userId) throws Exception;

    @Select("select * from label_notebook where uid=#{userId}")
    @Results({
            @Result(id = true,property = "lnid",column = "lnid"),
            @Result(property = "lid",column = "lid"),
            @Result(property = "nid",column = "nid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "created",column = "created"),
            @Result(property = "interval",column = "interval_"),
            @Result(property = "repetition",column = "repetition"),
            @Result(property = "easiness_factor",column = "easinessFactor"),
            @Result(property = "finished",column = "finished"),
    })
    List<LabelNotebook> findLabelNotebookByUid(Integer userId) throws Exception;

    @Select("select * from label where lid=#{lid}")
    @Results({
            @Result(id = true,property = "labelId",column = "lid"),
            @Result(property = "userId",column = "uid"),
            @Result(property = "labelName",column = "label_name"),
    })
    Label findLabelByLid(Integer lid) throws Exception;

    @Select("select * from trash_station where tsid=#{tsid}")
    @Results({
            @Result(id = true,property = "tsid",column = "tsid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "nid",column = "nid"),
            @Result(property = "created",column = "created"),
    })
    TrashStation findTrashStationByTsid(Integer tsid) throws Exception;

    @Delete("delete from notebook where nid=#{nid}")
    void deleteNotebookByNid(Integer nid) throws Exception;

    @Delete("delete from trash_station where tsid=#{tsid}")
    void deleteTrashStationByTsid(Integer tsid) throws Exception;

    @Insert("insert into label_notebook(uid,lid,nid,created) values(#{uid},#{lid},#{nid},#{created})")
    void addLabelToNotebook(@Param("uid") Integer uid, @Param("lid") Integer lid, @Param("nid") Integer nid,@Param("created")Date created) throws Exception;

    @Select("select * from label_notebook where uid=#{uid} and lid=#{lid} and finished=#{finished}")
    @Results({
            @Result(id = true,property = "lnid",column = "lnid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "lid",column = "lid"),
            @Result(property = "nid",column = "nid"),
            @Result(property = "created",column = "created"),
            @Result(property = "interval",column = "interval_"),
            @Result(property = "repetition",column = "repetition"),
            @Result(property = "easinessFactor",column = "easiness_factor"),
            @Result(property = "finished",column = "finished"),
    })
    List<LabelNotebook> findLabelNotebooksByLidAndUid(@Param("uid") Integer uid, @Param("lid") Integer lid, @Param("finished") Integer finished) throws Exception;

    @Select("select * from label_notebook where uid=#{uid} and lid=#{lid} and nid=#{nid}")
    LabelNotebook findOneLabelNotebookByUidAndLidAndNid(@Param("uid")Integer uid, @Param("lid")Integer lid, @Param("nid")Integer nid, @Param("finished") Integer finished) throws Exception;

    @Select("select * from label_notebook where lnid=#{lnid} and finished=#{finished}")
    @Results({
            @Result(id = true,property = "lnid",column = "lnid"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "lid",column = "lid"),
            @Result(property = "nid",column = "nid"),
            @Result(property = "created",column = "created"),
            @Result(property = "interval",column = "interval_"),
            @Result(property = "repetition",column = "repetition"),
            @Result(property = "easinessFactor",column = "easiness_factor"),
            @Result(property = "finished",column = "finished"),
    })
    LabelNotebook findLabelNotebookByLnid(@Param("lnid") Integer lnid, @Param("finished") Integer finished) throws Exception;

    @Delete("delete from label_notebook where lnid=#{lnid}")
    void deleteLabelNotebookByLnid(Integer lnid) throws Exception;

    @Update("update label_notebook set created=#{created},interval_=#{interval},repetition=#{repetition},easiness_factor=#{easinessFactor} where lnid=#{lnid} and finished=#{finished}")
    void updateLabelNotebookAllFieldByLnid(@Param("lnid") Integer lnid,@Param("created")  Date created, @Param("interval")Integer interval, @Param("repetition")Integer repetition, @Param("easinessFactor")Double easinessFactor, @Param("finished")Integer finished) throws Exception;

    @Update("update label_notebook set finished=#{finished},repetition=#{repetition} where lnid=#{lnid}")
    void finishedLabelNotebook(@Param("lnid")Integer lnid,@Param("repetition")Integer repetition,@Param("finished") Integer finished) throws Exception;

    @Delete("delete from trash_station where uid=#{uid}")
    void deleteTrashStationByUid(Integer uid) throws Exception;

    @Delete("delete from label_notebook where uid=#{uid}")
    void deleteLabelNotebookByUid(Integer uid) throws Exception;

    @Delete("delete from label where uid=#{uid}")
    void deleteLabelByUid(Integer uid) throws Exception;

    @Delete("delete from notebook where cid=#{categoryId}")
    void deleteNotebookByCid(Integer categoryId) throws Exception;

    @Delete("delete from category where uid=#{uid}")
    void deleteCategoryByUid(Integer uid) throws Exception;
}
