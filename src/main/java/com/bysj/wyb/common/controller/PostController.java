package com.bysj.wyb.common.controller;

import com.bysj.wyb.common.entity.Post;
import com.bysj.wyb.common.entity.Reply;
import com.bysj.wyb.common.result.HandleResult;
import com.bysj.wyb.common.result.Result;
import com.bysj.wyb.common.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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

    @GetMapping(value = "/updateForumName")
    public Result UpdateForumName(String uid,String forumName,String table){
        HandleResult hr=new HandleResult();
        if(uid!=null&&uid!=""&&forumName!=null&&forumName!=""){
            return postService.updateForumName(forumName,uid,table);
        }else{
            return hr.outResultWithoutData("1","未知异常");
        }
    }

    @RequestMapping(value = "/findReply")
    public Result findReply(@RequestBody Map<String,String> resBody){
        return postService.findPostReply(resBody.get("postId"));
    }

    @RequestMapping(value = "/addReply")
    public Result addReply(@RequestBody Reply reply){
        return postService.addPostReply(reply);
    }
}
