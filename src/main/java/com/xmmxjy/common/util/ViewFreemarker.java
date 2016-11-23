 package com.xmmxjy.common.util;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public class ViewFreemarker
/*     */   implements InitializingBean
/*     */ {
/*  23 */   static final Logger logger = LoggerFactory.getLogger(ViewFreemarker.class);
/*  24 */   private static Configuration _tplConfig = new Configuration();
/*     */ 
/*     */   static {
/*  27 */     _tplConfig.setClassForTemplateLoading(ViewFreemarker.class, "/content");
/*     */   }
/*     */ 
/*     */   private static String parseTemplate(String tplName, String encoding, Map<String, Object> paras)
/*     */   {
/*     */     try
/*     */     {
/*  39 */       StringWriter swriter = new StringWriter();
/*  40 */       Template mytpl = null;
/*  41 */       mytpl = _tplConfig.getTemplate(tplName, encoding);
                System.out.println(mytpl.toString());
/*  42 */       mytpl.process(paras, swriter);
/*  43 */       return swriter.toString();
/*     */     } catch (Exception e) {
/*  45 */       e.printStackTrace();
/*  46 */     return e.toString();
    }
/*     */   }
/*     */ 
/*     */   public static void view(HttpServletResponse response, String template)
/*     */     throws Exception
/*     */   {
/*  58 */     view(response, template, null);
/*     */   }
/*     */ 
/*     */   public static void view(HttpServletResponse rep, String template, Map<String, Object> paras)
/*     */     throws Exception
/*     */   {
/*  70 */     if (logger.isDebugEnabled()) {
/*  71 */       logger.debug("Freemarker loading：" + template);
/*     */     }
/*  73 */     HttpServletRequest request = ContextHolderUtils.getRequest();
/*  74 */     HttpServletResponse response = SynchronizationHelper.getCurrentResponse();
/*     */ 
/*  76 */     if (paras == null) {
/*  77 */       paras = new HashMap();
/*     */     }
/*  79 */     paras.put("request", request);
/*  80 */     paras.put("response", response);
/*     */ 
/*  83 */     String content = parseTemplate(template, "UTF-8", paras);
/*  84 */     outputToPage(request, response, content);
/*     */   }
/*     */ 
/*     */   private static void outputToPage(HttpServletRequest request, HttpServletResponse response, String content)
/*     */     throws Exception
/*     */   {
/*  96 */     response.setContentType("text/html");
/*  97 */     response.setHeader("Cache-Control", "no-store");
/*     */     try
/*     */     {
/* 100 */       PrintWriter writer = response.getWriter();
/* 101 */       writer.println(content);
/* 102 */       writer.flush();
/*     */     } catch (Exception e) {
/* 104 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void afterPropertiesSet()
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ }

