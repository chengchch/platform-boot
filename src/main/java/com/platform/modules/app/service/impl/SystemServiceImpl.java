/*
 * Copyright &copy; <a href="http://www.zsteel.cc">zsteel</a> All rights reserved.
 */

package com.platform.modules.app.service.impl;

import com.platform.framework.common.BaseServiceImpl;
import com.platform.framework.common.MybatisDao;
import com.platform.modules.app.service.SystemService;
import com.platform.modules.sys.bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service实现类
 *
 * @author lufengcheng
 * @date 2016-01-15 09:53:27
 */
@Service
@Transactional(readOnly = true)
public class SystemServiceImpl extends BaseServiceImpl<SysUser> implements SystemService {

    @Autowired
    private MybatisDao mybatisDao;

    /**
     * 保存用户信息
     *
     * @param object SysUser
     * @throws Exception
     */
    @Override
    public String save(SysUser object) throws Exception {
        return "";
    }


    @Transactional
    public void register() throws Exception{
        SysUser user = new SysUser();
        user.setRealName("测试");
        mybatisDao.insertBySql("INSERT INTO sys_user set username = \"111111\"");
        throw new RuntimeException("Roll me back!");
    }
}
