package com.bysj.wyb.common.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.bysj.wyb.common.entity.Counter;
import com.bysj.wyb.common.mapper.SysMapper;
import com.bysj.wyb.common.result.HandleResult;
import com.bysj.wyb.common.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SysService {

    @Resource
    SysMapper sysMapper;

    public void logCount(String uid) {
        Counter counter = new Counter();
        counter.setUid(uid);
        String logTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        counter.setLogTime(logTime);
        sysMapper.logCount(counter);
    }

    public Result uplodToOSS(MultipartFile multipartFile, String uploadCatalogAndName) {
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(uploadCatalogAndName);
        HandleResult hr = new HandleResult();
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-chengdu.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "your accessKeyId";
        String accessKeySecret = "your accessKeySecret";
        String bucketName = "wyb-bysj";
        URL url = null;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若需要防止生成的临时文件重复,可以在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multipartFile.transferTo(file);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uploadCatalogAndName, file);
            ossClient.putObject(putObjectRequest);
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            url = ossClient.generatePresignedUrl(bucketName, uploadCatalogAndName, expiration);
            System.out.println(url.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (url != null) {
                ossClient.shutdown();
                return hr.outResultWithData("0", "上传成功", url.toString());
            } else {
                ossClient.shutdown();
                return hr.outResultWithoutData("1", "上传失败");
            }
        }
    }
}
