package com.xmmxjy.system.dao;


import com.xmmxjy.common.mapper.MyMapper;
import com.xmmxjy.system.entity.FunctionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：菜单
 * @author：xmm
 * @since：2016年09月19日 16时27分34秒 星期一 
 * @version:1.0
 */
public interface FunctionDao extends MyMapper<FunctionEntity> {


	String getMaxLocalCode(@Param("localCodeLength") String localCodeLength,@Param("parentCode") String parentCode);
}

