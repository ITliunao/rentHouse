package com.xmmxjy.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Tools {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	//MD5密码加密工具
	public static String getMD5Str(String str){
	      MessageDigest messageDigest = null;
	      try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	      messageDigest.reset();
	      try {
			messageDigest.update(str.getBytes("gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    byte[] byteArray = messageDigest.digest();
	    StringBuffer md5StrBuff = new StringBuffer();
	    for (int i = 0; i < byteArray.length; i++) {
	      if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
	        md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
	      else
	        md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
	    }
	    return md5StrBuff.toString();
	  }

	/**
	 * 获取去掉横线的长度为32的UUID串.
	 *
	 * @author WuShuicheng.
	 * @return uuid.
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}



	/**
	 * 判断list是否为空
	 * @param list
	 * @return
	 */
	public static boolean  isListEmpty(List list){
		if (list == null) {
			return true;
		}
		if (list.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 把list<String>转化为逗号分隔的string
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		if (isListEmpty(list)) {
			return  null;
		}
		for (String str : list) {
			sb.append(str).append(",");
		}
		return sb.substring(0,sb.length()-1);
	}

}
