package com.notebook_domain.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Liyutian
 * @Date:Create：in 2022/2/24 11:25
 */
public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private Integer gender;
    private Date birthday;
    private Date created;
    private Integer status = 0;
    private String salt;
    private String verificationCode;
    private List<Category> categories;
    private List<Notebook> notebooks;
    private List<Label> labels;
    private List<TrashStation> trashStation;
    private List<LabelNotebook> labelNotebooks;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", created=" + created +
                ", status=" + status +
                ", salt='" + salt + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", categories=" + categories +
                ", notebooks=" + notebooks +
                ", labels=" + labels +
                ", trashStation=" + trashStation +
                ", labelNotebooks=" + labelNotebooks +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(List<Notebook> notebooks) {
        this.notebooks = notebooks;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<TrashStation> getTrashStation() {
        return trashStation;
    }

    public void setTrashStation(List<TrashStation> trashStation) {
        this.trashStation = trashStation;
    }

    public List<LabelNotebook> getLabelNotebooks() {
        return labelNotebooks;
    }

    public void setLabelNotebooks(List<LabelNotebook> labelNotebooks) {
        this.labelNotebooks = labelNotebooks;
    }
}
