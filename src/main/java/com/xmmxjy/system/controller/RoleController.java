package com.xmmxjy.system.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmmxjy.common.controller.BaseEndController;
import com.xmmxjy.common.util.AjaxJson;
import com.xmmxjy.common.util.BeanUtil;
import com.xmmxjy.common.util.EndUtil;
import com.xmmxjy.common.util.Tools;
import com.xmmxjy.system.entity.RoleEntity;
import com.xmmxjy.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 
 * @author xmm
 * @version  2016-09-14 22:16:43
 */
@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseEndController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	private static final String CURRENT_PAGE = "role/";

	@Autowired
	private RoleService roleService;


	/**
	 * 列表页面
	 * @return
	 */
	@RequiresPermissions("system.role.list")
	@RequestMapping(value = "/list.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String list(@ModelAttribute RoleEntity query, HttpServletRequest request, HttpServletResponse response,
					 @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
					 @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,ModelMap model) throws Exception{
		//用了分页组件
		PageHelper.startPage(pageNo, pageSize);
		RoleEntity role = new RoleEntity();
		/**
		 * 页面会转成空字符串，这里转成null，待后续想其他办法，这里加上转换，性能肯定有影响了
		 */
		BeanUtil.copyBean2Bean(role,query);
		List<RoleEntity> list = roleService.select(role);
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
	@RequiresPermissions("system.role.add")
	@RequestMapping(value = "/toAdd.do",method ={RequestMethod.GET, RequestMethod.POST})
	public String toAdd(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		EndUtil.sendEndParams(request,model);
		return END_PAGE + CURRENT_PAGE + ADD;
	}

	/**
	 * 修改页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system.role.edit")
	@RequestMapping(value = "/toEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
	public String toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		EndUtil.sendEndParams(request,model);
		RoleEntity role = roleService.selectByPrimaryKey(id);
		model.addAttribute("role",role);
		return END_PAGE + CURRENT_PAGE + EDIT;
	}

	/**
	 * 详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system.role.detail")
	@RequestMapping(value = "/toDetail.do",method ={RequestMethod.GET, RequestMethod.POST})
	public String toDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		EndUtil.sendEndParams(request,model);
		RoleEntity role = roleService.selectByPrimaryKey(id);
		model.addAttribute("role",role);
		return END_PAGE + CURRENT_PAGE + DETAIL;
	}


	/**
	 * 分配权限页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("system.role.assign")
	@RequestMapping(value = "/toAssign.do",method ={RequestMethod.GET, RequestMethod.POST})
	public String toAssign(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		EndUtil.sendEndParams(request,model);
		RoleEntity role = roleService.selectByPrimaryKey(id);
		model.addAttribute("role",role);
		List<String> permissionList = roleService.getPermissionList(id);
		model.addAttribute("permissions",Tools.listToString(permissionList));
		model.addAttribute("permissionList",permissionList);
		return END_PAGE + CURRENT_PAGE + "assign";
	}

	/**
	 * 保存方法
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/doAdd.do",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute("role") RoleEntity role){
		AjaxJson j = new AjaxJson();
		try {
			role.setId(Tools.get32UUID());
			roleService.save(role);
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
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/doEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute("role") RoleEntity role){
		AjaxJson j = new AjaxJson();
		try {
			int i = roleService.updateByPrimaryKey(role);
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

	/**
	 * 分配权限
	 * @return
	 */
	@RequestMapping(value = "/doAssign.do",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAssign(@RequestParam(required = true, value = "ids" ) String ids){
		AjaxJson j = new AjaxJson();
		try {
			logger.info("ids : {}",ids);
			j.setMsg("分配成功");
		} catch (Exception e) {
			logger.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("分配失败");
		}
		return j;
	}

	@RequestMapping(value = "/doDelete.do",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doDelete(@RequestParam(required = true, value = "id")String id){
		AjaxJson j = new AjaxJson();
		try {
			int i= roleService.delete(id);
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


}
