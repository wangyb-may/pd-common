package com.bysj.wyb.common.service;

import com.bysj.wyb.common.entity.Post;
import com.bysj.wyb.common.mapper.PostMapper;
import com.bysj.wyb.common.result.HandleResult;
import com.bysj.wyb.common.result.Result;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableServer.POA;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author wangyb
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService{

    @Resource
    PostMapper postMapper;

    @Resource
    RedisTemplate redisTemplate;

    String rediskey="posts";


    @Override
    public Result findPostList() {
        HandleResult hr=new HandleResult();

        if(redisTemplate.hasKey(rediskey)){
            log.info("缓存中存在帖子集合");
            List<Post> posts=redisTemplate.opsForList().range(rediskey,0,-1);
            return hr.outResultWithData("0","success",posts);
        }else{
            List<Post> posts=postMapper.findList();
            for(Post temp : posts){
                String name=postMapper.findForumName(temp.getCreateUser(),"pd_student");
                if(null!=name){
                    temp.setCreateUser(name);
                }
                redisTemplate.opsForList().leftPush(rediskey,temp);
            }
            return hr.outResultWithData("0","success",posts);
        }

    }

    //新发帖
    @Override
    public Result addPost(Post post) {
        HandleResult hr=new HandleResult();
        try {
            post.setPid(UUID.randomUUID().toString());
            post.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(1==postMapper.addPost(post)){
                //更新redis
                if(redisTemplate.hasKey(rediskey)){
                    redisTemplate.opsForList().leftPush(rediskey,postMapper.findPostById(post.getPid()));
                }
                return hr.outResultWithoutData("0","success");
            }else{
                return hr.outResultWithoutData("1","未知异常");
            }
        }catch (Exception e){
            return hr.outResultWithoutData("500",e.getMessage());
        }

    }

    /**
     * 查询帖子
     * @param pid
     * @return
     */
    @Override
    public Result findPostById(String pid) {
        HandleResult hr=new HandleResult();
        try {
            return hr.outResultWithData("0","查询成功",postMapper.findPostById(pid));
        }catch (Exception e){
            return hr.outResultWithoutData("500",e.getMessage());
        }

    }
}
