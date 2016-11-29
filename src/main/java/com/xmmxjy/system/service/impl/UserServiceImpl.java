package com.xmmxjy.system.service.impl;

import java.util.List;
import java.util.Set;

import com.xmmxjy.system.entity.UserEntity;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmmxjy.common.mapper.Pagination;
import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.system.dao.UserDao;
import com.xmmxjy.system.service.UserService;



/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-14 22:16:43
 */
@Service("userService")
public class UserServiceImpl  extends BaseServiceImpl<UserEntity> implements UserService {
	@Autowired
    private UserDao userDao;
   
    public List<UserEntity> listPage(UserEntity user, Pagination pagination){
		return userDao.listPage(user,pagination);
	}

	@Override
	public UserEntity selectByUsername(String username) {
		return userDao.selectByUsername(username);
	}

	@Override
	public Set<String> findPermissions(String id) {
		return userDao.findPermissions(id);
	}

	@Override
	public List<UserEntity> list(UserEntity user) {
		return userDao.select(user);
	}

	@Override
	public int update(String id, String password) {
		UserEntity user2 = new UserEntity();
		user2.setId(id);
		user2.setPassword(new Md5Hash(password).toString());
		return userDao.updateByPrimaryKey(user2);
	}

	@Override
	public int saveUser(UserEntity entity) {
		entity.setPassword(new Md5Hash(entity.getPassword()).toString());
		return userDao.insert(entity);
	}
}
