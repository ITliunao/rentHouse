package com.xmmxjy.system.service.impl;

import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.system.dao.RoleDao;
import com.xmmxjy.system.entity.RoleEntity;
import com.xmmxjy.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：角色
 * @author: xmm
 * @since：2016年09月19日 16时37分13秒 星期一 
 * @version:1.0
 */

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleEntity> implements RoleService {
	@Autowired
	private RoleDao roleDao;



	@Override
	public List<String> getPermissionList(String id) {
		return roleDao.getPermissionList(id);
	}

	@Override
	public void updateRole(String id, List<String> idList) {
		//删除该权限对应的资源
		roleDao.deletePermission(id);
		//新增该权限对应的资源
		for (int i = 0 ; i < idList.size(); i++) {
			roleDao.insertPermission(id,idList.get(i));
		}
	}

	@Override
	public List<String> functionListByRoleId(String id) {
		return roleDao.functionListByRoleId(id);
	}

}
