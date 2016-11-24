/*
 * Copyright &copy; <a href="http://www.zsteel.cc">zsteel</a> All rights reserved.
 */
package com.platform.framework.common;

import com.google.common.collect.Maps;
import com.platform.framework.config.SystemProperties;
import com.platform.framework.util.PropertiesLoader;
import com.platform.framework.util.StringUtils;
import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 系统配置管理类,负责系统整个配置的读取、缓存、修改
 *
 * @author lufengcheng
 * @date 2016-01-15 09:56:22
 */
public class SysConfigManager {

    //属性文件加载对象
    private static PropertiesLoader loader = new PropertiesLoader("application-dev.yml");

    //保存全局属性值
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 获取当前系统部署目录
     *
     * @return 路径
     */
    public static String getSysRootPath() {
        String sysRootPath = System.getProperty("webapp.root");
        if (sysRootPath == null) {
            String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
            String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
            String separator = System.getProperty("file.separator");
            sysRootPath = temp.replaceAll("/", separator + separator);
        }
        return sysRootPath;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * 获取上传文件的根目录
     *
     * @return path
     */
    public static String getFileUploadPath() {
        String dir = getConfig("fileUploadPath");
        if (StringUtils.isBlank(dir)) {
            dir = getSysRootPath() + "/uploads/";
        }
        return dir;
    }

    /**
     * 获取文件服务器地址
     *
     * @return url
     */
    public static String getFileAccessPath() {
        String url = getConfig("fileAccessPath");
        if (StringUtils.isBlank(url)) {
            url = "/";
        }
        return url;
    }

    /**
     * 获取管理端根路径
     */
    public static String getAdminPath() {
        String path = getConfig("adminPath");
        if (StringUtils.isBlank(path)) {
            path = "/";
        }
        return path;
    }

    /**
     * 获取前端根路径
     */
    public static String getFrontPath() {
        String path = getConfig("frontPath");
        if (StringUtils.isBlank(path)) {
            path = "/";
        }
        return path;
    }

    /**
     * 静态文件后缀
     */
    public static String getStaticFileSuffix() {
        return getConfig("staticFileSuffix");
    }

    /**
     * 获取URL后缀
     */
    public static String getUrlSuffix() {
        return getConfig("urlSuffix");
    }

    /**
     * 获取产品名称
     */
    public static String getProductName() {
        return getConfig("productName");
    }

    /**
     * 获取工程路径
     *
     * @return 路径
     */
    public static String getProjectPath() {
        // 如果配置了工程路径，则直接返回，否则自动获取。
        String projectPath = getConfig("projectPath");
        if (StringUtils.isNotBlank(projectPath)) {
            return projectPath;
        }
        try {
            File file = new DefaultResourceLoader().getResource("").getFile();
            if (file != null) {
                while (true) {
                    File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
                    if (f.exists()) {
                        break;
                    }
                    if (file.getParentFile() != null) {
                        file = file.getParentFile();
                    } else {
                        break;
                    }
                }
                projectPath = file.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectPath;
    }

}

