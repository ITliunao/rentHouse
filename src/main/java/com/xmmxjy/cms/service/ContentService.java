package com.xmmxjy.cms.service;

import com.xmmxjy.cms.entity.ContentAttributeEntity;
import com.xmmxjy.common.service.BaseService;
import com.xmmxjy.cms.entity.ContentEntity;

/**
* @author: xmm
* @version:1.0
*/
public interface ContentService extends BaseService<ContentEntity>{


    void save(ContentEntity content, ContentAttributeEntity text);

    int update(ContentEntity content, ContentAttributeEntity contentAttribute);
}
