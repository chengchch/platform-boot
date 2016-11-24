package com.platform.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lufengc
 * @date 2016/11/3
 */
@Configuration
@ConfigurationProperties
public class SystemProperties {

    private String productName;
    private String fileUploadPath;
    private String fileAccessPath;
    private String staticFileSuffix;
    private String urlSuffix;
    private String adminPath;
    private String frontPath;
    private String userKickoutAfter;
    private String userMaxSession;
    private boolean notAllowRefreshIndex;
    private String casServerUrl;
    private String casProjectUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public String getFileAccessPath() {
        return fileAccessPath;
    }

    public void setFileAccessPath(String fileAccessPath) {
        this.fileAccessPath = fileAccessPath;
    }

    public String getStaticFileSuffix() {
        return staticFileSuffix;
    }

    public void setStaticFileSuffix(String staticFileSuffix) {
        this.staticFileSuffix = staticFileSuffix;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }

    public String getAdminPath() {
        return adminPath;
    }

    public void setAdminPath(String adminPath) {
        this.adminPath = adminPath;
    }

    public String getFrontPath() {
        return frontPath;
    }

    public void setFrontPath(String frontPath) {
        this.frontPath = frontPath;
    }

    public String getUserKickoutAfter() {
        return userKickoutAfter;
    }

    public void setUserKickoutAfter(String userKickoutAfter) {
        this.userKickoutAfter = userKickoutAfter;
    }

    public String getUserMaxSession() {
        return userMaxSession;
    }

    public void setUserMaxSession(String userMaxSession) {
        this.userMaxSession = userMaxSession;
    }

    public boolean isNotAllowRefreshIndex() {
        return notAllowRefreshIndex;
    }

    public void setNotAllowRefreshIndex(boolean notAllowRefreshIndex) {
        this.notAllowRefreshIndex = notAllowRefreshIndex;
    }

    public String getCasServerUrl() {
        return casServerUrl;
    }

    public void setCasServerUrl(String casServerUrl) {
        this.casServerUrl = casServerUrl;
    }

    public String getCasProjectUrl() {
        return casProjectUrl;
    }

    public void setCasProjectUrl(String casProjectUrl) {
        this.casProjectUrl = casProjectUrl;
    }
}