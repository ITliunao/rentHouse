package com.xmmxjy.cms.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Table;

/**
 * 描述：内容文本
 * @author: xmm
 * @since：2016年12月09日 18时41分37秒 星期五 
 * @version:1.0
 */
@Table(name = "rh_content_attribute")
public class ContentAttributeEntity implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private Long id;	/**	 *内容来源	 */	private String source;	/**	 *来源地址	 */	private String sourceUrl;	/**	 *数据JSON	 */	private String data;	/**	 *内容	 */	private String text;	/**	 *字数	 */	private Integer wordCount;	public Long getId() {	    return this.id;	}	public void setId(Long id) {	    this.id=id;	}	public String getSource() {	    return this.source;	}	public void setSource(String source) {	    this.source=source;	}	public String getSourceUrl() {	    return this.sourceUrl;	}	public void setSourceUrl(String sourceUrl) {	    this.sourceUrl=sourceUrl;	}	public String getData() {	    return this.data;	}	public void setData(String data) {	    this.data=data;	}	public String getText() {	    return this.text;	}	public void setText(String text) {	    this.text=text;	}	public Integer getWordCount() {	    return this.wordCount;	}	public void setWordCount(Integer wordCount) {	    this.wordCount=wordCount;	}
}

