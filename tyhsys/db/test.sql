/*
Navicat MySQL Data Transfer

Source Server         : loc
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2013-02-25 14:39:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `person_info`
-- ----------------------------
DROP TABLE IF EXISTS `person_info`;
CREATE TABLE `person_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PI_CODE` varchar(20) DEFAULT NULL,
  `PI_NAME` varchar(30) DEFAULT NULL,
  `PI_PHONE` varchar(30) DEFAULT NULL,
  `PI_MOBILE` varchar(11) DEFAULT NULL,
  `PI_QQ` varchar(20) DEFAULT NULL,
  `PI_EMAIL` varchar(30) DEFAULT NULL,
  `PI_IDCARD` varchar(20) DEFAULT NULL,
  `PI_SEX` varchar(1) DEFAULT NULL COMMENT 'M表示男性；F表示女性',
  `PI_JOIN_DATE` datetime DEFAULT NULL,
  `PI_BIRTH_DATE` datetime DEFAULT NULL,
  `PI_DEGREE` varchar(30) DEFAULT NULL,
  `PI_TYPE` bigint(20) DEFAULT NULL,
  `PI_NATION` varchar(100) DEFAULT NULL,
  `PI_ADDRESS` varchar(100) DEFAULT NULL,
  `PI_MEMO` longtext,
  `PI_SUP` varchar(36) DEFAULT NULL,
  `PI_ORG` bigint(20) DEFAULT NULL,
  `PI_USER` varchar(30) DEFAULT NULL,
  `CRT_C` varchar(30) DEFAULT NULL,
  `CRT_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PI_CODE` (`PI_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person_info
-- ----------------------------

-- ----------------------------
-- Table structure for `person_type`
-- ----------------------------
DROP TABLE IF EXISTS `person_type`;
CREATE TABLE `person_type` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PT_CODE` varchar(10) DEFAULT NULL,
  `PT_NAME` varchar(30) DEFAULT NULL,
  `CRT_C` varchar(30) DEFAULT NULL,
  `CRT_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person_type
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SM_NAME` varchar(30) DEFAULT NULL,
  `SM_CODE` varchar(30) DEFAULT NULL,
  `SM_TITLE` varchar(100) DEFAULT NULL,
  `SM_PARENT` varchar(20) DEFAULT NULL,
  `SM_PAGE` varchar(100) DEFAULT NULL,
  `SM_CONTAIN_PAGE` longtext,
  `SM_IS_USED` varchar(1) DEFAULT NULL,
  `SM_IS_TOP` varchar(1) DEFAULT NULL,
  `SM_DESCRIPTION` longtext,
  `CRT_C` varchar(30) DEFAULT NULL,
  `CRT_DATE` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '10', null, '', '', null, 'Y', 'Y', '系统管理子模块', 'jerry', '2013-02-25 14:37:28');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '1001', '用户管理', '10', 'user/list', null, 'Y', null, null, 'sys', '2013-02-20 14:15:43');
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', '1002', null, '10', 'menu/list', null, 'Y', null, '', 'jerry', '2013-02-21 11:31:15');
INSERT INTO `sys_menu` VALUES ('4', '权限管理', '1003', null, '10', 'auth/list', null, 'Y', null, '', 'jerry', '2013-02-21 11:34:45');
INSERT INTO `sys_menu` VALUES ('5', '测试', 'test', null, '10', '', null, 'Y', null, '', 'jerry', '2013-02-22 10:49:28');
INSERT INTO `sys_menu` VALUES ('7', 'ce111', 'ce111', null, '', '', null, 'Y', null, '', 'jerry', '2013-02-22 13:17:07');
INSERT INTO `sys_menu` VALUES ('8', 'ce222', 'ce222', null, '', '', null, 'Y', null, '', 'jerry', '2013-02-22 13:17:37');
INSERT INTO `sys_menu` VALUES ('9', 'ce22', 'ce22', null, '', '', null, 'Y', null, '', 'jerry', '2013-02-22 13:20:09');
INSERT INTO `sys_menu` VALUES ('10', 'ce88', 'ce88', null, '', '', null, 'Y', null, '', 'jerry', '2013-02-22 13:43:08');
INSERT INTO `sys_menu` VALUES ('11', 'ce99', 'ce99', null, '', '', null, 'Y', null, '', 'jerry', '2013-02-22 13:55:23');

-- ----------------------------
-- Table structure for `sys_menu_oper`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_oper`;
CREATE TABLE `sys_menu_oper` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SMO_NAME` varchar(30) DEFAULT NULL,
  `SMO_OPERATION` varchar(10) DEFAULT NULL,
  `SMO_MENU_ID` bigint(20) DEFAULT NULL,
  `SMO_VALID` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKF6CDAA3CEBF0C694` (`SMO_MENU_ID`),
  CONSTRAINT `FKF6CDAA3CEBF0C694` FOREIGN KEY (`SMO_MENU_ID`) REFERENCES `sys_menu` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_oper
-- ----------------------------
INSERT INTO `sys_menu_oper` VALUES ('3', '新增', 'add', '11', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('4', '编辑', 'edit', '11', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('5', '编辑', 'edit1', '10', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('6', '新增', 'add1', '10', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('7', '新增', 'add2', '11', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('8', '编辑', 'edit2', '11', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('9', '新增', 'add', '11', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('10', '编辑', 'edit', '11', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('77', '新增', 'add', '1', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('78', '编辑', 'edit', '1', 'Y');

-- ----------------------------
-- Table structure for `sys_oper_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SOL_MENU_ID` bigint(20) DEFAULT NULL,
  `SOL_OPER_TYPE` varchar(1) DEFAULT NULL,
  `SOL_OPER_USER` bigint(20) DEFAULT NULL,
  `SOL_OPER_TIME` datetime DEFAULT NULL,
  `SOL_OPER_DETAIL` longtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_org`
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SO_CODE` varchar(30) DEFAULT NULL,
  `SO_NAME` varchar(30) DEFAULT NULL,
  `SO_PARENT` bigint(20) DEFAULT NULL,
  `CRT_C` varchar(30) DEFAULT NULL,
  `CRT_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SO_CODE` (`SO_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SR_CODE` varchar(30) DEFAULT NULL,
  `SR_NAME` varchar(100) DEFAULT NULL,
  `SR_MEMO` longtext,
  `CRT_C` varchar(30) DEFAULT NULL,
  `CRT_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SR_CODE` (`SR_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SU_USERNAME` varchar(30) DEFAULT NULL,
  `SU_PASSWORD` varchar(30) DEFAULT NULL,
  `SU_NAME_CN` varchar(20) DEFAULT NULL,
  `SU_ACC_ENA` varchar(1) DEFAULT NULL COMMENT 'N表示不可用；Y表示可用。',
  `SU_MEMO` varchar(150) DEFAULT NULL,
  `CRT_C` varchar(30) DEFAULT NULL,
  `CRT_DATE` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SU_USERNAME` (`SU_USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'jerry', '123456', 'jerry', 'Y', null, 'sys', '2013-02-05 14:28:23');
INSERT INTO `sys_user` VALUES ('3', 'admin', '123456', 'admin', 'Y', null, 'sys', '2013-02-18 13:59:12');
INSERT INTO `sys_user` VALUES ('4', 'tyh', '123456', '唐有欢', 'Y', null, 'sys', '2013-02-18 13:59:53');
INSERT INTO `sys_user` VALUES ('5', 'test1', '123456', '测试员1', 'Y', null, 'sys', '2013-02-18 14:00:45');
INSERT INTO `sys_user` VALUES ('6', 'test2', '123456', '测试员2', 'Y', null, 'jerry', '2013-02-19 10:07:03');
INSERT INTO `sys_user` VALUES ('7', 'zhangsan', '123456', '张三丰', 'Y', null, 'jerry', '2013-02-19 14:33:38');
INSERT INTO `sys_user` VALUES ('8', 'lihao', '123456', '李好1', 'Y', null, 'jerry', '2013-02-19 14:59:10');
INSERT INTO `sys_user` VALUES ('9', 'mingbai', '123456', '明百明', 'Y', null, 'jerry', '2013-02-19 14:59:41');
INSERT INTO `sys_user` VALUES ('10', 'leidongyi', '123456', '雷东宜', 'Y', null, 'jerry', '2013-02-25 13:29:08');
INSERT INTO `sys_user` VALUES ('12', 'lihao1', '123456', '李好2', 'Y', null, 'jerry', '2013-02-25 14:36:22');
