package com.xmmxjy.system.service;
import java.util.List;
import java.util.Set;

import com.xmmxjy.common.mapper.Pagination;
import com.xmmxjy.common.service.BaseService;
import com.xmmxjy.system.entity.UserEntity;


/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-14 22:16:43
 */
public interface UserService extends BaseService<UserEntity>{
	
	/**
	 * 分页查找
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	List<UserEntity> listPage(UserEntity user, Pagination pagination);



	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	UserEntity selectByUsername(String username);

	/**
	 * 根据用户名查找权限
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);


	List<UserEntity> list (UserEntity user);

	/**
	 * 根据用户id更新密码
	 * @param id
	 * @param password
	 */
	int update(String id, String password);
}
