/*
 * Copyright &copy; <a href="http://www.lufengc.cc">lufengc</a> All rights reserved.
 */

package com.platform.modules.sys.action;

import com.platform.framework.common.SysConfigManager;
import com.platform.framework.util.FileUtils;
import com.platform.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传action
 *
 * @author lufengc
 * @date 2016-01-15 09:56:22
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class UploadAction {

    private static final String ALLOW_TYPE_IMG = "jpg|gif|jpeg|png|bmp";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 上传图片
     *
     * @param upload 上传文件
     * @return json
     */
    @ResponseBody
    @RequestMapping("/uploadImage")
    public String uploadImage(@RequestParam(value = "upload", required = false) MultipartFile upload) {
        String fileName = "";
        try {
            if (upload != null) {
                String uploadFileName = upload.getOriginalFilename();
                String filetype = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                // 文件类型限制
                boolean allowed = ALLOW_TYPE_IMG.contains(filetype);
                if (allowed) {
                    String path = SysConfigManager.getFileUploadPath();
                    String realPath = "userid_" + UserUtils.getPrincipal() + "/images/"
                            + simpleDateFormat.format(new Date()) + "/";
                    fileName = realPath + UUID.randomUUID().toString().replace("-", "") + "." + filetype;
                    FileUtils.createDirectory(path + realPath);
                    upload.transferTo(new File(path + fileName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 编辑器上传图片
     *
     * @param file 上传文件
     */
    @RequestMapping("/editorUpload")
    public void editorUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (!file.isEmpty()) {
            String uploadFileName = file.getOriginalFilename();
            String filetype = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);

            // 文件类型限制
            boolean allowed = ALLOW_TYPE_IMG.contains(filetype);
            if (!allowed) {
                response.getWriter().write("error|不支持的类型");
                return;
            }
            // 图片大小限制
            if (file.getSize() > 5 * 1024 * 1024) {
                response.getWriter().write("error|图片大小不能超过5M");
                return;
            }
            String path = SysConfigManager.getFileUploadPath();
            String realPath = "/editor/images/" + simpleDateFormat.format(new Date()) + "/";
            String fileName = UUID.randomUUID().toString().replace("-", "") + "." + filetype;
            FileUtils.createDirectory(path + realPath);
            file.transferTo(new File(path + realPath + fileName));
            // 返回图片的URL地址
            response.getWriter().write(request.getContextPath() + SysConfigManager.getFileAccessPath()
                    + realPath + fileName);
        }
    }

}