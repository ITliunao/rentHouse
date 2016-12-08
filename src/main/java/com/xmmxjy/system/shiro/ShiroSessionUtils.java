package com.xmmxjy.system.shiro;

import com.xmmxjy.system.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroSessionUtils {

	public static final String LOGIN_ATTRIVUTE_NAME = "currentUser";

	public static void setAttribute(Object key, Object value) {
		Subject account = SecurityUtils.getSubject();
		if (null != account) {
			Session session = account.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	public static Object getAttribute(Object key) {
		Subject account = SecurityUtils.getSubject();
		if (null != account) {
			Session session = account.getSession();
			if (null != session) {
				return session.getAttribute(key);
			}
		}
		return null;
	}

	public static Object removeAttribute(Object key) {
		Subject account = SecurityUtils.getSubject();
		if (null != account) {
			Session session = account.getSession();
			if (null != session) {
				return session.removeAttribute(key);
			}
		}
		return null;
	}

	public static boolean hasLogin() {
		return getAttribute(LOGIN_ATTRIVUTE_NAME) != null ? true : false;
	}

	public static void setAsLogin(UserEntity user) {
		setAttribute(LOGIN_ATTRIVUTE_NAME, user);
	}

	public static void setAsLogout() {
		removeAttribute(LOGIN_ATTRIVUTE_NAME);
	}

	public static UserEntity getLoginUser() {
		Object o = getAttribute(LOGIN_ATTRIVUTE_NAME);
		if(o!=null){
			return (UserEntity) o;
		}
		return null;
	}

}
