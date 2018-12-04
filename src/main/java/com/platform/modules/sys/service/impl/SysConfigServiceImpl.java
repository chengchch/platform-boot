/*
 * Copyright &copy; <a href="http://www.lufengc.cc">lufengc</a> All rights reserved.
 */

package com.platform.modules.sys.service.impl;

import com.platform.framework.common.BaseServiceImpl;
import com.platform.framework.common.MybatisDao;
import com.platform.modules.sys.bean.SysConfig;
import com.platform.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典表service实现类
 *
 * @author lufengc
 * @date 2016/1/16 14:05
 */
@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements SysConfigService {
    @Autowired
    private MybatisDao mybatisDao;

    /**
     * 保存
     *
     * @param object SysDict
     * @return ID
     * @throws Exception
     */
    @Override
    public String save(SysConfig object) throws Exception {
        if (object.getId() != null) {
            mybatisDao.update(object);
        } else {
            mybatisDao.insert(object);
        }
        return object.getId();
    }

}
