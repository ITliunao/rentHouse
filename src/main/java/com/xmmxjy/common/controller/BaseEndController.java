package com.xmmxjy.common.controller;

import com.xmmxjy.common.util.DateConvertEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

/**
 * Created by Administrator on 16-11-23.
 */
public class BaseEndController {

    protected final static String END_PAGE = "end/";

    protected final static String LIST = "list";

    protected final static String ADD = "add";

    protected final static String EDIT = "edit";

    protected final static String DETAIL = "detail";

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateConvertEditor());
    }
}
