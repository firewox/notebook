package com.notebook_domain.domain;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/3/27 17:19
 */
public class Notebook {
    private Integer notebookId;
    private Integer categoryId;
    private String notebookName;
    private String notebookContent;
    private Integer trashStatus;

    public Integer getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(Integer notebookId) {
        this.notebookId = notebookId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    public String getNotebookContent() {
        return notebookContent;
    }

    public void setNotebookContent(String notebookContent) {
        this.notebookContent = notebookContent;
    }

    public Integer getTrashStatus() {
        return trashStatus;
    }

    public void setTrashStatus(Integer trashStatus) {
        this.trashStatus = trashStatus;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "notebookId=" + notebookId +
                ", categoryId=" + categoryId +
                ", notebookName='" + notebookName + '\'' +
                ", notebookContent='" + notebookContent + '\'' +
                ", trashStatus=" + trashStatus +
                '}';
    }
}
