package com.bysj.wyb.common.mapper;

import com.bysj.wyb.common.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangyb
 */
@Mapper
public interface PostMapper {

    public List<Post> findList();

    public int addPost(Post post);

    public Post findPostById(String pid);

    /**
     * dsad
     * @param uid
     * @param tableName
     * @return
     */
    String findForumName(String uid,String tableName);

    List<Post> findUserPostListByUserId(String createUser);

    int updateForumName(String name,String uid,String table);
}
