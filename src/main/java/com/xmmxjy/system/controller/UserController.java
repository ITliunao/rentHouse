package com.xmmxjy.system.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmmxjy.common.controller.BaseEndController;
import com.xmmxjy.common.util.AjaxJson;
import com.xmmxjy.common.util.BeanUtil;
import com.xmmxjy.common.util.EndUtil;
import com.xmmxjy.common.util.Tools;
import com.xmmxjy.system.entity.RoleEntity;
import com.xmmxjy.system.entity.UserEntity;
import com.xmmxjy.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.xmmxjy.system.service.UserService;

import java.util.List;
import java.util.UUID;


/**
 * 
 * @author xmm
 * @version  2016-09-14 22:16:43
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseEndController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static final String CURRENT_PAGE = "user/";

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;


	/**
	 * 列表页面
	 * @return
	 */
	@RequiresPermissions("system.user.list")
	@RequestMapping(value = "/list.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String list(@ModelAttribute UserEntity query, HttpServletRequest request, HttpServletResponse response,
					 @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
					 @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,ModelMap model) throws Exception{
		//用了分页组件
		PageHelper.startPage(pageNo, pageSize);
		UserEntity user = new UserEntity();
		/**
		 * 页面会转成空字符串，这里转成null，待后续想其他办法，这里加上转换，性能肯定有影响了
		 */
		BeanUtil.copyBean2Bean(user,query);
		List<UserEntity> list = userService.list(user);
		logger.info("list : {}",list);
		model.addAttribute("query",query);
		PageInfo page = new PageInfo(list);
		//传入分页所需的参数
		EndUtil.sendPageParams(request,model,page);
		//传入固定的参数
		EndUtil.sendEndParams(request,model);
		return END_PAGE + CURRENT_PAGE + LIST;
	}

	/**
	 * 新增页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system.user.add")
	@RequestMapping(value = "/toAdd.do",method ={RequestMethod.GET, RequestMethod.POST})
	public String toAdd(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		EndUtil.sendEndParams(request,model);
		List<RoleEntity> roleList = roleService.selectAll();
		model.addAttribute("roleList",roleList);
		return END_PAGE + CURRENT_PAGE + ADD;
	}

	/**
	 * 修改页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system.user.edit")
	@RequestMapping(value = "/toEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
	public String toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		EndUtil.sendEndParams(request,model);
		UserEntity user = userService.selectByPrimaryKey(id);
		model.addAttribute("user",user);
		List<RoleEntity> roleList = roleService.selectAll();
		model.addAttribute("roleList",roleList);
		return END_PAGE + CURRENT_PAGE + EDIT;
	}

	/**
	 * 修改页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system.user.detail")
	@RequestMapping(value = "/toDetail.do",method ={RequestMethod.GET, RequestMethod.POST})
	public String toDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		EndUtil.sendEndParams(request,model);
		UserEntity user = userService.selectByPrimaryKey(id);
		model.addAttribute("user",user);
		return END_PAGE + CURRENT_PAGE + DETAIL;
	}


	@RequiresPermissions("system.user.password")
	@RequestMapping(value = "/toPassword.do",method ={RequestMethod.GET, RequestMethod.POST})
	public String toPassword(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		EndUtil.sendEndParams(request,model);
		UserEntity user = userService.selectByPrimaryKey(id);
		model.addAttribute("user",user);
		return END_PAGE + CURRENT_PAGE + "password";
	}

	/**
	 * 保存方法
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/doAdd.do",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute("user") UserEntity user,@RequestParam(required = true, value = "confirmPassword" ) String confirmPassword){
		AjaxJson j = new AjaxJson();
		try {
            if (Tools.isEmpty(user.getPassword())) {
                j.setMsg("密码不能为空！");
                j.setSuccess(false);
                return j;
            }
            if (Tools.isEmpty(confirmPassword)) {
                j.setMsg("确认密码不能为空！");
                j.setSuccess(false);
                return j;
            }
            if (!user.getPassword().equals(confirmPassword)) {
                j.setMsg("两次输入的密码不相等！");
                j.setSuccess(false);
                return j;
            }
			user.setId(Tools.get32UUID());
			userService.saveUser(user);
			j.setMsg("保存成功");
		} catch (Exception e) {
			logger.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}

	/**
	 * 更新方法
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/doEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute("user") UserEntity user){
		AjaxJson j = new AjaxJson();
		try {
			int i = userService.updateByPrimaryKey(user);
			logger.info("i : ---- {}",i);
			if (i > 0) {
				j.setMsg("更新成功");
			} else {
				j.setMsg("更新失败");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("更新失败");
		}
		return j;
	}

	@RequestMapping(value = "/doDelete.do",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doDelete(@RequestParam(required = true, value = "id")String id){
		AjaxJson j = new AjaxJson();
		try {
			int i= userService.delete(id);
			if (i > 0) {
				j.setMsg("删除成功");
			} else {
				j.setMsg("删除失败");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value = "/doPassword.do",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doPassword(@RequestParam(required = true, value = "id" ) String id,
							   @RequestParam(required = true, value = "password" ) String password,
							   @RequestParam(required = true, value = "newPassword" ) String newPassword,
							   @RequestParam(required = true, value = "confirmPassword" ) String confirmPassword){
		AjaxJson j = new AjaxJson();
		try {
			UserEntity user =  userService.selectByPrimaryKey(id);
			if (Tools.isEmpty(password)) {
				j.setMsg("旧密码不能为空");
				j.setSuccess(false);
				return j;
			}
			if (!user.getPassword().equals(new Md5Hash(password).toString())){
				j.setMsg("旧密码有误，无法修改");
				j.setSuccess(false);
				return j;
			}
			if (Tools.isEmpty(newPassword)) {
				j.setMsg("新密码不能为空");
				j.setSuccess(false);
				return j;
			}
			if (Tools.isEmpty(confirmPassword)) {
				j.setMsg("确认密码不能为空");
				j.setSuccess(false);
				return j;
			}
			if (!newPassword.equals(confirmPassword)) {
				j.setMsg("两次密码输入不一致，不能修改");
				j.setSuccess(false);
				return j;
			}
			userService.update(id,newPassword);
			j.setMsg("修改密码成功");
		} catch (Exception e) {
			logger.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("修改密码失败");
		}
		return j;
	}
}
