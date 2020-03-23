package com.bysj.wyb.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pd_post")
@Data
public class Post implements Serializable {

    String pid;

    String title;

    @Column(name = "posttype")
    int postType;

    String content;

    @Column(name = "createuser")
    String createUser;

    @Column(name = "createtime")
    String createTime;

    @Column(name = "replycount")
    int replyCount;

    @Column(name = "clickcount")
    int clickCount;

    @Column(name = "isdelete")
    int isDelete;

    @Column(name = "deltime")
    String delTime;

    @Column(name = "deluser")
    String delUser;
}
