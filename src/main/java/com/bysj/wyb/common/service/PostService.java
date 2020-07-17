package com.bysj.wyb.common.service;

import com.bysj.wyb.common.entity.Post;
import com.bysj.wyb.common.entity.Reply;
import com.bysj.wyb.common.result.Result;

/**
 * @author wangyb
 */
public interface PostService {

    public Result findPostList();

    /**
     * 发帖
     * @param posts
     * @return
     */
    public Result addPost(Post posts);

    /**
     * 帖子详细信息
     * @param pid
     * @return
     */
    public Result findPostById(String pid);

    public Result findUserPostListById(String createUser);

    /**
     * 修改论坛昵称
     * @param name
     * @param uid
     * @return
     */
    Result updateForumName(String name,String uid,String table);

    /**
     * 查询回复
     * @param postId
     * @return
     */
    Result findPostReply(String postId);

    /**
     * 添加回复
     * @param re
     * @return
     */
    Result addPostReply(Reply re);
}
