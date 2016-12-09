package com.xmmxjy.cms.service.impl;

import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.cms.dao.ChannelDao;
import com.xmmxjy.cms.entity.ChannelEntity;
import com.xmmxjy.cms.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author: xmm
* @version:1.0
*/

@Service("channelService")
public class ChannelServiceImpl extends BaseServiceImpl<ChannelEntity> implements ChannelService {
    @Autowired
    private ChannelDao channelDao;


    @Override
    public List<ChannelEntity> selectByParentId(Integer id) {
        return channelDao.selectByParentId(id);
    }
}
