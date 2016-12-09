-- 栏目表
DROP TABLE IF EXISTS rh_channel;
create table rh_channel (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  parent_id int(11) DEFAULT NULL COMMENT '父栏目ID',
  name VARCHAR (50) NOT NULL COMMENT '栏目名称',
  type int(11) DEFAULT NULL COMMENT '类型',
  sort int(11) NOT NULL default '0' COMMENT '顺序',
  is_display tinyint(11) DEFAULT '1' COMMENT '是否显示',
  PRIMARY KEY  (id),
  KEY fk_channel_parent (parent_id),
  CONSTRAINT fk_channel_parent FOREIGN KEY (parent_id) REFERENCES rh_channel (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '栏目';


DROP TABLE IF EXISTS rh_channel;
create table rh_channel (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  name VARCHAR (50) NOT NULL COMMENT '栏目名称',
  type int(11) DEFAULT NULL COMMENT '类型',
  sort int(11) NOT NULL default '0' COMMENT '顺序',
  is_display tinyint(11) DEFAULT '1' COMMENT '是否显示',
  PRIMARY KEY  (id),
  CONSTRAINT fk_channel_my FOREIGN KEY (id) REFERENCES rh_channel (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '栏目';


create table rh_channel_paths (
  ancestor int(11) NOT NULL COMMENT '上级ID',
  descendant int(11) NOT NULL COMMENT '下级ID',
  path_length int(11) NOT NULL COMMENT '路径长度',
  PRIMARY KEY(ancestor,descendant),
  CONSTRAINT fk_channel_ancestor FOREIGN KEY (ancestor) REFERENCES rh_channel(id),
  CONSTRAINT fk_channel_descendant FOREIGN KEY (descendant) REFERENCES rh_channel(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '栏目表关系表';


-- 区域表 包括省，直辖市，自治区
DROP TABLE IF EXISTS rh_province;
create table rh_province (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  code VARCHAR(20) NOT NULL COMMENT '省份编码',
  name VARCHAR(20) NOT NULL COMMENT '省份名称',
  PRIMARY KEY  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '省份';

-- 省份 栏目 关联表
DROP TABLE IF EXISTS rh_channel_province;
create table rh_channel_province (
  channel_id int(11) NOT NULL COMMENT '栏目ID',
  province_id int(11) NOT NULL  COMMENT '省份ID',
  PRIMARY KEY  (channel_id,province_id),
  CONSTRAINT rh_channel_province_channel FOREIGN KEY (channel_id) REFERENCES rh_channel (id),
  CONSTRAINT rh_channel_province_province FOREIGN KEY (province_id) REFERENCES rh_province (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '栏目与省份关联';

-- 新闻内容

DROP TABLE IF EXISTS rh_content;
create table rh_content (
  id bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  title VARCHAR(200) NOT NULL COMMENT '标题',
  user_id bigint(11) NOT NULL COMMENT '发表用户',
  check_user_id bigint(20) default NULL COMMENT '审核用户',
  channel_id int(11) NOT NULL COMMENT '栏目',
  copied tinyint(1) NOT NULL COMMENT '是否转载',
  author varchar(50) DEFAULT NULL COMMENT '作者',
  editor varchar(50) DEFAULT NULL COMMENT '编辑',
  description varchar(300) DEFAULT NULL COMMENT '简介',
  clicks int(11) NOT NULL COMMENT '点击数',
  comments int(11) NOT NULL COMMENT '评论数',
  publish_date datetime NOT NULL COMMENT '发布日期',
  create_date datetime NOT NULL COMMENT '创建日期',
  status int(11) NOT NULL COMMENT '状态：0、草稿 1、已发布 2、待审核',
  is_comment tinyint(1) DEFAULT '1' COMMENT '是否可以评论',
  PRIMARY KEY  (id),
  FOREIGN KEY (channel_id) REFERENCES rh_channel(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '新闻内容';


-- 具体的内容
-- ----------------------------
DROP TABLE IF EXISTS rh_content_attribute;
CREATE TABLE rh_content_attribute (
  id bigint(20) NOT NULL,
  source varchar(50) default NULL COMMENT '内容来源',
  source_url varchar(2048) default NULL COMMENT '来源地址',
  data longtext COMMENT '数据JSON',
  text longtext COMMENT '内容',
  word_count int(11) NOT NULL COMMENT '字数',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容扩展';

-- 标签

DROP TABLE IF EXISTS rh_tag;
CREATE TABLE rh_tag (
  id bigint(20) NOT NULL auto_increment,
  name varchar(50) NOT NULL COMMENT '名称',
  search_count int(11) NOT NULL COMMENT '搜索次数',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签';

-- 内容标签

DROP TABLE IF EXISTS rh_content_tag;
CREATE TABLE rh_content_tag (
  tag_id bigint(20) NOT NULL COMMENT '标签ID',
  content_id bigint(20) NOT NULL COMMENT '内容ID',
  PRIMARY KEY  (tag_id,content_id),
  CONSTRAINT rh_content_tag_content FOREIGN KEY (content_id) REFERENCES rh_content (id),
  CONSTRAINT rh_content_tag_tag FOREIGN KEY (tag_id) REFERENCES rh_tag (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容标签';