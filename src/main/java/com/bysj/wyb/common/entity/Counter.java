package com.bysj.wyb.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangyb
 */
@Data
@Entity
@Table(name = "pd_log")
public class Counter {
    @Id
    String uid;

    @Column(name = "logtime")
    String logTime;
}
