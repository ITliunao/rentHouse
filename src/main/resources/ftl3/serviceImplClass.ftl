package com.xmmxjy.${package}.service.impl;

import com.xmmxjy.common.service.impl.BaseServiceImpl;
import com.xmmxjy.${package}.dao.${className}Dao;
import com.xmmxjy.${package}.entity.${className}Entity;
import com.xmmxjy.${package}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author: xmm
* @version:1.0
*/

@Service("${lowerName}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${className}Entity> implements ${className}Service {
    @Autowired
    private ${className}Dao ${lowerName}Dao;

}
