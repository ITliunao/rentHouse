package com.xmmxjy.common.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.jeecgframework.p3.cg.def.FtlDef;
import org.jeecgframework.p3.cg.factory.CodeGenerateFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xmm on 16-11-29.
 */
public class CodeUtil {
    public static void createFileByFTL(Configuration cfg, Map<String, Object> root, String ftlName, String fileDirPath, String targetFile) {
        String encoding = "utf-8";
        String workspasePath = "/home/xmm/Desktop/ftl";
        try {
            Template temp = cfg.getTemplate(ftlName, encoding);
            String e = workspasePath + File.separator + targetFile;

            File file = new File(e);
            if(!file.exists()) {
                (new File(file.getParent())).mkdirs();
                System.out.println("创建文件:" + file.getAbsolutePath());
            }

            FileOutputStream os = new FileOutputStream(file);
            OutputStreamWriter out = new OutputStreamWriter(os, encoding);
            temp.process(root, out);
            out.flush();
        } catch (IOException var13) {
            var13.printStackTrace();
        } catch (TemplateException var14) {
            var14.printStackTrace();
        }
    }
    public static void main(String [] args) throws IOException {
        /*Configuration cfg = new Configuration();
        String templateBasePath = new CodeUtil().getClass().getResource("/").getPath() + "ftl";
        cfg.setDirectoryForTemplateLoading(new File(templateBasePath));
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        Map<String,Object> root = new HashMap<String,Object>();
        root.put("packapge","system");
        root.put("className","class");
        root.put("lowerName","lower");
        root.put("columnDatas","");
        createFileByFTL(cfg, root, "domainClass.ftl", "", domainPath);*/

        CodeGenerateFactory.codeGenerateByFTL("rh_dict_type", "数据字典类型", FtlDef.KEY_TYPE_02);


    }

}
