package com.xmmxjy.system.service.impl;

import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.system.dao.DictDataDao;
import com.xmmxjy.system.entity.DictDataEntity;
import com.xmmxjy.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author: xmm
* @version:1.0
*/

@Service("dictDataService")
public class DictDataServiceImpl extends BaseServiceImpl<DictDataEntity> implements DictDataService {
    @Autowired
    private DictDataDao dictDataDao;

}
