package com.xmmxjy.system.service.impl;

import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.system.dao.DictTypeDao;
import com.xmmxjy.system.entity.DictTypeEntity;
import com.xmmxjy.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：角色
 * @author: xmm
 * @since：2016年09月19日 16时37分13秒 星期一 
 * @version:1.0
 */

@Service("dictTypeService")
public class DictTypeServiceImpl extends BaseServiceImpl<DictTypeEntity> implements DictTypeService {
	@Autowired
	private DictTypeDao dictTypeDao;


}
