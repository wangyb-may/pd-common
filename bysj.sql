/*
Navicat MySQL Data Transfer

Source Server         : 本地sql
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : bysj

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-07-17 17:24:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pd_admin
-- ----------------------------
DROP TABLE IF EXISTS `pd_admin`;
CREATE TABLE `pd_admin` (
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `isdelete` int(255) DEFAULT NULL COMMENT '0启用1删除',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_attachment
-- ----------------------------
DROP TABLE IF EXISTS `pd_attachment`;
CREATE TABLE `pd_attachment` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '附件名称',
  `uptime` datetime DEFAULT NULL COMMENT '上传时间',
  `upurl` varchar(255) NOT NULL COMMENT '上传路径',
  `uid` varchar(255) DEFAULT NULL COMMENT '上传人id',
  `courseid` varchar(255) DEFAULT NULL COMMENT '附件所在课程id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_course
-- ----------------------------
DROP TABLE IF EXISTS `pd_course`;
CREATE TABLE `pd_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名字',
  `coursecontext` text,
  `teacher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任课教师',
  `classnumber` varchar(255) DEFAULT NULL COMMENT '课程所在班级',
  `createtime` datetime DEFAULT NULL,
  `isdelete` int(11) DEFAULT '0' COMMENT '是否删除0否1是',
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_course_choose
-- ----------------------------
DROP TABLE IF EXISTS `pd_course_choose`;
CREATE TABLE `pd_course_choose` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `courseid` varchar(255) NOT NULL,
  `choosetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_homework
-- ----------------------------
DROP TABLE IF EXISTS `pd_homework`;
CREATE TABLE `pd_homework` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `name` varchar(255) NOT NULL COMMENT '课程名',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `classnumber` varchar(255) NOT NULL COMMENT '作业所在班级',
  `courseid` varchar(255) NOT NULL COMMENT '作业所在课程',
  `teacherid` varchar(255) NOT NULL COMMENT '发布老师',
  `context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '作业内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_homework_status
-- ----------------------------
DROP TABLE IF EXISTS `pd_homework_status`;
CREATE TABLE `pd_homework_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `homeworkid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作业号',
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生id',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '1为已提交，0为未提交',
  `uptime` datetime DEFAULT NULL COMMENT '提交时间',
  `score` int(11) DEFAULT '0' COMMENT '分数',
  `upurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1286 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_log
-- ----------------------------
DROP TABLE IF EXISTS `pd_log`;
CREATE TABLE `pd_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL COMMENT '登录用户id',
  `logtime` datetime DEFAULT NULL COMMENT '登陆时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=364 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_post
-- ----------------------------
DROP TABLE IF EXISTS `pd_post`;
CREATE TABLE `pd_post` (
  `pid` varchar(255) NOT NULL COMMENT '帖子id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '帖子标题',
  `posttype` int(11) NOT NULL COMMENT '帖子分类：0学术，1灌水',
  `abstract` text COMMENT '摘要',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帖子内容',
  `createuser` varchar(255) NOT NULL COMMENT '发帖人id',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `replycount` int(11) NOT NULL DEFAULT '0' COMMENT '回复数',
  `clickcount` int(11) NOT NULL DEFAULT '0' COMMENT '点击数',
  `isdelete` int(11) DEFAULT '0' COMMENT '是否被删除：0否，1是',
  `deltime` datetime DEFAULT NULL COMMENT '删除时间',
  `deluser` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_reply
-- ----------------------------
DROP TABLE IF EXISTS `pd_reply`;
CREATE TABLE `pd_reply` (
  `postid` varchar(255) NOT NULL COMMENT '回复所在的帖子id',
  `id` varchar(255) NOT NULL COMMENT '回复的id',
  `parentid` varchar(255) NOT NULL COMMENT '回复的父级id',
  `content` text COMMENT '回复内容',
  `respondentname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回复人昵称',
  `replytime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_student
-- ----------------------------
DROP TABLE IF EXISTS `pd_student`;
CREATE TABLE `pd_student` (
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `nickname` varchar(255) NOT NULL COMMENT '登录名（学号）',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `classnumber` varchar(255) NOT NULL COMMENT '所在班级号',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `mobile` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `imageurl` varchar(255) DEFAULT NULL,
  `signforum` int(11) NOT NULL DEFAULT '0' COMMENT '是否注册过论坛，0否1是',
  `forumname` varchar(255) DEFAULT NULL,
  `isdelete` int(11) DEFAULT '0' COMMENT '0启用1删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for pd_teacher
-- ----------------------------
DROP TABLE IF EXISTS `pd_teacher`;
CREATE TABLE `pd_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师职工号、id号',
  `name` varchar(255) DEFAULT NULL COMMENT '教师姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `forumname` varchar(255) DEFAULT NULL COMMENT '论坛昵称',
  `isdelete` int(11) DEFAULT '0' COMMENT '0启用1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
