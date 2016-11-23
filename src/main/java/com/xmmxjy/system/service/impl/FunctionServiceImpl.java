package com.xmmxjy.system.service.impl;

import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.system.dao.FunctionDao;
import com.xmmxjy.system.entity.FunctionEntity;
import com.xmmxjy.system.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：菜单
 * @author: xmm
 * @since：2016年09月19日 16时27分34秒 星期一 
 * @version:1.0
 */

@Service("functionService")
public class FunctionServiceImpl extends BaseServiceImpl<FunctionEntity> implements FunctionService {

	@Autowired
	private FunctionDao functionDao;

	public String getMaxLocalCode(String localCodeLength, String parentCode) {
		return functionDao.getMaxLocalCode(localCodeLength, parentCode);
	}


}
