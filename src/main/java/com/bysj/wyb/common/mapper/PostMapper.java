package com.bysj.wyb.common.mapper;

import com.bysj.wyb.common.entity.Post;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
