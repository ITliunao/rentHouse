package com.xmmxjy.system.service;

import com.xmmxjy.common.service.BaseService;
import com.xmmxjy.system.entity.FunctionEntity;

/**
 * 描述：菜单
 * @author: xmm
 * @since：2016年09月19日 16时27分34秒 星期一 
 * @version:1.0
 */
public interface FunctionService extends BaseService<FunctionEntity>{


    String getMaxLocalCode(String localCodeLength, String parentCode);
}
