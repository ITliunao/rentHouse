
package com.xmmxjy.common.service.impl;

import com.xmmxjy.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by liuzh on 2014/12/11.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;

    @Override
    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    public int save(T entity) {
        return mapper.insert(entity);
    }

    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    public int updateByPrimaryKey(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }



    //TODO 其他...
}
