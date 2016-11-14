/*
 * Copyright &copy; <a href="http://www.zsteel.cc">zsteel</a> All rights reserved.
 */
package com.platform.modules.cms.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论Entity
 *
 * @author lufengc
 * @version 2013-05-15
 */
public class CmsComment implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String categoryId;// 分类编号
    private String contentId;    // 归属分类内容的编号（Article.id、Photo.id、Download.id）
    private String title;    // 归属分类内容的标题（Article.title、Photo.title、Download.title）
    private String content; // 评论内容
    private String name;    // 评论姓名
    private String ip;        // 评论IP
    private Date createDate;// 评论时间
    private Integer auditUserId; // 审核人
    private Date auditDate;    // 审核时间
    private String delFlag;    // 删除标记删除标记（0：正常；1：删除；2：审核）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}