package com.xmmxjy.system.controller;

import com.xmmxjy.common.util.EndUtil;
import com.xmmxjy.system.Constants;
import com.xmmxjy.system.entity.FunctionEntity;
import com.xmmxjy.system.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 16-9-28.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        EndUtil.sendEndParams(request,model);
        List<FunctionEntity> functionList = functionService.list(Constants.FUNCTION_LEVEL_1);
        List<FunctionEntity> subFunctionList = functionService.list(Constants.FUNCTION_LEVEL_2);
        model.addAttribute("functionList",functionList);
        model.addAttribute("subFunctionList",subFunctionList);
        return "end/index";
    }

    /**
     * 跳转到欢迎页
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/home.do",method = {RequestMethod.GET,RequestMethod.POST})
    public String home(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
        EndUtil.sendEndParams(request,model);
        return "end/include/home";
    }

    @Autowired
    private FunctionService functionService;

}
