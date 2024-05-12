package com.notebook_domain.domain;

import java.io.Serializable;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/3/26 15:01
 */
public class Category implements Serializable{
    private Integer categoryId;
    private Integer userId;
    private String categoryName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
