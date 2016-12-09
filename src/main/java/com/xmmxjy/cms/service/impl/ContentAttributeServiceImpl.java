package com.xmmxjy.cms.service.impl;

import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.cms.dao.ContentAttributeDao;
import com.xmmxjy.cms.entity.ContentAttributeEntity;
import com.xmmxjy.cms.service.ContentAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author: xmm
* @version:1.0
*/

@Service("contentAttributeService")
public class ContentAttributeServiceImpl extends BaseServiceImpl<ContentAttributeEntity> implements ContentAttributeService {
    @Autowired
    private ContentAttributeDao contentAttributeDao;

}
