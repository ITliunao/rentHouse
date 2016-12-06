package com.xmmxjy.generator.factory;


import com.xmmxjy.generator.CommonPageParser;
import com.xmmxjy.generator.CreateBean;
import com.xmmxjy.generator.def.CodeResourceUtil;
import com.xmmxjy.generator.def.FreemarkerEngine;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerateFactory {
    private static final Log log = LogFactory.getLog(CodeGenerateFactory.class);
    private static String url;
    private static String username;
    private static String passWord;
    private static String buss_package;

    static {
        url = CodeResourceUtil.URL;
        username = CodeResourceUtil.USERNAME;
        passWord = CodeResourceUtil.PASSWORD;
        buss_package = CodeResourceUtil.bussiPackage;
    }

    public CodeGenerateFactory() {
    }

    public static void codeGenerateByVM(String tableName, String codeName, String keyType) {
        try {
            CreateBean e1 = new CreateBean();
            e1.setMysqlInfo(url, username, passWord);
            String className = e1.getTablesNameToClassName(tableName);
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            String tableNameUpper = tableName.toUpperCase();
            String tablesAsName = e1.getTablesASName(tableName);
            if(StringUtils.isEmpty(codeName)) {
                Map sqlmapPathSrc = e1.getTableCommentMap();
                codeName = (String)sqlmapPathSrc.get(tableNameUpper);
            }

            String sqlmapPathSrc1 = CodeResourceUtil.getConfigInfo("sqlmap_path_src");
            String domainPathSrc = CodeResourceUtil.getConfigInfo("domain_path_src");
            String daoPathSrc = CodeResourceUtil.getConfigInfo("dao_path_src");
            String daoImplPathSrc = CodeResourceUtil.getConfigInfo("dao_impl_path_src");
            String managerPathSrc = CodeResourceUtil.getConfigInfo("manager_path_src");
            String managerImplPathSrc = CodeResourceUtil.getConfigInfo("manager_impl_path_src");
            String servicePathSrc = CodeResourceUtil.getConfigInfo("service_path_src");
            String serviceImplPathSrc = CodeResourceUtil.getConfigInfo("service_impl_path_src");
            String sqlmapPackage = CodeResourceUtil.getConfigInfo("sqlmap_path");
            String domainPackage = CodeResourceUtil.getConfigInfo("domain_path");
            String daoPackage = CodeResourceUtil.getConfigInfo("dao_path");
            String daoImplPackage = CodeResourceUtil.getConfigInfo("dao_impl_path");
            String managerPackage = CodeResourceUtil.getConfigInfo("manager_path");
            String managerImplPackage = CodeResourceUtil.getConfigInfo("manager_impl_path");
            String servicePackage = CodeResourceUtil.getConfigInfo("service_path");
            String serviceImplPackage = CodeResourceUtil.getConfigInfo("service_impl_path");
            String sqlMapperPath = sqlmapPackage.replace(".", "\\") + "\\" + tableNameUpper + ".xml";
            String domainPath = domainPackage.replace(".", "\\") + "\\" + className + ".java";
            String daoPath = daoPackage.replace(".", "\\") + "\\" + className + "Dao.java";
            String daoImplPath = daoImplPackage.replace(".", "\\") + "\\" + className + "DaoImpl.java";
            String managerPath = managerPackage.replace(".", "\\") + "\\" + className + "Manager.java";
            String managerImplPath = managerImplPackage.replace(".", "\\") + "\\" + className + "ManagerImpl.java";
            String servicePath = servicePackage.replace(".", "\\") + "\\" + className + "Service.java";
            String serviceImplPath = serviceImplPackage.replace(".", "\\") + "\\" + className + "ServiceImpl.java";
            String sqlMapperFlag = CodeResourceUtil.getConfigInfo("sqlmap_flag");
            String domainFlag = CodeResourceUtil.getConfigInfo("domain_flag");
            String daoFlag = CodeResourceUtil.getConfigInfo("dao_flag");
            String daoImplFlag = CodeResourceUtil.getConfigInfo("dao_impl_flag");
            String managerFlag = CodeResourceUtil.getConfigInfo("manager_flag");
            String managerImplFlag = CodeResourceUtil.getConfigInfo("manager_impl_flag");
            String serviceFlag = CodeResourceUtil.getConfigInfo("service_flag");
            String serviceImplFlag = CodeResourceUtil.getConfigInfo("service_impl_flag");
            VelocityContext context = new VelocityContext();
            context.put("className", className);
            context.put("lowerName", lowerName);
            context.put("codeName", codeName);
            context.put("tableName", tableName);
            context.put("tableNameUpper", tableNameUpper);
            context.put("tablesAsName", tablesAsName);
            context.put("bussPackage", buss_package);
            context.put("domainPackage", domainPackage);
            context.put("daoPackage", daoPackage);
            context.put("daoImplPackage", daoImplPackage);
            context.put("managerPackage", managerPackage);
            context.put("managerImplPackage", managerImplPackage);
            context.put("servicePackage", servicePackage);
            context.put("serviceImplPackage", serviceImplPackage);
            context.put("keyType", keyType);
            context.put("feilds", e1.getBeanFeilds(tableName, className));
            Map sqlMap = e1.getAutoCreateSql(tableName);
            List columnDatas = e1.getColumnDatas(tableName);
            context.put("columnDatas", columnDatas);
            List columnKeyDatas = e1.getColumnKeyDatas(columnDatas);
            context.put("columnKeyDatas", columnKeyDatas);
            context.put("SQL", sqlMap);
            if("Y".equals(sqlMapperFlag)) {
                CommonPageParser.WriterPage(context, "sqlmap.vm", sqlmapPathSrc1, sqlMapperPath);
            }

            if("Y".equals(domainFlag)) {
                CommonPageParser.WriterPage(context, "domainClass.vm", domainPathSrc, domainPath);
            }

            if("Y".equals(daoFlag)) {
                CommonPageParser.WriterPage(context, "daoClass.vm", daoPathSrc, daoPath);
            }

            if("Y".equals(daoImplFlag)) {
                CommonPageParser.WriterPage(context, "daoImplClass.vm", daoImplPathSrc, daoImplPath);
            }

            if("Y".equals(managerFlag)) {
                CommonPageParser.WriterPage(context, "managerClass.vm", managerPathSrc, managerPath);
            }

            if("Y".equals(managerImplFlag)) {
                CommonPageParser.WriterPage(context, "managerImplClass.vm", managerImplPathSrc, managerImplPath);
            }

            if("Y".equals(serviceFlag)) {
                CommonPageParser.WriterPage(context, "serviceClass.vm", servicePathSrc, servicePath);
            }

            if("Y".equals(serviceImplFlag)) {
                CommonPageParser.WriterPage(context, "serviceImplClass.vm", serviceImplPathSrc, serviceImplPath);
            }

            log.info("----------------------------代码生成完毕---------------------------");
            System.out.println("----------------------------代码生成完毕---------------------------");
        } catch (Exception var44) {
            var44.printStackTrace();
        }

    }

    public static void codeGenerateByFTL(String tableName, String codeName, String keyType) {
        try {
            CreateBean e1 = new CreateBean();
            e1.setMysqlInfo(url, username, passWord);
            String className = e1.getTablesNameToClassName(tableName);
            //移除固定前缀
            className = className.substring(2,className.length());
            String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
            String tableNameUpper = tableName.toUpperCase();
            String tablesAsName = e1.getTablesASName(tableName);
            if(StringUtils.isEmpty(codeName)) {
                Map pathSrc = e1.getTableCommentMap();
                codeName = (String)pathSrc.get(tableNameUpper);
            }
            //java代码目录
            String pathSrc1 = CodeResourceUtil.getConfigInfo("path_src");
            //包名(和project组合) 如：com.xmmxjy
            String basePackage = CodeResourceUtil.getConfigInfo("base_package");
            //包名 如：system
            String projectName = CodeResourceUtil.getConfigInfo("project_name");
            basePackage = basePackage + "." + projectName;
            //代码作者
            String author = CodeResourceUtil.getConfigInfo("author");
            //目前没有用
            String baseDao = CodeResourceUtil.getConfigInfo("baseDao");
            //页面路径
            String pagePath = CodeResourceUtil.getConfigInfo("page_path");
            //实体路径
            String domainPath = pathSrc1 + basePackage.replace(".", "\\") + "\\" + "entity" + "\\" + className + "Entity.java";
            //dao路径
            String daoPath = pathSrc1 + basePackage.replace(".", "\\") + "\\" + "dao" + "\\" + className + "Dao.java";
            //service路径
            String servicePath = pathSrc1 + basePackage.replace(".", "\\") + "\\" + "service" + "\\" + className + "Service.java";
            //serviceImpl实现路径
            String serviceImplPath = pathSrc1 + basePackage.replace(".", "\\") + "\\" + "service" + "\\" + "impl" + "\\" + className + "ServiceImpl.java";
            //控制器路径
            String controllerPath = pathSrc1 + basePackage.replace(".", "\\") + "\\" + "controller" + "\\" + className + "Controller.java";
            //list页面路径
            String pageIndexPath = pagePath + "\\" + lowerName + "\\" +"list.html";
            //add页面路径
            String pageAddPath = pagePath + "\\" + lowerName + "\\" +"add.html";
            //修改页面路径
            String pageEditPath = pagePath + "\\" + lowerName  + "\\" + "edit.html";
            //详情页面路径
            String pageDetailPath = pagePath + "\\" + lowerName + "\\" + "detail.html";

            String sqlMapperFlag = CodeResourceUtil.getConfigInfo("sqlmap_flag");
            String domainFlag = CodeResourceUtil.getConfigInfo("domain_flag");
            String daoFlag = CodeResourceUtil.getConfigInfo("dao_flag");
            String daoImplFlag = CodeResourceUtil.getConfigInfo("dao_impl_flag");
            String serviceFlag = CodeResourceUtil.getConfigInfo("service_flag");
            String serviceImplFlag = CodeResourceUtil.getConfigInfo("service_impl_flag");
            String controllerFlag = CodeResourceUtil.getConfigInfo("controller_flag");
            String domainQueryFlag = CodeResourceUtil.getConfigInfo("domain_query_flag");
            String pageFlag = CodeResourceUtil.getConfigInfo("page_flag");
            String sqlFlag = CodeResourceUtil.getConfigInfo("sql_flag");
            Map sqlMap = e1.getAutoCreateSql(tableName);
            List columnDatas = e1.getColumnDatas(tableName);
            List columnKeyDatas = e1.getColumnKeyDatas(columnDatas);
            String columnKeyParam = e1.getColumnKeyParam(columnKeyDatas);
            String columnKeyUseParam = e1.getColumnKeyUseParam(columnKeyDatas);
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
            String nowDate = dateformat.format(new Date());
            System.out.println("时间:" + nowDate);
            HashMap root = new HashMap();
            root.put("author", author);
            root.put("baseDao", baseDao);
            root.put("className", className);
            root.put("lowerName", lowerName);
            root.put("codeName", codeName);
            root.put("tableName", tableName);
            root.put("tableNameUpper", tableNameUpper);
            root.put("tablesAsName", tablesAsName);
            root.put("projectName", projectName);
            root.put("package",projectName);
            root.put("keyType", keyType);
            root.put("nowDate", nowDate);
            root.put("feilds", e1.getBeanFeilds(tableName, className));
            root.put("queryfeilds", e1.getQueryBeanFeilds(tableName, className));
            root.put("columnDatas", columnDatas);
            root.put("columnKeyDatas", columnKeyDatas);
            root.put("columnKeyParam", columnKeyParam);
            root.put("columnKeyUseParam", columnKeyUseParam);
            root.put("SQL", sqlMap);
            Configuration cfg = new Configuration();
            String templateBasePath = getClassPath() + CodeResourceUtil.getConfigInfo("templatepath_ftl");
            cfg.setDirectoryForTemplateLoading(new File(templateBasePath));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            if("Y".equals(domainFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "domainClass.ftl", domainPath);
            }

            if("Y".equals(daoFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "daoClass.ftl", daoPath);
            }

            if("Y".equals(serviceFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "serviceClass.ftl", servicePath);
            }

            if("Y".equals(serviceImplFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "serviceImplClass.ftl", serviceImplPath);
            }

            if("Y".equals(controllerFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "controllerClass.ftl", controllerPath);
            }

            if("Y".equals(pageFlag)) {
                FreemarkerEngine.createFileByFTL(cfg, root, "list.ftl", pageIndexPath);
                FreemarkerEngine.createFileByFTL(cfg, root, "add.ftl", pageAddPath);
                FreemarkerEngine.createFileByFTL(cfg, root, "detail.ftl", pageDetailPath);
                FreemarkerEngine.createFileByFTL(cfg, root, "edit.ftl", pageEditPath);
            }


            log.info("----------------------------代码生成完毕---------------------------");
            System.out.println("----------------------------代码生成完毕---------------------------");
        } catch (Exception var59) {
            var59.printStackTrace();
        }

    }

    public static String getProjectPath() {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/";
        return path;
    }

    public static String getClassPath() {
        String path = (new CodeGenerateFactory()).getClass().getResource("/").getPath();
        return path;
    }
}
