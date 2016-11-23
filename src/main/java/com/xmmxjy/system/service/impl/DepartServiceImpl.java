package com.xmmxjy.system.service.impl;


import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.system.dao.DepartDao;
import com.xmmxjy.system.entity.DepartEntity;
import com.xmmxjy.system.entity.UserEntity;
import com.xmmxjy.system.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：部门
 * @author: xmm
 * @since：2016年09月19日 16时28分19秒 星期一 
 * @version:1.0
 */
@Service("departService")
public class DepartServiceImpl extends BaseServiceImpl<DepartEntity> implements DepartService {

	@Autowired
	private DepartDao departDao;
}
