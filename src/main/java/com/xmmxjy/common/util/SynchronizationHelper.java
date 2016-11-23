/*    */ package com.xmmxjy.common.util;
/*    */ 
/*    */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*    */

/*    */
/*    */ public class SynchronizationHelper
/*    */ {
/* 19 */   private static Logger logger = LoggerFactory.getLogger(SynchronizationHelper.class);
/* 20 */   private static final ThreadLocal<HttpServletRequest> localServletRequest = new ThreadLocal();
/* 21 */   private static final ThreadLocal<HttpServletResponse> localServletResponse = new ThreadLocal();
/*    */ 
/*    */   public static void clear()
/*    */   {
/* 29 */     localServletRequest.remove();
/* 30 */     localServletResponse.remove();
/* 31 */     if (logger.isDebugEnabled())
/* 32 */       logger.debug("清理当前线程");
/*    */   }
/*    */ 
/*    */   public static void setCurrentRequest(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 42 */     localServletRequest.set(request);
/* 43 */     localServletResponse.set(response);
/* 44 */     if (logger.isDebugEnabled())
/* 45 */       logger.debug("把当前请求绑定到当前进程");
/*    */   }
/*    */ 
/*    */   public static HttpServletRequest getCurrentRequest()
/*    */   {
/* 53 */     return ((HttpServletRequest)localServletRequest.get());
/*    */   }
/*    */ 
/*    */   public static HttpServletResponse getCurrentResponse()
/*    */   {
/* 61 */     return ((HttpServletResponse)localServletResponse.get());
/*    */   }
/*    */ }

/* Location:           D:\maven\repository\org\p3framework\jeecg-p3-com.xmmxjy.common\1.0-SNAPSHOT\jeecg-p3-com.xmmxjy.common-1.0-SNAPSHOT.jar
 * Qualified Name:     org.jeecgframework.p3.com.xmmxjy.common.util.plugin.SynchronizationHelper
 * JD-Core Version:    0.5.3
 */