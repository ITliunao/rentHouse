package com.xmmxjy.cms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmmxjy.common.controller.BaseEndController;
import com.xmmxjy.common.util.AjaxJson;
import com.xmmxjy.common.util.BeanUtil;
import com.xmmxjy.common.util.EndUtil;
import com.xmmxjy.common.util.Tools;
import com.xmmxjy.cms.entity.ContentEntity;
import com.xmmxjy.cms.service.ContentService;
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
@RequestMapping(value="/content")
public class ContentController extends BaseEndController {

private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

private static final String CURRENT_PAGE = "content/";

@Autowired
private ContentService contentService;


/**
* 列表页面
* @return
*/
@RequiresPermissions("cms.content.list")
@RequestMapping(value = "/list.do",method = {RequestMethod.GET,RequestMethod.POST})
public String list(@ModelAttribute ContentEntity query, HttpServletRequest request, HttpServletResponse response,
@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,ModelMap model) throws Exception{
//用了分页组件
    PageHelper.startPage(pageNo, pageSize);
    ContentEntity content = new ContentEntity();
    /**
    * 页面会转成空字符串，这里转成null，待后续想其他办法，这里加上转换，性能肯定有影响了
    */
    BeanUtil.copyBean2Bean(content,query);
List<ContentEntity> list = contentService.select(content);
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
    @RequiresPermissions("cms.content.add")
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
    @RequiresPermissions("cms.content.edit")
    @RequestMapping(value = "/toEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toEdit(@RequestParam(required = true, value = "id" ) Integer id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
    EndUtil.sendEndParams(request,model);
        ContentEntity content = contentService.selectByPrimaryKey(id);
        model.addAttribute("content",content);
        return END_PAGE + CURRENT_PAGE + EDIT;
    }

    /**
    * 详情页面
    * @param request
    * @param response
    * @param model
    * @return
    */
    @RequiresPermissions("cms.content.detail")
    @RequestMapping(value = "/toDetail.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toDetail(@RequestParam(required = true, value = "id" ) Integer id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
        EndUtil.sendEndParams(request,model);
        ContentEntity content = contentService.selectByPrimaryKey(id);
        model.addAttribute("content",content);
        return END_PAGE + CURRENT_PAGE + DETAIL;
    }




    /**
    * 保存方法
    * @param content
    * @return
    */
    @RequiresPermissions("cms.content.add")
    @RequestMapping(value = "/doAdd.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doAdd(@ModelAttribute("content") ContentEntity content,HttpServletRequest request){
        AjaxJson j = new AjaxJson();
        String text = request.getParameter("text");

        try {
            contentService.save(content,text);
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
    * @param content
    * @return
    */
    @RequiresPermissions("cms.content.edit")
    @RequestMapping(value = "/doEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doEdit(@ModelAttribute("content") ContentEntity content){
    AjaxJson j = new AjaxJson();
        try {
        int i = contentService.updateByPrimaryKey(content);
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

    @RequiresPermissions("cms.content.delete")
    @RequestMapping(value = "/doDelete.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doDelete(@RequestParam(required = true, value = "id")Integer id){
    AjaxJson j = new AjaxJson();
    try {
        int i= contentService.delete(id);
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
