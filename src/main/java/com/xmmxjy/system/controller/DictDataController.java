package com.xmmxjy.system.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmmxjy.common.controller.BaseEndController;
import com.xmmxjy.common.util.AjaxJson;
import com.xmmxjy.common.util.BeanUtil;
import com.xmmxjy.common.util.EndUtil;
import com.xmmxjy.common.util.Tools;
import com.xmmxjy.system.entity.DictDataEntity;
import com.xmmxjy.system.service.DictDataService;
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
* @version :1.0
*/
@Controller
@RequestMapping(value="/dictData")
public class DictDataController extends BaseEndController {

private static final Logger logger = LoggerFactory.getLogger(DictDataController.class);

private static final String CURRENT_PAGE = "dictData/";

@Autowired
private DictDataService dictDataService;


/**
* 列表页面
* @return
*/
@RequiresPermissions("system.dictData.list")
@RequestMapping(value = "/list.do",method = {RequestMethod.GET,RequestMethod.POST})
public String list(@ModelAttribute DictDataEntity query, HttpServletRequest request, HttpServletResponse response,
@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,ModelMap model) throws Exception{
//用了分页组件
    PageHelper.startPage(pageNo, pageSize);
    DictDataEntity dictData = new DictDataEntity();
    /**
    * 页面会转成空字符串，这里转成null，待后续想其他办法，这里加上转换，性能肯定有影响了
    */
    BeanUtil.copyBean2Bean(dictData,query);
List<DictDataEntity> list = dictDataService.select(dictData);
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
    @RequiresPermissions("system.dictData.add")
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
    @RequiresPermissions("system.dictData.edit")
    @RequestMapping(value = "/toEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
    EndUtil.sendEndParams(request,model);
    DictDataEntity dictData = dictDataService.selectByPrimaryKey(id);
        model.addAttribute("dictData",dictData);
        return END_PAGE + CURRENT_PAGE + EDIT;
    }

    /**
    * 详情页面
    * @param request
    * @param response
    * @param model
    * @return
    */
    @RequiresPermissions("system.dictData.detail")
    @RequestMapping(value = "/toDetail.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
        EndUtil.sendEndParams(request,model);
        DictDataEntity dictData = dictDataService.selectByPrimaryKey(id);
        model.addAttribute("dictData",dictData);
        return END_PAGE + CURRENT_PAGE + DETAIL;
    }




    /**
    * 保存方法
    * @param dictData
    * @return
    */
    @RequiresPermissions("system.dictData.add")
    @RequestMapping(value = "/doAdd.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doAdd(@ModelAttribute("dictData") DictDataEntity dictData){
        AjaxJson j = new AjaxJson();
        try {
            dictData.setId(Tools.get32UUID());
            dictDataService.save(dictData);
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
    * @param dictData
    * @return
    */
    @RequiresPermissions("system.dictData.edit")
    @RequestMapping(value = "/doEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doEdit(@ModelAttribute("dictData") DictDataEntity dictData){
    AjaxJson j = new AjaxJson();
        try {
        int i = dictDataService.updateByPrimaryKey(dictData);
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

    @RequiresPermissions("system.dictData.delete")
    @RequestMapping(value = "/doDelete.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doDelete(@RequestParam(required = true, value = "id")String id){
    AjaxJson j = new AjaxJson();
    try {
        int i= dictDataService.delete(id);
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
