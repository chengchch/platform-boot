/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.platform.modules.sys.action;

import com.platform.framework.common.BaseAction;
import com.platform.framework.util.StringUtils;
import com.platform.modules.sys.bean.Param;
import com.platform.modules.sys.bean.SysConfig;
import com.platform.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统配置Controller
 *
 * @author liugf
 * @version 2016-02-07
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/config")
public class SysConfigAction extends BaseAction<SysConfig> {

    @Autowired
    private SysConfigService configService;

    @ModelAttribute
    public SysConfig get(@RequestParam(required = false) String id) throws Exception {
        if (StringUtils.isNotBlank(id)) {
            return configService.get(id);
        } else {
            return new SysConfig();
        }
    }

    /**
     * 系统配置列表页面
     */
    @RequiresPermissions("sys:config:view")
    @RequestMapping(value = {"index", ""})
    public String list(Model model, SysConfig object, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SysConfig sysConfig = configService.get("1");
        model.addAttribute("sysConfig", sysConfig);
        return "modules/sys/sysConfig";
    }

    @Override
    @RequiresPermissions("sys:config:view")
    public String form(Model model, SysConfig object) throws Exception {
        return null;
    }

    /**
     * 保存系统配置
     */
    @RequiresPermissions("sys:config:edit")
    @RequestMapping(value = "save")
    public String save(Model model, SysConfig object, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, object)) {
            return form(model, object);
        }
        object.setId("1");
        configService.save(object);
        addMessage(redirectAttributes, "保存系统配置成功");
        return "redirect:" + adminPath + "/sys/config?repage";
    }

    /**
     * 删除系统配置
     */
    @RequiresPermissions("sys:config:edit")
    @RequestMapping(value = "delete")
    public String delete(Model model, SysConfig object, Param param, RedirectAttributes redirectAttributes) throws Exception {
        configService.delete(param.getIds());
        addMessage(redirectAttributes, "删除系统配置成功");
        return "redirect:" + adminPath + "/sys/config?repage";
    }


}