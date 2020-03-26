package com.bysj.wyb.common.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.bysj.wyb.common.entity.Counter;
import com.bysj.wyb.common.mapper.SysMapper;
import com.bysj.wyb.common.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SysService {

    @Resource
    SysMapper sysMapper;

    public void logCount(String uid){
        Counter counter=new Counter();
        counter.setUid(uid);
        String logTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        counter.setLogTime(logTime);
        sysMapper.logCount(counter);
    }

    public Result uplodToOSS(MultipartFile multipartFile){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-chengdu.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FqEfb86cMQiGtcEtxPn";
        String accessKeySecret = "RxEK1zRbOaCMWc78NNNpK82LUqom89";
        String bucketName = "wyb-bysj";

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
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "image/456.jpg", file);
            ossClient.putObject(putObjectRequest);
            Date expiration = new Date(new Date().getTime() + 3600 * 1000*24);
            URL url=ossClient.generatePresignedUrl(bucketName,"image/456.jpg",expiration);
            System.out.println(url.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 关闭OSSClient。
        ossClient.shutdown();
        return null;
    }
}
