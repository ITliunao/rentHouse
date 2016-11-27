package com.xmmxjy.common.util;

import com.xmmxjy.system.service.FunctionService;


/**
 * 根据 P3 1.0的TableTree 的页面组件规则的ID 生成器
 * (代码逻辑摘抄自 jeecg)
 * @author jg_huangxg
 *
 */
public class SimpleTreeIdBuild {
	
	public String getId(FunctionService functionService, String parentCode) {
		
		String idCode = null;
		String localMaxCode = null;
		if (Tools.isEmpty(parentCode)) {//无上级
			localMaxCode = getMaxLocalCode(functionService,null);
			idCode = YouBianCodeUtil.getNextYouBianCode(localMaxCode);
		}else{//有上级
			localMaxCode = getMaxLocalCode(functionService,parentCode);
			idCode = YouBianCodeUtil.getSubYouBianCode(parentCode, localMaxCode);
		}
		
		return idCode;
		
	}
	
	private synchronized String getMaxLocalCode(FunctionService functionService,String parentCode){
		if(Tools.isEmpty(parentCode)){
			parentCode = "";
		}
		int localCodeLength = parentCode.length() + YouBianCodeUtil.zhanweiLength;

		return functionService.getMaxLocalCode(String.valueOf(localCodeLength), parentCode);
	}
}
