package com.notebook_domain.domain;

import java.io.Serializable;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/3/29 15:12
 */
public class Label implements Serializable{
    private Integer labelId;
    private Integer userId;
    private String labelName;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelId=" + labelId +
                ", userId=" + userId +
                ", labelName='" + labelName + '\'' +
                '}';
    }
}
