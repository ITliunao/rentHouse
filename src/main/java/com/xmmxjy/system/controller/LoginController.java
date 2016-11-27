package com.xmmxjy.system.controller;

import com.xmmxjy.common.controller.BaseEndController;
import com.xmmxjy.common.util.EndUtil;
import com.xmmxjy.system.entity.UserEntity;
import com.xmmxjy.system.service.FunctionService;
import com.xmmxjy.system.service.UserService;
import com.xmmxjy.system.shiro.ShiroSessionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 16-9-20.
 */
@Controller
public class LoginController extends BaseEndController {

    @Autowired
    private UserService userService;
    @Autowired
    private FunctionService functionService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value="/login.do" , method = {RequestMethod.GET, RequestMethod.POST})
    public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserEntity user, ModelMap model){
        Subject currentUser = SecurityUtils.getSubject();
        EndUtil.sendEndParams(request,model);
        try {
            if (!currentUser.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
                currentUser.login(token);
            }
            model.addAttribute("user", ShiroSessionUtils.getAttribute(ShiroSessionUtils.LOGIN_ATTRIVUTE_NAME));
            return "redirect:/";
        }catch (AuthenticationException e) {
            return END_PAGE + LOGIN;
        }

    }
    @RequestMapping( value = "/logout.do",method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        UserEntity user =  (UserEntity) ShiroSessionUtils.getAttribute(ShiroSessionUtils.LOGIN_ATTRIVUTE_NAME);
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
                if (logger.isInfoEnabled()){
                    if (user != null) {
                        logger.info("用户 ：" + user.getUsername() + "退出登录");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return END_PAGE + LOGIN;
    }


}
