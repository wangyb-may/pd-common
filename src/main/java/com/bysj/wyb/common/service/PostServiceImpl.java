package com.bysj.wyb.common.service;

import com.bysj.wyb.common.entity.Post;
import com.bysj.wyb.common.entity.Reply;
import com.bysj.wyb.common.mapper.PostMapper;
import com.bysj.wyb.common.result.HandleResult;
import com.bysj.wyb.common.result.IdWorker;
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
public class PostServiceImpl implements PostService {

    @Resource
    PostMapper postMapper;


    String rediskey = "posts";


    @Override
    public Result findPostList() {
        HandleResult hr = new HandleResult();


        List<Post> posts = postMapper.findList();
        for (Post temp : posts) {
            String name = postMapper.findForumName(temp.getCreateUser(), "pd_student");
            if (null != name) {
                temp.setCreateUser(name);
            }
        }
        return hr.outResultWithData("0", "success", posts);


    }

    //新发帖
    @Override
    public Result addPost(Post post) {
        HandleResult hr = new HandleResult();
        try {
            post.setPid(UUID.randomUUID().toString());
            post.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if (1 == postMapper.addPost(post)) {

                return hr.outResultWithoutData("0", "发帖成功！");
            } else {
                return hr.outResultWithoutData("1", "未知异常");
            }
        } catch (Exception e) {
            return hr.outResultWithoutData("500", e.getMessage());
        }

    }

    /**
     * 根据帖子id查询帖子详情
     *
     * @param pid
     * @return
     */
    @Override
    public Result findPostById(String pid) {
        HandleResult hr = new HandleResult();
        try {
            return hr.outResultWithData("0", "查询成功", postMapper.findPostById(pid));
        } catch (Exception e) {
            return hr.outResultWithoutData("500", e.getMessage());
        }

    }

    @Override
    public Result findUserPostListById(String createUser) {
        HandleResult hr = new HandleResult();
        try {
            return hr.outResultWithData("0", "查询成功", postMapper.findUserPostListByUserId(createUser));
        } catch (Exception e) {
            return hr.outResultWithoutData("500", e.getMessage());
        }
    }

    @Override
    public Result updateForumName(String name, String uid, String table) {
        HandleResult hr = new HandleResult();
        try {
            if (1 == postMapper.updateForumName(name, uid, table)) {
                return hr.outResultWithData("0", "设置成功", name);
            } else {
                return hr.outResultWithoutData("1", "设置失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return hr.outResultWithoutData("1", "服务异常");
        }
    }

    @Override
    public Result findPostReply(String postId) {
        HandleResult hr = new HandleResult();
        try {
            return hr.outResultWithData("0", "成功", postMapper.findPostReply(postId));

        } catch (Exception e) {
            log.error(e.getMessage());
            return hr.outResultWithoutData("0", "服务异常");
        }
    }

    @Override
    public Result addPostReply(Reply re) {
        HandleResult hr = new HandleResult();
        try {
            re.setReplyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            IdWorker snow = new IdWorker(1, 1, 1);
            re.setId(String.valueOf(snow.nextId()));
            postMapper.addReply(re);
            return hr.outResultWithoutData("0", "回复成功！");
        } catch (Exception e) {

            log.error(e.getMessage());
            return hr.outResultWithoutData("1", "错误");
        }

    }
}
