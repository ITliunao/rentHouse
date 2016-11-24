package com.xmmxjy.common.util;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xmm
 * Created by Administrator on 16-11-22.
 */
public class EndUtil {

    private final static Logger logger = LoggerFactory.getLogger(EndUtil.class);

    /**
     * 发送公共参数给后台页面
     * @param request
     * @param model
     */
    public static void sendEndParams(HttpServletRequest request, ModelMap model) {
        String basePath = request.getContextPath();
        logger.info("basePath : {}",basePath);
        model.addAttribute("basePath",basePath);
    }

    /**
     * 发送分页参数
     * @param request
     * @param model
     * @param page
     */
    public static void sendPageParams(HttpServletRequest request, ModelMap model, PageInfo page) {
        logger.info("page : {}",page);
        model.addAttribute("total",page.getTotal());
        model.addAttribute("list",page.getList());
        model.addAttribute("pageNo",page.getPageNum());
        model.addAttribute("pageSize",page.getPageSize());
        model.addAttribute("pages",page.getPages());
    }
}
