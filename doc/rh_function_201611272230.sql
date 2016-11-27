INSERT INTO `rent-house`.rh_function (id,functionlevel,functionname,functionorder,functionurl,parent_function_id,icon,function_type,create_by,create_name,update_by,update_date,create_date,update_name,permission) VALUES 
('A01',1,'系统管理','1','',NULL,'',NULL,'','','',NULL,NULL,'','system')
,('A01A01',2,'用户管理','1','user/list.do','A01','',NULL,'','','',NULL,NULL,'','system.user.list')
,('A01A02',2,'菜单管理','2','function/list.do','A01','',NULL,'','','',NULL,NULL,'','system.functioin.list')
;