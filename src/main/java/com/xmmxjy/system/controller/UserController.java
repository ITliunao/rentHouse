package com.xmmxjy.system.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmmxjy.common.controller.BaseEndController;
import com.xmmxjy.common.util.BeanUtil;
import com.xmmxjy.common.util.EndUtil;
import com.xmmxjy.system.entity.UserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.xmmxjy.system.service.UserService;

import java.util.List;


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

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String save(Model model) {
		return "";
	}


	/**
	 * 列表页面
	 * @return
	 */
	@RequiresPermissions("system.user.list")
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public String list(@ModelAttribute UserEntity query, HttpServletRequest request, HttpServletResponse response,
					 @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
					 @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,ModelMap model) throws Exception{
		PageHelper.startPage(pageNo, pageSize);
		/**
		 * 页面会转成空字符串，这里转成null
		 */
		UserEntity user = new UserEntity();
		BeanUtil.copyBean2Bean(user,query);
		List<UserEntity> list = userService.list(user);
		logger.info("list : {}",list);
		model.addAttribute("query",query);
		PageInfo page = new PageInfo(list);
		EndUtil.sendPageParams(request,model,page);
		EndUtil.sendEndParams(request,model);
		return END_PAGE + CURRENT_PAGE + LIST;
	}


}
