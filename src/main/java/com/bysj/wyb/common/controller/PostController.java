package com.bysj.wyb.common.controller;

import com.bysj.wyb.common.entity.Post;
import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.common.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wangyb
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/post")
public class PostController {

    @Resource
    PostService postService;

    /**
     * 发帖
     * @param post
     * @return
     */
    @RequestMapping(value = "/add")
    public Result addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    /**
     * 查询帖子列表（未删除的帖子）
     * @return
     */
    @RequestMapping(value = "/list")
    public Result findList(){
        return postService.findPostList();
    }


    @RequestMapping(value = "/userPostList")
    public Result findUserPostList(String userId){
        return postService.findUserPostListById(userId);
    }
}
