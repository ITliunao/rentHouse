package com.xmmxjy.cms.service.impl;

import com.xmmxjy.cms.dao.ContentAttributeDao;
import com.xmmxjy.cms.dao.ContentDao;
import com.xmmxjy.cms.entity.ContentAttributeEntity;
import com.xmmxjy.cms.entity.ContentEntity;
import com.xmmxjy.cms.service.ContentService;
import com.xmmxjy.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author: xmm
* @version:1.0
*/

@Service("contentService")
public class ContentServiceImpl extends BaseServiceImpl<ContentEntity> implements ContentService {

    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private ContentAttributeDao contentAttributeDao;
    @Override
    public void save(ContentEntity content, ContentAttributeEntity attribute) {
      // 插入啊
      int i =  contentDao.insertUseGeneratedKeys(content);
      if (i > 0) {
          attribute.setId(content.getId());
          contentAttributeDao.insert(attribute);
      }
      logger.info("content : {}",content);
    }

    @Override
    public int update(ContentEntity content, ContentAttributeEntity contentAttribute) {
        int i = contentDao.updateByPrimaryKey(content);
        int j = contentAttributeDao.updateByPrimaryKey(contentAttribute);
        return j;
    }
    @Override
    public int delete(Object id) {
        int i = this.contentDao.deleteByPrimaryKey(id);
        int j = this.contentAttributeDao.deleteByPrimaryKey(id);
        return j;
    }

}
