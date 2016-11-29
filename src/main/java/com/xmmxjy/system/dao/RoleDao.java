package com.xmmxjy.system.dao;


import com.xmmxjy.common.mapper.MyMapper;
import com.xmmxjy.system.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述：角色
 * @author：xmm
 * @since：2016年09月19日 16时37分13秒 星期一 
 * @version:1.0
 */
public interface RoleDao extends MyMapper<RoleEntity> {


	List<String> getPermissionList(String id);

    List<String> functionListByRoleId(String id);

    void deletePermission(String id);

    void insertPermission(@Param("id") String id, @Param("functionId") String functionId);
}

