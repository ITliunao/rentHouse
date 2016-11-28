package com.xmmxjy.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmmxjy.common.controller.BaseEndController;
import com.xmmxjy.common.util.*;
import com.xmmxjy.system.entity.DepartEntity;
import com.xmmxjy.system.service.DepartService;
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
 * Created by xmm on 16-11-27.
 */
@Controller
@RequestMapping(value="/depart")
public class DepartController extends BaseEndController{
    private static final Logger logger = LoggerFactory.getLogger(DepartController.class);

    private static final String CURRENT_PAGE = "depart/";

    @Autowired
    private DepartService departService;


    /**
     * 列表页面
     * @return
     */
    @RequiresPermissions("system.depart.list")
    @RequestMapping(value = "/list.do",method = {RequestMethod.GET,RequestMethod.POST})
    public String list(@ModelAttribute DepartEntity query, HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize, ModelMap model) throws Exception{
        //用了分页组件
        PageHelper.startPage(pageNo, pageSize);
        DepartEntity depart = new DepartEntity();
        /**
         * 页面会转成空字符串，这里转成null，待后续想其他办法，这里加上转换，性能肯定有影响了
         */
        BeanUtil.copyBean2Bean(depart,query);
        List<DepartEntity> list = departService.select(depart);
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
    @RequiresPermissions("system.depart.add")
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
    @RequiresPermissions("system.depart.edit")
    @RequestMapping(value = "/toEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
        EndUtil.sendEndParams(request,model);
        DepartEntity depart = departService.selectByPrimaryKey(id);
        model.addAttribute("depart",depart);
        return END_PAGE + CURRENT_PAGE + EDIT;
    }

    /**
     * 修改页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("system.depart.detail")
    @RequestMapping(value = "/toDetail.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
        EndUtil.sendEndParams(request,model);
        DepartEntity depart = departService.selectByPrimaryKey(id);
        DepartEntity parentDepartEntity = null;
        if (Tools.notEmpty(depart.getParentDepartId())) {
            parentDepartEntity = departService.selectByPrimaryKey(depart.getParentDepartId());
            model.addAttribute("parentDepartName",parentDepartEntity.getDepartName());
        } else {
            model.addAttribute("parentDepartName","");
        }
        model.addAttribute("depart",depart);
        return END_PAGE + CURRENT_PAGE + DETAIL;
    }


    /**
     * 保存方法
     * @param depart
     * @return
     */
    @RequestMapping(value = "/doAdd.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doAdd(@ModelAttribute("depart") DepartEntity depart){
        AjaxJson j = new AjaxJson();
        try {
            if (Tools.isEmpty(depart.getParentDepartId())) {//无上级
                depart.setId(new SimpleTreeIdBuild().getId(departService,null));
                depart.setParentDepartId(null);
            }else{//有上级
                depart.setId(new SimpleTreeIdBuild().getId(this.departService,depart.getParentDepartId()));
            }
            departService.save(depart);
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
     * @param depart
     * @return
     */
    @RequestMapping(value = "/doEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doEdit(@ModelAttribute("depart") DepartEntity depart){
        AjaxJson j = new AjaxJson();
        try {
            int i = departService.updateByPrimaryKey(depart);
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
            int i = departService.delete(id);
            logger.info("删除depart --- i : ---- {}",i);
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
    @RequestMapping(value = "/tree.do")
    @ResponseBody
    public List<DepartEntity> tree() {
        logger.info("请求树形");
        List<DepartEntity> departList = departService.selectAll();
        return departList;
    }
}
