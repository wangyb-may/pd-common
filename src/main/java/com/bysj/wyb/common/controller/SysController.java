package com.bysj.wyb.common.controller;


import com.bysj.wyb.common.annotation.UserLog;
import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.common.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin
@RequestMapping("/system")
public class SysController {

    @Autowired
    SysService sysService;

    /**
     * 登录信息登记
     * @param uid
     * @param request
     */
    @UserLog
    @RequestMapping(value = "/logCounter")
    public void logCounter(@RequestParam String uid, HttpServletRequest request){
        sysService.logCount(uid);
    }


    /**
     * 上传文件接口
     * @param file
     * @param uploadCatalogAndName
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result uploadToOss(@RequestPart("file") MultipartFile file, @RequestParam String uploadCatalogAndName){
        return sysService.uplodToOSS(file,uploadCatalogAndName);
    }
}
