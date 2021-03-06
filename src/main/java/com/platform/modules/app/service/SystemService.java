/*
 * Copyright &copy; <a href="http://www.lufengc.cc">lufengc</a> All rights reserved.
 */

package com.platform.modules.app.service;

import com.platform.framework.common.BaseService;
import com.platform.modules.sys.bean.SysUser;

/**
 * 用户service实现类
 *
 * @author lufengcheng
 * @date 2016-01-13 09:41:50
 */
public interface SystemService extends BaseService<SysUser> {

    void register() throws Exception;
}
