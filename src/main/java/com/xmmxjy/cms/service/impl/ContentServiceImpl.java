package com.xmmxjy.cms.service.impl;

import com.xmmxjy.cms.dao.ContentAttributeDao;
import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.cms.dao.ContentDao;
import com.xmmxjy.cms.entity.ContentEntity;
import com.xmmxjy.cms.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author: xmm
* @version:1.0
*/

@Service("contentService")
public class ContentServiceImpl extends BaseServiceImpl<ContentEntity> implements ContentService {
    @Autowired
    private ContentDao contentDao;

    @Autowired
    private ContentAttributeDao contentAttributeDao;
    @Override
    public void save(ContentEntity content, String text) {
      // 插入啊
      long l =  contentDao.saveContent(content);
    }
}
