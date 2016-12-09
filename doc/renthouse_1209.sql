/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : renthouse

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2016-12-09 19:46:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `rh_channel`
-- ----------------------------
DROP TABLE IF EXISTS `rh_channel`;
CREATE TABLE `rh_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父栏目ID',
  `name` varchar(50) NOT NULL COMMENT '栏目名称',
  `type` int(11) DEFAULT NULL COMMENT '栏目类型',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `is_display` tinyint(11) DEFAULT '1' COMMENT '是否显示',
  PRIMARY KEY (`id`),
  KEY `fk_channel_parent` (`parent_id`),
  CONSTRAINT `fk_channel_parent` FOREIGN KEY (`parent_id`) REFERENCES `rh_channel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='栏目';

-- ----------------------------
-- Records of rh_channel
-- ----------------------------
INSERT INTO rh_channel VALUES ('1', null, '体育', null, '1', '1');
INSERT INTO rh_channel VALUES ('2', '1', '篮球', null, '2', '1');
INSERT INTO rh_channel VALUES ('3', '1', 'NBA', null, '3', null);
INSERT INTO rh_channel VALUES ('4', null, '音乐', null, '5', null);
INSERT INTO rh_channel VALUES ('5', '4', '古典音乐', null, '6', null);

-- ----------------------------
-- Table structure for `rh_content`
-- ----------------------------
DROP TABLE IF EXISTS `rh_content`;
CREATE TABLE `rh_content` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `user_id` bigint(11) NOT NULL COMMENT '发表用户',
  `check_user_id` bigint(20) DEFAULT NULL COMMENT '审核用户',
  `channel_id` int(11) NOT NULL COMMENT '栏目',
  `copied` tinyint(1) NOT NULL COMMENT '是否转载',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `editor` varchar(50) DEFAULT NULL COMMENT '编辑',
  `description` varchar(300) DEFAULT NULL COMMENT '简介',
  `clicks` int(11) NOT NULL COMMENT '点击数',
  `comments` int(11) NOT NULL COMMENT '评论数',
  `publish_date` datetime NOT NULL COMMENT '发布日期',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `status` int(11) NOT NULL COMMENT '状态：0、草稿 1、已发布 2、待审核',
  `is_comment` tinyint(1) DEFAULT '1' COMMENT '是否可以评论',
  PRIMARY KEY (`id`),
  KEY `channel_id` (`channel_id`),
  CONSTRAINT `rh_content_ibfk_1` FOREIGN KEY (`channel_id`) REFERENCES `rh_channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻内容';

-- ----------------------------
-- Records of rh_content
-- ----------------------------

-- ----------------------------
-- Table structure for `rh_content_attribute`
-- ----------------------------
DROP TABLE IF EXISTS `rh_content_attribute`;
CREATE TABLE `rh_content_attribute` (
  `id` bigint(20) NOT NULL,
  `source` varchar(50) DEFAULT NULL COMMENT '内容来源',
  `source_url` varchar(2048) DEFAULT NULL COMMENT '来源地址',
  `data` longtext COMMENT '数据JSON',
  `text` longtext COMMENT '内容',
  `word_count` int(11) NOT NULL COMMENT '字数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容扩展';

-- ----------------------------
-- Records of rh_content_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for `rh_content_tag`
-- ----------------------------
DROP TABLE IF EXISTS `rh_content_tag`;
CREATE TABLE `rh_content_tag` (
  `tag_id` bigint(20) NOT NULL COMMENT '标签ID',
  `content_id` bigint(20) NOT NULL COMMENT '内容ID',
  PRIMARY KEY (`tag_id`,`content_id`),
  KEY `rh_content_tag_content` (`content_id`),
  CONSTRAINT `rh_content_tag_content` FOREIGN KEY (`content_id`) REFERENCES `rh_content` (`id`),
  CONSTRAINT `rh_content_tag_tag` FOREIGN KEY (`tag_id`) REFERENCES `rh_tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容标签';

-- ----------------------------
-- Records of rh_content_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `rh_depart`
-- ----------------------------
DROP TABLE IF EXISTS `rh_depart`;
CREATE TABLE `rh_depart` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `depart_name` varchar(100) NOT NULL COMMENT '部门名称',
  `description` longtext COMMENT '部门描述',
  `parent_depart_id` varchar(32) DEFAULT NULL COMMENT '父ID',
  `org_code` varchar(64) DEFAULT NULL COMMENT '部门编码',
  `org_type` varchar(20) DEFAULT NULL COMMENT '部门类型',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `fk_parent_depart_id` (`parent_depart_id`),
  CONSTRAINT `fk_parent_depart_id` FOREIGN KEY (`parent_depart_id`) REFERENCES `rh_depart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rh_depart
-- ----------------------------
INSERT INTO rh_depart VALUES ('A01', '北京北京', '', null, '', '', '', '', null, null, null, null, null, null);
INSERT INTO rh_depart VALUES ('A01A01', '昌平', '昌平', 'A01', '', '', '', '', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `rh_dict_data`
-- ----------------------------
DROP TABLE IF EXISTS `rh_dict_data`;
CREATE TABLE `rh_dict_data` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `value` varchar(255) NOT NULL COMMENT 'Ӧֵ',
  `seq` int(11) NOT NULL COMMENT 'С',
  `create_by` varchar(32) DEFAULT NULL COMMENT 'id',
  `create_name` varchar(32) DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL COMMENT '޸id',
  `update_date` datetime DEFAULT NULL COMMENT '޸ʱ',
  `create_date` datetime DEFAULT NULL COMMENT 'ʱ',
  `update_name` varchar(32) DEFAULT NULL COMMENT '޸',
  `dict_type_id` varchar(32) NOT NULL COMMENT 'ֵID',
  PRIMARY KEY (`id`),
  KEY `fk_dict_type_id` (`dict_type_id`),
  CONSTRAINT `fk_dict_type_id` FOREIGN KEY (`dict_type_id`) REFERENCES `rh_dict_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rh_dict_data
-- ----------------------------
INSERT INTO rh_dict_data VALUES ('4827d157a52e4722b1bdca5fabcb8cfb', '女', '0', '2', null, null, null, null, null, null, '479c5e0f1cac4e388f227c7d4e77ba36');
INSERT INTO rh_dict_data VALUES ('df09afc053fb4f1e983e69042cb70076', '男', '1', '1', null, null, null, null, null, null, '479c5e0f1cac4e388f227c7d4e77ba36');

-- ----------------------------
-- Table structure for `rh_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `rh_dict_type`;
CREATE TABLE `rh_dict_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  `code` varchar(32) NOT NULL COMMENT 'ͱ',
  `description` varchar(100) DEFAULT NULL,
  `seq` int(11) NOT NULL COMMENT 'С',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rh_dict_type
-- ----------------------------
INSERT INTO rh_dict_type VALUES ('479c5e0f1cac4e388f227c7d4e77ba36', '性别', 'sex', '', '1');

-- ----------------------------
-- Table structure for `rh_function`
-- ----------------------------
DROP TABLE IF EXISTS `rh_function`;
CREATE TABLE `rh_function` (
  `id` varchar(32) NOT NULL,
  `functionlevel` smallint(6) DEFAULT NULL COMMENT '菜单级别',
  `functionname` varchar(50) NOT NULL COMMENT '菜单名称',
  `functionorder` int(10) DEFAULT NULL COMMENT '菜单顺序',
  `functionurl` varchar(100) DEFAULT NULL COMMENT '菜单地址',
  `parent_function_id` varchar(32) DEFAULT NULL COMMENT '父ID',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标的css样式',
  `function_type` smallint(6) DEFAULT NULL COMMENT '菜单类型',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人',
  `permission` varchar(100) DEFAULT '' COMMENT '访问权限',
  PRIMARY KEY (`id`),
  KEY `fk_parent_function_id` (`parent_function_id`),
  CONSTRAINT `fk_parent_function_id` FOREIGN KEY (`parent_function_id`) REFERENCES `rh_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rh_function
-- ----------------------------
INSERT INTO rh_function VALUES ('A01', '1', '系统管理', '1', '', null, '', null, '', '', '', null, null, '', 'system');
INSERT INTO rh_function VALUES ('A01A01', '2', '用户管理', '1', 'user/list.do', 'A01', '', null, '', '', '', null, null, '', 'system.user.list');
INSERT INTO rh_function VALUES ('A01A01A01', '3', '新增', '1', '', 'A01A01', '', null, '', '', '', null, null, '', 'system.user.add');
INSERT INTO rh_function VALUES ('A01A01A02', '3', '修改', '2', '', 'A01A01', '', null, '', '', '', null, null, '', 'system.user.edit');
INSERT INTO rh_function VALUES ('A01A01A03', '3', '删除', '3', '', 'A01A01', '', null, '', '', '', null, null, '', 'system.user.delete');
INSERT INTO rh_function VALUES ('A01A01A04', '3', '修改密码', '4', '', 'A01A01', '', null, '', '', '', null, null, '', 'system.user.password');
INSERT INTO rh_function VALUES ('A01A01A05', '3', '详情', '5', '', 'A01A01', '', null, '', '', '', null, null, '', 'system.user.detail');
INSERT INTO rh_function VALUES ('A01A02', '2', '菜单管理', '2', 'function/list.do', 'A01', '', null, '', '', '', null, null, '', 'system.functioin.list');
INSERT INTO rh_function VALUES ('A01A02A01', '3', '新增', '1', '', 'A01A02', '', null, '', '', '', null, null, '', 'system.function.add');
INSERT INTO rh_function VALUES ('A01A02A02', '3', '修改', '2', '', 'A01A02', '', null, '', '', '', null, null, '', 'system.function.edit');
INSERT INTO rh_function VALUES ('A01A02A03', '3', '删除', '3', '', 'A01A02', '', null, '', '', '', null, null, '', 'system.function.delete');
INSERT INTO rh_function VALUES ('A01A02A04', '3', '详情', '4', '', 'A01A02', '', null, '', '', '', null, null, '', 'system.function.detail');
INSERT INTO rh_function VALUES ('A01A03', '2', '部门管理', '3', 'depart/list.do', 'A01', '', null, '', '', '', null, null, '', 'system.depart.list');
INSERT INTO rh_function VALUES ('A01A03A01', '3', '新增', '1', '', 'A01A03', '', null, '', '', '', null, null, '', 'system.depart.add');
INSERT INTO rh_function VALUES ('A01A03A02', '3', '修改', '2', '', 'A01A03', '', null, '', '', '', null, null, '', 'system.depart.edit');
INSERT INTO rh_function VALUES ('A01A03A03', '3', '删除', '3', '', 'A01A03', '', null, '', '', '', null, null, '', 'system.depart.delete');
INSERT INTO rh_function VALUES ('A01A03A04', '3', '详情', '4', '', 'A01A03', '', null, '', '', '', null, null, '', 'system.depart.detail');
INSERT INTO rh_function VALUES ('A01A04', '2', '数据字典', '5', 'dictType/list.do', 'A01', '', null, '', '', '', null, null, '', 'system.dictType.list');
INSERT INTO rh_function VALUES ('A01A04A01', '3', '新增', '1', '', 'A01A04', '', null, '', '', '', null, null, '', 'system.dictType.add');
INSERT INTO rh_function VALUES ('A01A04A02', '3', '修改', '2', '', 'A01A04', '', null, '', '', '', null, null, '', 'system.dictType.edit');
INSERT INTO rh_function VALUES ('A01A04A03', '3', '删除', '3', '', 'A01A04', '', null, '', '', '', null, null, '', 'system.dictType.delete');
INSERT INTO rh_function VALUES ('A01A04A04', '3', '详情', '4', '', 'A01A04', '', null, '', '', '', null, null, '', 'system.dictType.detail');
INSERT INTO rh_function VALUES ('A01A04A05', '3', '明细', '5', '', 'A01A04', '', null, '', '', '', null, null, '', 'system.dictData.list');
INSERT INTO rh_function VALUES ('A02', '2', '角色管理', '4', 'role/list.do', 'A01', '', null, '', '', '', null, null, '', 'system.role.list');
INSERT INTO rh_function VALUES ('A02A01', '3', '新增', '1', '', 'A02', '', null, '', '', '', null, null, '', 'system.role.add');
INSERT INTO rh_function VALUES ('A02A02', '3', '修改', '2', '', 'A02', '', null, '', '', '', null, null, '', 'system.role.edit');
INSERT INTO rh_function VALUES ('A02A03', '3', '删除', '3', '', 'A02', '', null, '', '', '', null, null, '', 'system.role.delete');
INSERT INTO rh_function VALUES ('A02A04', '3', '详情', '4', '', 'A02', '', null, '', '', '', null, null, '', 'system.role.detail');
INSERT INTO rh_function VALUES ('A02A05', '3', '分配权限', '5', '', 'A02', '', null, '', '', '', null, null, '', 'system.role.assign');
INSERT INTO rh_function VALUES ('A03', '1', '新闻管理', '2', '', null, '', null, '', '', '', null, null, '', 'cms');
INSERT INTO rh_function VALUES ('A03A01', '2', '栏目管理', '1', 'channel/list.do', 'A03', '', null, '', '', '', null, null, '', 'cms.channle.list');
INSERT INTO rh_function VALUES ('A03A01A01', '3', '新增', '1', '', 'A03A01', '', null, '', '', '', null, null, '', 'cms.channel.add');
INSERT INTO rh_function VALUES ('A03A01A02', '3', '删除', '2', '', 'A03A01', '', null, '', '', '', null, null, '', 'cms.channel.delete');
INSERT INTO rh_function VALUES ('A03A01A03', '3', '修改', '3', '', 'A03A01', '', null, '', '', '', null, null, '', 'cms.channel.edit');
INSERT INTO rh_function VALUES ('A03A01A04', '3', '详情', '4', '', 'A03A01', '', null, '', '', '', null, null, '', 'cms.channel.detail');
INSERT INTO rh_function VALUES ('A03A02', '2', '新闻内容', '2', 'content/list.do', 'A03', '', null, '', '', '', null, null, '', 'cms.content.list');
INSERT INTO rh_function VALUES ('A03A02A01', '3', '新增', '1', '', 'A03A02', '', null, '', '', '', null, null, '', 'cms.content.add');
INSERT INTO rh_function VALUES ('A03A02A02', '3', '修改', '2', '', 'A03A02', '', null, '', '', '', null, null, '', 'cms.content.edit');
INSERT INTO rh_function VALUES ('A03A02A03', '3', '删除', '3', '', 'A03A02', '', null, '', '', '', null, null, '', 'cms.content.delete');
INSERT INTO rh_function VALUES ('A03A03', '3', '详情', '4', '', 'A03A02', '', null, '', '', '', null, null, '', 'cms.content.detail');

-- ----------------------------
-- Table structure for `rh_province`
-- ----------------------------
DROP TABLE IF EXISTS `rh_province`;
CREATE TABLE `rh_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(20) NOT NULL COMMENT '省份编码',
  `name` varchar(20) NOT NULL COMMENT '省份名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省份';

-- ----------------------------
-- Records of rh_province
-- ----------------------------

-- ----------------------------
-- Table structure for `rh_role`
-- ----------------------------
DROP TABLE IF EXISTS `rh_role`;
CREATE TABLE `rh_role` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `create_name` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rh_role
-- ----------------------------
INSERT INTO rh_role VALUES ('e4a98004ca5e4f20b62fd9db6cdc53f7', 'test', '测试', '', null, '', '', null, '');
INSERT INTO rh_role VALUES ('ec40dba24fa14ff6a589739904029cf0', 'admin', '管理员', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `rh_role_function`
-- ----------------------------
DROP TABLE IF EXISTS `rh_role_function`;
CREATE TABLE `rh_role_function` (
  `function_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`function_id`,`role_id`),
  KEY `fk_function_role_id` (`function_id`),
  KEY `fk_role_function_id` (`role_id`),
  CONSTRAINT `fk_function_role_id` FOREIGN KEY (`function_id`) REFERENCES `rh_function` (`id`),
  CONSTRAINT `fk_role_function_id` FOREIGN KEY (`role_id`) REFERENCES `rh_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rh_role_function
-- ----------------------------
INSERT INTO rh_role_function VALUES ('A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A01A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A01A02', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A01A03', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A01A04', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A01A05', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A02', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A02A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A02A02', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A02A03', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A02A04', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A03', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A03A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A03A02', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A03A03', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A03A04', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A04', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A04A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A04A02', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A04A03', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A04A04', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A01A04A05', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A02', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A02A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A02A02', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A02A03', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A02A04', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A02A05', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03A01A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03A01A02', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03A01A03', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03A01A04', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03A02A01', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03A02A02', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03A02A03', 'ec40dba24fa14ff6a589739904029cf0');
INSERT INTO rh_role_function VALUES ('A03A03', 'ec40dba24fa14ff6a589739904029cf0');

-- ----------------------------
-- Table structure for `rh_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `rh_role_user`;
CREATE TABLE `rh_role_user` (
  `role_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_user_role_id` (`user_id`),
  KEY `fk_role_user_id` (`role_id`),
  CONSTRAINT `fk_role_user_id` FOREIGN KEY (`role_id`) REFERENCES `rh_role` (`id`),
  CONSTRAINT `fk_user_role_id` FOREIGN KEY (`user_id`) REFERENCES `rh_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rh_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for `rh_tag`
-- ----------------------------
DROP TABLE IF EXISTS `rh_tag`;
CREATE TABLE `rh_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '鍚嶇О',
  `search_count` int(11) NOT NULL COMMENT '鎼滅储娆℃暟',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鏍囩';

-- ----------------------------
-- Records of rh_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `rh_user`
-- ----------------------------
DROP TABLE IF EXISTS `rh_user`;
CREATE TABLE `rh_user` (
  `id` varchar(32) NOT NULL COMMENT '主键,UUID自动生成',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐值',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `status` smallint(6) DEFAULT NULL COMMENT '用户状态',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `mobile_phone` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `office_phone` varchar(20) DEFAULT NULL COMMENT '办公室电话',
  `region` varchar(50) DEFAULT NULL COMMENT '所属区域',
  `last_login_time` varchar(20) DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '上次登录IP',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `depart_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人id',
  `create_name` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rh_user
-- ----------------------------
INSERT INTO rh_user VALUES ('4AC1A1D75ED04981A4D1371293DC237C', 'admin', '', '21232f297a57a5a743894a0e4a801fc3', 'admin', null, 'xmmxjy@163.com', '2016-11-24 10:54:14', '1', '18800112233', '', '', '', '', 'ec40dba24fa14ff6a589739904029cf0', '', '', null, '', '', '2016-09-20 10:50:24', '');
INSERT INTO rh_user VALUES ('8dd2193c764946989ae63db8f9502683', 'test', null, '21232f297a57a5a743894a0e4a801fc3', 'test', null, '', null, '', '', '', '', '', '', 'e4a98004ca5e4f20b62fd9db6cdc53f7', '', '', null, '', '', null, '');
