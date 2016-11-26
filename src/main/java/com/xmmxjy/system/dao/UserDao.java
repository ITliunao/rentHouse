package com.xmmxjy.system.dao;

import java.util.List;

import com.xmmxjy.common.mapper.Pagination;
import com.xmmxjy.system.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import com.xmmxjy.common.mapper.MyMapper;

/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-14 22:16:43
 * @see com.xmmxjy.system.entity.UserEntity
 */
public interface UserDao extends MyMapper<UserEntity> {
	
	/**
	 * 分页查找
	 * 
	 * @param user
	 * @param pagination
	 * @return
	 */
	List<UserEntity> listPage(@Param("user") UserEntity user,
                        @Param("pagination") Pagination pagination);

	/**
	 * 根据username查找user
	 * @param username
	 * @return
	 */
	UserEntity selectByUsername(@Param("username") String username);

	/**
	 * 根据ID更新密码
	 * @param id
	 * @param password
	 * @return
	 */
    int updatePassword(String id, String password);
}
