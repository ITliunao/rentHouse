package com.xmmxjy.system.service;

import com.xmmxjy.common.service.BaseService;
import com.xmmxjy.system.entity.RoleEntity;

import java.util.List;

/**
 * 描述：角色
 * @author: xmm
 * @since：2016年09月19日 16时37分13秒 星期一 
 * @version:1.0
 */
public interface RoleService extends BaseService<RoleEntity>{


	/**
	 * 获取该角色所对应的资源ID
	 * @param id
	 * @return
	 */
    public List<String> getPermissionList(String id);
}
