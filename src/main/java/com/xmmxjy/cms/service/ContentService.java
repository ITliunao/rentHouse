package com.xmmxjy.cms.service;

import com.xmmxjy.common.service.BaseService;
import com.xmmxjy.cms.entity.ContentEntity;

import java.util.List;

/**
* @author: xmm
* @version:1.0
*/
public interface ContentService extends BaseService<ContentEntity>{


    void save(ContentEntity content, String text);
}
