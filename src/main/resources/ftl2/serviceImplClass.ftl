package ${serviceImplPackage};

import com.xmmxjy.common.service.impl.BaseServiceImpl;
import ${daoPackage}.${className}Dao;
import ${domainPackage}.${className}Entity;
import ${servicePackage}.${className}Service;
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
