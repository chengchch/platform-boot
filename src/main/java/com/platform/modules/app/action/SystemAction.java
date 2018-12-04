/*
 * Copyright &copy; <a href="http://www.lufengc.cc">lufengc</a> All rights reserved.
 */

package com.platform.modules.app.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.framework.common.BaseFrontAction;
import com.platform.framework.common.Result;
import com.platform.framework.exception.CommonException;
import com.platform.modules.app.service.SystemService;
import com.platform.modules.sys.bean.SysUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @version 2016/9/12
 */
@RestController
@RequestMapping(value = "api")
public class SystemAction extends BaseFrontAction {

    @Autowired
    private SystemService systemService;

    @ApiOperation(value = "登录", notes = "用户登录")
    @RequestMapping(value = "/appLogin", method = RequestMethod.POST)
    public String appLogin(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String username = request.getParameter("username");
        json.put("username", username);
        return json.toString();
    }

    @ApiOperation(value = "获取用户", notes = "根据用户id查询用户信息")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public String getUserInfo(HttpServletRequest request, String id) {
        Result result;
        SysUser user;
        try {
            user = systemService.get(id);
            if (user != null) {
                result = new Result("0", "获取用户信息成功！", user);
            } else {
                result = new Result("0", "用户信息不存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result("200", "获取用户信息出错！");
        }
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public String register(Integer a) throws Exception {
        try {
			systemService.register();
        } catch (Exception e) {
            throw new CommonException(e);
        }
        Result result = new Result("1", "注册成功");
        return JSON.toJSONString(result);
    }

}
