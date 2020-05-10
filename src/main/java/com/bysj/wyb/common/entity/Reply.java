package com.bysj.wyb.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "pd_reply")
public class Reply {
    String id;

    @Column(name = "postid")
    String postId;

    @Column(name = "parentid")
    String parentId;

    String content;

    @Column(name = "respondentname")
    String respondentName;

    @Column(name = "replytime")
    String replyTime;
}
