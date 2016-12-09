package com.xmmxjy.cms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmmxjy.cms.entity.ChannelEntity;
import com.xmmxjy.cms.service.ChannelService;
import com.xmmxjy.common.controller.BaseEndController;
import com.xmmxjy.common.util.AjaxJson;
import com.xmmxjy.common.util.BeanUtil;
import com.xmmxjy.common.util.EndUtil;
import com.xmmxjy.system.Constants;
import com.xmmxjy.system.shiro.ShiroSessionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
*
* @author xmm
* @version :1.0
*/
@Controller
@RequestMapping(value="/channel")
public class ChannelController extends BaseEndController {

private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);

private static final String CURRENT_PAGE = "channel/";

@Autowired
private ChannelService channelService;


/**
* 列表页面
* @return
*/
@RequiresPermissions("cms.channel.list")
@RequestMapping(value = "/list.do",method = {RequestMethod.GET,RequestMethod.POST})
public String list(@ModelAttribute ChannelEntity query, HttpServletRequest request, HttpServletResponse response,
@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,ModelMap model) throws Exception{
//用了分页组件
    PageHelper.startPage(pageNo, pageSize);
    ChannelEntity channel = new ChannelEntity();
    /**
    * 页面会转成空字符串，这里转成null，待后续想其他办法，这里加上转换，性能肯定有影响了
    */
    BeanUtil.copyBean2Bean(channel,query);
    List<ChannelEntity> list = channelService.select(channel);
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
    @RequiresPermissions("cms.channel.add")
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
    @RequiresPermissions("cms.channel.edit")
    @RequestMapping(value = "/toEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toEdit(@RequestParam(required = true, value = "id" ) Integer id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
    EndUtil.sendEndParams(request,model);
    ChannelEntity channel = channelService.selectByPrimaryKey(id);
        model.addAttribute("channel",channel);
        return END_PAGE + CURRENT_PAGE + EDIT;
    }

    /**
    * 详情页面
    * @param request
    * @param response
    * @param model
    * @return
    */
    @RequiresPermissions("cms.channel.detail")
    @RequestMapping(value = "/toDetail.do",method ={RequestMethod.GET, RequestMethod.POST})
    public String toDetail(@RequestParam(required = true, value = "id" ) Integer id,HttpServletRequest request,HttpServletResponse response,ModelMap model){
        EndUtil.sendEndParams(request,model);
        ChannelEntity channel = channelService.selectByPrimaryKey(id);
        model.addAttribute("channel",channel);
        return END_PAGE + CURRENT_PAGE + DETAIL;
    }




    /**
    * 保存方法
    * @param channel
    * @return
    */
    @RequiresPermissions("cms.channel.add")
    @RequestMapping(value = "/doAdd.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doAdd(@ModelAttribute("channel") ChannelEntity channel){
        AjaxJson j = new AjaxJson();
        try {
            channelService.save(channel);
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
    * @param channel
    * @return
    */
    @RequiresPermissions("cms.channel.edit")
    @RequestMapping(value = "/doEdit.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doEdit(@ModelAttribute("channel") ChannelEntity channel){
    AjaxJson j = new AjaxJson();
        try {
        int i = channelService.updateByPrimaryKey(channel);
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

    @RequiresPermissions("cms.channel.delete")
    @RequestMapping(value = "/doDelete.do",method ={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson doDelete(@RequestParam(required = true, value = "id")Integer id){
    AjaxJson j = new AjaxJson();
    try {
        int i= channelService.delete(id);
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
    public List<ChannelEntity> tree() {
        logger.info("请求树形");
        List<ChannelEntity> channelList = channelService.selectAll();
        return channelList;
    }

    @RequestMapping(value = "/channelList.do")
    @ResponseBody
    public Map<String,Object> listById(Integer id) {
        logger.info("请求树形");
        List<ChannelEntity> channelList = channelService.selectByParentId(id);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("channelList",channelList);
        Set<String> permissions = (Set<String>)ShiroSessionUtils.getAttribute(Constants.SHIRO_PERMISSIONS);
        if (permissions.contains("cms.channel.add")) {
            map.put("add",true);
        }
        if (permissions.contains("cms.channel.edit")) {
            map.put("edit",true);
        }
        if (permissions.contains("cms.channel.detail")) {
            map.put("detail",true);
        }
        if (permissions.contains("cms.channel.delete")) {
            map.put("delete",true);
        }
        return map;
    }

}
