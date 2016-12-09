package com.xmmxjy.cms.service;

import com.xmmxjy.common.service.BaseService;
import com.xmmxjy.cms.entity.ChannelEntity;

import java.util.List;

/**
* @author: xmm
* @version:1.0
*/
public interface ChannelService extends BaseService<ChannelEntity>{

    /**
     * 查询所有的子栏目
     * @param id
     * @return
     */
    List<ChannelEntity> selectByParentId(Integer id);
}
