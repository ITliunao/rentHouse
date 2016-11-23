package com.xmmxjy.system.shiro;

import com.xmmxjy.system.Constants;
import com.xmmxjy.system.service.UserService;
import com.xmmxjy.system.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 用户认证
 *
 * @author yang
 */
public class ShiroUserRealm extends AuthorizingRealm {
    public Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private UserService userService;

    public static final String HASH_ALGORITHM = "MD5";
    public static final int HASH_INTERATIONS = 1;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {
        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserEntity user = ShiroSessionUtils.getLoginUser();
        Set<String> permissions;
        if (null == user) {
            user = userService.selectByUsername(username);
        }
        // 获取权限信息
        if (null != user) {
            //从session中获取权限
            Object o = ShiroSessionUtils.getAttribute(Constants.SHIRO_PERMISSIONS);
            if (o != null) {
                permissions = (Set<String>) o;
            } else {
                permissions = userService.findPermissions(user.getId());
                ShiroSessionUtils.setAttribute(Constants.SHIRO_PERMISSIONS, permissions);
            }
            if (permissions != null && permissions.size() > 0) {
                authorizationInfo.addStringPermissions(permissions);
            }
            logger.info("authorizationInfo : " + authorizationInfo.getStringPermissions());
            return authorizationInfo;
        } else {
            throw new AuthorizationException();
        }
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        // 根据用户名查找用户
        // Account account =
        // accountService.findAccountByUsername(token2.getUsername());
        UserEntity user = null;
        user = userService.selectByUsername(username);
        if (null != user) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                    username, user.getPassword(), this.getName());
            ShiroSessionUtils.setAsLogin(user);

            /**
             * 关闭浏览器，再打开后，虽然授权缓存了，但是认证是必须的，在认证成功后，清除之前的缓存。
             */
            clearCache(authcInfo.getPrincipals());

            return authcInfo;
        } else {
            // 认证没有通过
            throw new UnknownAccountException();// 没帐号
        }
    }

}
