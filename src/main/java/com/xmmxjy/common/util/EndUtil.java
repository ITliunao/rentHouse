package com.xmmxjy.common.util;

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
}
