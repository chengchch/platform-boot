/*
 * Copyright &copy; <a href="http://www.zsteel.cc">zsteel</a> All rights reserved.
 */

package com.platform.framework.common;

import com.platform.framework.util.DateUtils;
import com.platform.framework.util.StringUtils;

import java.util.Date;

/**
 * 向客户端传递状态(客户端只接收状态)
 *
 * @author lufengcheng
 * @date 2016-01-15 09:56:22
 */
public class Result {

    /**
     * 1XX表示客户端的错误，2XX表示服务端的错误
     * 0：成功
     * 100：请求错误
     * 101：缺少appKey
     * 102：缺少签名
     * 103：缺少参数
     * 200：服务器出错
     * 201：服务不可用
     * 202：服务器正在重启
     */
    private String code = "0";
    private String message;
    private String url;
    private String systime;
    private Object data;

    public Result() {
    }

    public Result(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public Result(String code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSystime() {
        if (StringUtils.isEmpty(systime)) {
            systime = DateUtils.formatDateTime(new Date());
        }
        return systime;
    }

    public void setSystime(String systime) {
        this.systime = systime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
