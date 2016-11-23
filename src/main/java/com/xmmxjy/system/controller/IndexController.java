package com.xmmxjy.system.controller;

import com.xmmxjy.common.util.EndUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 16-9-28.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        EndUtil.sendEndParams(request,model);

        return "end/index";
    }

    /**
     * 跳转到欢迎页
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "home",method = {RequestMethod.GET,RequestMethod.POST})
    public String home(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
        return "end/include/home";
    }
}
