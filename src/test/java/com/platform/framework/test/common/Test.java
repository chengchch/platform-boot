package com.platform.framework.test.common;

import com.platform.framework.common.MybatisDao;
import com.platform.framework.util.StringUtils;

import java.net.URL;
import java.util.Objects;

/**
 * @author Administrator
 * @version 2016/9/28
 */
public class Test {
    public static String idsToString(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            // 查询排序 id设置为字符串形式
            String[] split = ids.split(",");
            String idStr = "";
            for (String s : split) {
                if (Objects.equals(idStr, "")) {
                    idStr = "'" + s + "'";
                } else {
                    idStr += ",'" + s + "'";
                }
            }
            return idStr;
        } else {
            return ids;
        }
    }

    public static void main(String[] args) {
        String path = MybatisDao.class.getName();
        System.out.println(path);
    }
}
