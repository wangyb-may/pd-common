package com.bysj.wyb.common.service;

import com.bysj.wyb.common.entity.Post;
import com.bysj.wyb.common.result.Result;

/**
 * @author wangyb
 */
public interface PostService {

    public Result findPostList();

    public Result addPost(Post posts);

    public Result findPostById(String pid);

    public Result findUserPostListById(String createUser);

    /**
     * 修改论坛昵称
     * @param name
     * @param uid
     * @return
     */
    Result updateForumName(String name,String uid,String table);
}
