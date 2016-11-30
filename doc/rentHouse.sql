DROP TABLE IF EXISTS rh_user;
CREATE TABLE rh_user (
  id varchar(32) NOT NULL COMMENT '主键,UUID自动生成',
  username varchar(20) NOT NULL COMMENT '用户名',
  salt varchar(100) DEFAULT NULL COMMENT '盐值',
  password varchar(100) DEFAULT NULL COMMENT '密码',
  real_name varchar(50) DEFAULT NULL COMMENT '真实姓名',
  status smallint(6) DEFAULT NULL COMMENT '用户状态',
  email varchar(50) DEFAULT NULL COMMENT '邮箱',
  birthday datetime DEFAULT NULL COMMENT '生日',
  sex varchar(10) DEFAULT NULL COMMENT '性别',
  mobile_phone varchar(30) DEFAULT NULL COMMENT '手机号码',
  office_phone varchar(20) DEFAULT NULL COMMENT '办公室电话',
  region varchar(50) DEFAULT NULL COMMENT '所属区域',
  last_login_time varchar(20) DEFAULT NULL COMMENT '上次登录时间',
  last_login_ip varchar(20) DEFAULT NULL COMMENT '上次登录IP',
  role_id varchar(32) DEFAULT NULL COMMENT '角色ID',
  depart_id varchar(32) DEFAULT NULL COMMENT '部门ID',
  update_name varchar(32) DEFAULT NULL COMMENT '修改人',
  update_date datetime DEFAULT NULL COMMENT '修改时间',
  update_by varchar(32) DEFAULT NULL COMMENT '修改人id',
  create_name varchar(32) DEFAULT NULL COMMENT '创建人',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  create_by varchar(32) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS rh_role;
CREATE TABLE rh_role (
  id varchar(32) NOT NULL COMMENT 'ID',
  role_code varchar(20) DEFAULT NULL COMMENT '角色编码',
  role_name varchar(50) DEFAULT NULL COMMENT '角色名称',
  update_name varchar(32) DEFAULT NULL COMMENT '修改人',
  update_date datetime DEFAULT NULL COMMENT '修改时间',
  update_by varchar(32) DEFAULT NULL COMMENT '修改人id',
  create_name varchar(32) DEFAULT NULL COMMENT '创建人',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  create_by varchar(32) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS rh_role_user;
CREATE TABLE rh_role_user (
  role_id varchar(32) NOT NULL,
  user_id varchar(32) NOT NULL,
  PRIMARY KEY (role_id,user_id),
  KEY fk_user_role_id (user_id),
  KEY fk_role_user_id (role_id),
  CONSTRAINT fk_role_user_id FOREIGN KEY (role_id) REFERENCES rh_role (id),
  CONSTRAINT fk_user_role_id FOREIGN KEY (user_id) REFERENCES rh_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS rh_depart;
CREATE TABLE rh_depart (
  id varchar(32) NOT NULL COMMENT 'ID',
  depart_name varchar(100) NOT NULL COMMENT '部门名称',
  description longtext COMMENT '部门描述',
  parent_depart_id varchar(32) DEFAULT NULL COMMENT '父ID',
  org_code varchar(64) DEFAULT NULL COMMENT '部门编码',
  org_type varchar(1) DEFAULT NULL COMMENT '部门类型',
  mobile varchar(32) DEFAULT NULL COMMENT '手机号码',
  address varchar(100) DEFAULT NULL COMMENT '地址',
  create_by varchar(32) DEFAULT NULL COMMENT '创建人id',
  create_name varchar(32) DEFAULT NULL COMMENT '创建人',
  update_by varchar(32) DEFAULT NULL COMMENT '修改人id',
  update_date datetime DEFAULT NULL COMMENT '修改时间',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_name varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (id),
  KEY fk_parent_depart_id (parent_depart_id),
  CONSTRAINT fk_parent_depart_id FOREIGN KEY (parent_depart_id) REFERENCES rh_depart (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS rh_function;
CREATE TABLE rh_function (
  id varchar(32) NOT NULL,
  functionlevel smallint(6) DEFAULT NULL COMMENT '菜单级别',
  functionname varchar(50) NOT NULL COMMENT '菜单名称',
  functionorder varchar(10) DEFAULT NULL COMMENT '菜单顺序',
  functionurl varchar(100) DEFAULT NULL COMMENT '菜单地址',
  parent_function_id varchar(32) DEFAULT NULL COMMENT '父ID',
  icon varchar(50) DEFAULT NULL COMMENT '图标的css样式',
  function_type smallint(6) DEFAULT NULL COMMENT '菜单类型',
  create_by varchar(32) DEFAULT NULL COMMENT '创建人id',
  create_name varchar(32) DEFAULT NULL COMMENT '创建人',
  update_by varchar(32) DEFAULT NULL COMMENT '修改人id',
  update_date datetime DEFAULT NULL COMMENT '修改时间',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_name varchar(32) DEFAULT NULL COMMENT '修改人',
  permission varchar(100) DEFAULT '' COMMENT '访问权限',
  PRIMARY KEY (id),
  KEY fk_parent_function_id (parent_function_id),
  CONSTRAINT fk_parent_function_id FOREIGN KEY (parent_function_id) REFERENCES rh_function (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS rh_role_function;
CREATE TABLE rh_role_function (
  function_id varchar(32) NOT NULL,
  role_id varchar(32) NOT NULL,
  PRIMARY KEY (function_id,role_id),
  KEY fk_function_role_id (function_id),
  KEY fk_role_function_id (role_id),
  CONSTRAINT fk_function_role_id FOREIGN KEY (function_id) REFERENCES rh_function (id),
  CONSTRAINT fk_role_function_id FOREIGN KEY (role_id) REFERENCES rh_role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS rh_dict_type;
CREATE TABLE rh_dict_type (
  id varchar(32) NOT NULL COMMENT '主键',
  name varchar(50) NOT NULL COMMENT '类型名称',
  code varchar(32) NOT NULL COMMENT '类型编码',
  description varchar(100) DEFAULT NULL COMMENT '类型描述',
  seq int(11) NOT NULL COMMENT '排序，由小到大',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS rh_dict_data;
CREATE TABLE rh_dict_data (
  id varchar(32) NOT NULL,
  name varchar(32) NOT NULL COMMENT '名称',
  value varchar(255) NOT NULL COMMENT '对应的值',
  seq int(11) NOT NULL COMMENT '排序，由小到大',
  create_by varchar(32) DEFAULT NULL COMMENT '创建人id',
  create_name varchar(32) DEFAULT NULL COMMENT '创建人',
  update_by varchar(32) DEFAULT NULL COMMENT '修改人id',
  update_date datetime DEFAULT NULL COMMENT '修改时间',
  create_date datetime DEFAULT NULL COMMENT '创建时间',
  update_name varchar(32) DEFAULT NULL COMMENT '修改人',
  dict_type_id int(11) NOT NULL COMMENT '字典类型ID',
  PRIMARY KEY (`id`),
  KEY fk_dict_type_id (dict_type_id),
  CONSTRAINT fk_dict_type_id FOREIGN KEY (dict_type_id) REFERENCES rh_dict_type (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*
CREATE TABLE rh_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '系统用户ID',
  `from` varchar(255) DEFAULT NULL COMMENT '来源 url',
  `ip` varchar(22) DEFAULT NULL COMMENT '客户端IP',
  `url` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL COMMENT '记录时间',
  `err_msg` text COMMENT '异常信息',
  `err_code` int(10) DEFAULT '0' COMMENT '状态码，0：正常',
  `class_name` varchar(200) DEFAULT NULL COMMENT 'controller类名',
  `method_name` varchar(64) DEFAULT NULL COMMENT '方法名',
  `start_time` datetime DEFAULT NULL COMMENT '操作时间',
  `spend_time` bigint(20) DEFAULT NULL COMMENT '耗时，毫秒',
  `params` text COMMENT '提供的参数',
  PRIMARY KEY (`id`),
  KEY `FK_sys_EVENT` (`uid`),
  CONSTRAINT `sys_log_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37061 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;*/
