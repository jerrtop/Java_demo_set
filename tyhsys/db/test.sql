/*
Navicat MySQL Data Transfer

Source Server         : loc
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2013-03-22 14:25:35
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
  `PI_JOIN_DATE` varchar(10) DEFAULT NULL,
  `PI_BIRTH_DATE` varchar(10) DEFAULT NULL,
  `PI_DEGREE` varchar(30) DEFAULT NULL,
  `PI_TYPE` varchar(30) DEFAULT '',
  `PI_NATION` varchar(100) DEFAULT NULL,
  `PI_ADDRESS` varchar(100) DEFAULT NULL,
  `PI_SUP` varchar(36) DEFAULT NULL,
  `PI_ORG` bigint(20) DEFAULT NULL,
  `CRT_C` varchar(30) DEFAULT NULL,
  `CRT_DATE` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person_info
-- ----------------------------
INSERT INTO `person_info` VALUES ('1', 'admin', 'admin', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('2', 'jerry', 'jerry', null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null);
INSERT INTO `person_info` VALUES ('3', 'jerry1', 'jerry1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('4', 'leidongyi', 'leidongyi', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('5', 'lihao', 'lihao', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('6', 'lihao1', 'lihao1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('7', 'md11', 'md11', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('8', 'md5', 'md5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('9', 'mingbai', 'mingbai', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('10', 'test1', 'test1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('11', 'test2', 'test2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('12', 'tyh', 'tyh', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('13', 'zhangsan', 'zhangsan', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `person_info` VALUES ('18', '', '测试1', '', '', '', '', '', null, '', '', '', null, '', '', '', '1', null, null);
INSERT INTO `person_info` VALUES ('19', '1111', '1111', '', '', '', '', '', null, '', '', '', null, '', '', '', '11', 'jerry', '2013-03-21 15:38:21');
INSERT INTO `person_info` VALUES ('21', '', '测试3', '', '', '', '', '', null, '2013-03-21', '2013-03-20', '', null, '', '', '18', '5', 'jerry', '2013-03-22 11:00:31');
INSERT INTO `person_info` VALUES ('22', '', '测试3', '', '', '1111', '1111@qq.com', '', null, '2013-03-22', '2013-03-22', '', null, '', '', '', '5', 'jerry', '2013-03-22 11:34:22');
INSERT INTO `person_info` VALUES ('24', '11101', '测试111', '123', '123', '', '123@qq.com', '', 'M', '2013-03-22', '2013-03-21', '', '', '', '', '20', '2', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '10', null, '', '', null, 'Y', 'Y', '系统管理子模块', 'jerry', '2013-02-25 14:37:28');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '100101', '用户管理', '1001', 'user/list', null, 'Y', null, null, 'sys', '2013-02-20 14:15:43');
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', '100102', null, '1001', 'menu/list', null, 'Y', null, '', 'jerry', '2013-02-21 11:31:15');
INSERT INTO `sys_menu` VALUES ('4', '用户角色管理', '100103', null, '1001', 'userrole/list', null, 'Y', null, '', 'jerry', '2013-02-21 11:34:45');
INSERT INTO `sys_menu` VALUES ('12', '角色管理', '100104', null, '1001', 'role/list', null, 'Y', null, '', 'jerry', '2013-02-26 09:39:11');
INSERT INTO `sys_menu` VALUES ('13', '短信平台', '20', null, '', '', null, 'Y', null, '', 'jerry', '2013-02-27 16:21:51');
INSERT INTO `sys_menu` VALUES ('14', '短信模板', '2001', null, '20', '', null, 'Y', null, '', 'jerry', '2013-02-27 16:25:33');
INSERT INTO `sys_menu` VALUES ('16', '系统管理', '1001', null, '10', null, null, 'Y', '', '系统管理', 'jerry', '2013-02-25 14:37:28');
INSERT INTO `sys_menu` VALUES ('17', '模板管理', '200101', null, '2001', 'sms/list', null, 'Y', null, null, 'jerry', '2013-02-27 16:25:33');
INSERT INTO `sys_menu` VALUES ('18', '模板类别', '200102', null, '2001', 'sms/catery', null, 'Y', null, null, 'jerry', '2013-02-27 16:25:33');
INSERT INTO `sys_menu` VALUES ('19', '组织架构', '100105', null, '1001', 'org/list', null, 'Y', null, '', 'jerry', '2013-03-20 09:50:29');

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
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_oper
-- ----------------------------
INSERT INTO `sys_menu_oper` VALUES ('77', '新增', 'add', '1', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('78', '编辑', 'edit', '1', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('90', '删除', 'delete', '12', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('91', '新增', 'add', '12', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('92', '查询', 'search', '12', 'Y');
INSERT INTO `sys_menu_oper` VALUES ('93', '编辑', 'edit', '12', 'Y');

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
  `CRT_DATE` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SO_CODE` (`SO_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('1', '10', '九唐科技', null, 'jerry', '2013-03-20 10:38:00');
INSERT INTO `sys_org` VALUES ('2', '1001', '销售部', '1', 'jerry', '2013-03-20 10:38:00');
INSERT INTO `sys_org` VALUES ('3', '1002', '研发部', '1', 'jerry', '2013-03-20 15:11:28');
INSERT INTO `sys_org` VALUES ('4', '1003', '市场部', '1', 'jerry', '2013-03-20 15:12:10');
INSERT INTO `sys_org` VALUES ('5', '1004', '运营分公司', '1', 'jerry', '2013-03-20 15:32:36');
INSERT INTO `sys_org` VALUES ('7', '1005', '韩国分公司', '1', 'jerry', '2013-03-20 15:40:14');
INSERT INTO `sys_org` VALUES ('8', '1006', '美国分公司', '1', 'jerry', '2013-03-20 15:43:09');
INSERT INTO `sys_org` VALUES ('9', '100601', '美国洛杉矶办事处', '8', 'jerry', '2013-03-20 15:43:50');

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
  `CRT_DATE` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SR_CODE` (`SR_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('17', 'sr110', '系统管理员', null, 'jerry', '2013-03-01 09:59:09');
INSERT INTO `sys_role` VALUES ('18', 'sr111', 'IT支撑室', null, 'jerry', '2013-03-01 09:59:47');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SRM_ROLE_ID` bigint(20) DEFAULT NULL,
  `SRM_MENU_ID` bigint(20) DEFAULT NULL,
  `SRM_OPERS` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('183', '18', '1', 'add,edit');
INSERT INTO `sys_role_menu` VALUES ('184', '18', '16', '');
INSERT INTO `sys_role_menu` VALUES ('185', '18', '2', '');
INSERT INTO `sys_role_menu` VALUES ('186', '18', '3', '');
INSERT INTO `sys_role_menu` VALUES ('187', '18', '12', '');
INSERT INTO `sys_role_menu` VALUES ('188', '18', '13', '');
INSERT INTO `sys_role_menu` VALUES ('189', '18', '14', '');
INSERT INTO `sys_role_menu` VALUES ('190', '17', '1', 'add,edit');
INSERT INTO `sys_role_menu` VALUES ('191', '17', '16', '');
INSERT INTO `sys_role_menu` VALUES ('192', '17', '2', '');
INSERT INTO `sys_role_menu` VALUES ('193', '17', '3', '');
INSERT INTO `sys_role_menu` VALUES ('194', '17', '4', '');
INSERT INTO `sys_role_menu` VALUES ('195', '17', '12', '');
INSERT INTO `sys_role_menu` VALUES ('196', '17', '19', '');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SU_USERNAME` varchar(30) DEFAULT NULL,
  `SU_PASSWORD` varchar(32) DEFAULT NULL,
  `SU_ACC_ENA` varchar(1) DEFAULT NULL COMMENT 'N表示不可用；Y表示可用。',
  `SU_MEMO` varchar(150) DEFAULT NULL,
  `CRT_C` varchar(30) DEFAULT NULL,
  `CRT_DATE` varchar(19) DEFAULT NULL,
  `SU_PERSON_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SU_USERNAME` (`SU_USERNAME`),
  KEY `FK74A81DFD15D3989C` (`SU_PERSON_ID`),
  CONSTRAINT `FK74A81DFD15D3989C` FOREIGN KEY (`SU_PERSON_ID`) REFERENCES `person_info` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'jerry', '25d55ad283aa400af464c76d713c07ad', 'Y', null, 'sys', '2013-02-05 14:28:23', '2');
INSERT INTO `sys_user` VALUES ('3', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'sys', '2013-02-18 13:59:12', '1');
INSERT INTO `sys_user` VALUES ('4', 'tyh', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'sys', '2013-02-18 13:59:53', '12');
INSERT INTO `sys_user` VALUES ('5', 'test1', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'sys', '2013-02-18 14:00:45', '10');
INSERT INTO `sys_user` VALUES ('6', 'test2', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'jerry', '2013-02-19 10:07:03', '11');
INSERT INTO `sys_user` VALUES ('7', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'jerry', '2013-02-19 14:33:38', '13');
INSERT INTO `sys_user` VALUES ('8', 'lihao', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'jerry', '2013-02-19 14:59:10', '5');
INSERT INTO `sys_user` VALUES ('9', 'mingbai', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'jerry', '2013-02-19 14:59:41', '9');
INSERT INTO `sys_user` VALUES ('10', 'leidongyi', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'jerry', '2013-02-25 13:29:08', '4');
INSERT INTO `sys_user` VALUES ('12', 'lihao1', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'jerry', '2013-02-25 14:36:22', '6');
INSERT INTO `sys_user` VALUES ('13', 'jerry1', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'jerry', '2013-02-27 09:28:32', '3');
INSERT INTO `sys_user` VALUES ('14', 'md5', '25d55ad283aa400af464c76d713c07ad', 'Y', '', 'jerry', '2013-03-05 09:37', '8');
INSERT INTO `sys_user` VALUES ('15', 'md11', 'e10adc3949ba59abbe56e057f20f883e', 'Y', null, 'jerry', '2013-03-05 09:43:49', '7');
INSERT INTO `sys_user` VALUES ('18', 'testp1', '25d55ad283aa400af464c76d713c07ad', 'Y', null, 'jerry', '2013-03-21 15:34:00', '18');
INSERT INTO `sys_user` VALUES ('19', 'testp2', '25d55ad283aa400af464c76d713c07ad', 'Y', null, 'jerry', '2013-03-21 15:38:21', '19');
INSERT INTO `sys_user` VALUES ('21', '123456', '25d55ad283aa400af464c76d713c07ad', 'Y', null, 'jerry', '2013-03-22 14:19:06', '24');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SUR_USER_ID` bigint(20) DEFAULT NULL,
  `SUR_ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '18');
INSERT INTO `sys_user_role` VALUES ('2', '3', '17');
INSERT INTO `sys_user_role` VALUES ('3', '1', '17');
INSERT INTO `sys_user_role` VALUES ('5', '15', '18');
