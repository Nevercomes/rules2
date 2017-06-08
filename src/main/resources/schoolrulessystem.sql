/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : schoolrulessystem

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-06-08 10:16:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `account` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `username` varchar(20) NOT NULL COMMENT '姓名',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `uc_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for `clockin`
-- ----------------------------
DROP TABLE IF EXISTS `clockin`;
CREATE TABLE `clockin` (
  `clock_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '打卡编号',
  `student_id` bigint(20) DEFAULT NULL COMMENT '学号',
  `clock_day` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '打卡时间日期',
  `duration` int(11) DEFAULT '0' COMMENT '学习时长',
  `title_num` int(11) DEFAULT '0' COMMENT '题目数',
  `comment` varchar(255) DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`clock_id`),
  KEY `clock_fk_1` (`student_id`),
  CONSTRAINT `clock_fk_1` FOREIGN KEY (`student_id`) REFERENCES `userinfo` (`student_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clockin
-- ----------------------------

-- ----------------------------
-- Table structure for `contestregistion`
-- ----------------------------
DROP TABLE IF EXISTS `contestregistion`;
CREATE TABLE `contestregistion` (
  `student_id` bigint(20) NOT NULL COMMENT '学号',
  `test_id` int(11) NOT NULL COMMENT '测试编号',
  `status` int(11) NOT NULL COMMENT '竞赛状态',
  PRIMARY KEY (`student_id`,`test_id`),
  KEY `conreg_fk_2` (`test_id`),
  CONSTRAINT `conreg_fk_1` FOREIGN KEY (`student_id`) REFERENCES `userinfo` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `conreg_fk_2` FOREIGN KEY (`test_id`) REFERENCES `testinfo` (`test_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contestregistion
-- ----------------------------

-- ----------------------------
-- Table structure for `integral`
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral` (
  `student_id` bigint(20) NOT NULL COMMENT '学号',
  `integral_num` int(11) DEFAULT '0' COMMENT '总积分数目',
  KEY `integral_fk_1` (`student_id`),
  CONSTRAINT `integral_fk_1` FOREIGN KEY (`student_id`) REFERENCES `userinfo` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of integral
-- ----------------------------

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知编号',
  `title` varchar(50) NOT NULL COMMENT '通知标题',
  `content` text NOT NULL COMMENT '通知文本内容',
  `picture` varchar(50) DEFAULT NULL COMMENT '图片描述url',
  `submit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  `source` int(11) DEFAULT NULL COMMENT '作者，来源',
  `type` int(11) NOT NULL COMMENT '通知类型',
  PRIMARY KEY (`notice_id`),
  KEY `notice_fk_1` (`source`),
  CONSTRAINT `notice_fk_1` FOREIGN KEY (`source`) REFERENCES `admin` (`admin_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for `option`
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选项编号',
  `title_id` int(11) NOT NULL COMMENT '题目编号',
  `content` varchar(300) NOT NULL COMMENT '选项内容',
  `checked` tinyint(1) NOT NULL COMMENT '是否选择',
  PRIMARY KEY (`option_id`),
  KEY `option_fk_1` (`title_id`),
  CONSTRAINT `option_fk_1` FOREIGN KEY (`title_id`) REFERENCES `title` (`title_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of option
-- ----------------------------

-- ----------------------------
-- Table structure for `signon`
-- ----------------------------
DROP TABLE IF EXISTS `signon`;
CREATE TABLE `signon` (
  `student_id` bigint(20) NOT NULL COMMENT '学号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  KEY `signon_fk_1` (`student_id`),
  CONSTRAINT `signon_fk_1` FOREIGN KEY (`student_id`) REFERENCES `userinfo` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signon
-- ----------------------------

-- ----------------------------
-- Table structure for `testinfo`
-- ----------------------------
DROP TABLE IF EXISTS `testinfo`;
CREATE TABLE `testinfo` (
  `test_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试/竞赛编号',
  `name` varchar(50) NOT NULL COMMENT '考试名称',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '考试类型',
  `grade` int(11) NOT NULL COMMENT '年级，如2015',
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '考试开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '考试截止时间',
  `duration` int(11) NOT NULL COMMENT '考试时长',
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of testinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `testrecord`
-- ----------------------------
DROP TABLE IF EXISTS `testrecord`;
CREATE TABLE `testrecord` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL,
  `test_id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `submit_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `testrecord_fk_1` (`student_id`),
  KEY `testrecord_fk_2` (`test_id`),
  CONSTRAINT `testrecord_fk_1` FOREIGN KEY (`student_id`) REFERENCES `userinfo` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `testrecord_fk_2` FOREIGN KEY (`test_id`) REFERENCES `testinfo` (`test_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of testrecord
-- ----------------------------

-- ----------------------------
-- Table structure for `title`
-- ----------------------------
DROP TABLE IF EXISTS `title`;
CREATE TABLE `title` (
  `title_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '题目编号',
  `name` varchar(300) NOT NULL COMMENT '题目内容',
  `diff_id` int(11) NOT NULL COMMENT '题目难度',
  `score` int(11) NOT NULL COMMENT '分值',
  PRIMARY KEY (`title_id`),
  KEY `title_fk_1` (`diff_id`),
  CONSTRAINT `title_fk_1` FOREIGN KEY (`diff_id`) REFERENCES `titlediff` (`diff_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of title
-- ----------------------------

-- ----------------------------
-- Table structure for `titlediff`
-- ----------------------------
DROP TABLE IF EXISTS `titlediff`;
CREATE TABLE `titlediff` (
  `diff_id` int(11) NOT NULL COMMENT '难度编号',
  `name` varchar(20) NOT NULL COMMENT '难度分类名称',
  PRIMARY KEY (`diff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of titlediff
-- ----------------------------

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `student_id` bigint(20) NOT NULL COMMENT '学号',
  `username` varchar(20) NOT NULL COMMENT '姓名',
  `sex` tinyint(1) NOT NULL COMMENT '性别',
  `class` varchar(20) NOT NULL COMMENT '专业班级，如工程试验班1503',
  `grade` int(11) NOT NULL COMMENT '年级，如2015',
  `college` varchar(20) NOT NULL COMMENT '学院名称，如软件学院',
  `school` varchar(20) NOT NULL DEFAULT '中南大学' COMMENT '学校名称，如中南大学',
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `wrongtitle`
-- ----------------------------
DROP TABLE IF EXISTS `wrongtitle`;
CREATE TABLE `wrongtitle` (
  `title_id` int(11) DEFAULT NULL COMMENT '题目编号',
  `student_id` bigint(20) NOT NULL COMMENT '学号',
  KEY `wrongtitle_fk_1` (`title_id`),
  KEY `wrongtitle_fk_2` (`student_id`),
  CONSTRAINT `wrongtitle_fk_1` FOREIGN KEY (`title_id`) REFERENCES `title` (`title_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `wrongtitle_fk_2` FOREIGN KEY (`student_id`) REFERENCES `userinfo` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wrongtitle
-- ----------------------------
