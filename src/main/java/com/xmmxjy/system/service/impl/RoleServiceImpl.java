package com.xmmxjy.system.service.impl;

import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.system.dao.RoleDao;
import com.xmmxjy.system.entity.RoleEntity;
import com.xmmxjy.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		List<Map<String,Object>> list = roleDao.getPermissionList(id);
		List<String> pList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (Map<String,Object> map : list) {
				pList.add((String)map.get("permission"));
			}
		}
		return pList;
	}
}
