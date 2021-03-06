/*
 * Copyright &copy; <a href="http://www.lufengc.cc">lufengc</a> All rights reserved.
 */

package com.platform.modules.cms.service;

import com.platform.framework.common.BaseService;
import com.platform.modules.cms.bean.CmsCategory;

import java.util.List;

/**
 * 栏目Service
 *
 * @author lufengc
 * @version 2013-5-31
 */
public interface CategoryService extends BaseService<CmsCategory> {

    List<CmsCategory> getByUser(boolean isCurrentSite, String module) throws Exception;

    List<CmsCategory> getByParentId(String parentId, String siteId);

    List<CmsCategory> getByIds(String ids) throws Exception;

    void updateSort(Integer[] ids, Integer[] sortIds);

    List<CmsCategory> getByParentIdsLike(String parentIds);
}
