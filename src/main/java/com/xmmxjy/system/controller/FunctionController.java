package com.xmmxjy.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmmxjy.common.controller.BaseEndController;
import com.xmmxjy.common.util.*;
import com.xmmxjy.system.entity.FunctionEntity;
import com.xmmxjy.system.service.FunctionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;
import java.util.List;

/**
 * Created by xmm on 16-11-27.
 */
@Controller
@RequestMapping(value="/function")
public class FunctionController extends BaseEndController{
    private static final Logger logger = LoggerFactory.getLogger(FunctionController.class);

    private static final String CURRENT_PAGE = "function/";

    @Autowired
    private FunctionService functionService;


    /**
     * 列表页面
     * @return
     */
    @RequiresPermissions("system.function.list")
    @RequestMapping(value = "/list.do",method = {RequestMethod.GET,RequestMethod.POST})
    public String list(@ModelAttribute FunctionEntity query, HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
                       @RequestParam(required = false, value = "pageSize", defaultValue = "50") int pageSize, ModelMap model) throws Exception{
        //用了分页组件
        PageHelper.startPage(pageNo, pageSize);
        FunctionEntity function = new FunctionEntity();
        /**
         * 页面会转成空字符串，这里转成null，待后续想其他办法，这里加上转换，性能肯定有影响了
         */
        BeanUtil.copyBean2Bean(function,query);
        List<FunctionEntity> list = functionService.list(function);
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
    @RequiresPermissions("system.function.add")
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
    @RequiresPermissions("system.function.edit")
    @RequestMapping(value = "/toEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
        EndUtil.sendEndParams(request,model);
        FunctionEntity function = functionService.selectByPrimaryKey(id);
        model.addAttribute("function",function);
        return END_PAGE + CURRENT_PAGE + EDIT;
    }

    /**
     * 修改页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("system.function.detail")
    @RequestMapping(value = "/toDetail.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
        EndUtil.sendEndParams(request,model);
        FunctionEntity function = functionService.selectByPrimaryKey(id);
        FunctionEntity parentFunctionEntity = null;
        if (Tools.notEmpty(function.getParentFunctionId())) {
            parentFunctionEntity = functionService.selectByPrimaryKey(function.getParentFunctionId());
            model.addAttribute("parentFunctionName",parentFunctionEntity.getFunctionname());
        } else {
            model.addAttribute("parentFunctionName","");
        }
        model.addAttribute("function",function);
        return END_PAGE + CURRENT_PAGE + DETAIL;
    }


    /**
     * 保存方法
     * @param function
     * @return
     */
    @RequestMapping(value = "/doAdd.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doAdd(@ModelAttribute("function") FunctionEntity function){
        AjaxJson j = new AjaxJson();
        try {
            if (Tools.isEmpty(function.getParentFunctionId())) {//无上级
                function.setId(new SimpleTreeIdBuild().getId(this.functionService,null));
                function.setParentFunctionId(null);
            }else{//有上级
                function.setId(new SimpleTreeIdBuild().getId(this.functionService,function.getParentFunctionId()));
            }
            functionService.save(function);
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
     * @param function
     * @return
     */
    @RequestMapping(value = "/doEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doEdit(@ModelAttribute("function") FunctionEntity function){
        AjaxJson j = new AjaxJson();
        try {
            int i = functionService.updateByPrimaryKey(function);
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
            int i = functionService.delete(id);
            logger.info("删除function --- i : ---- {}",i);
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
    public List<FunctionEntity> tree() {
        logger.info("请求树形");
        List<FunctionEntity> functionList = functionService.selectAll();
        return functionList;
    }
}
