package com.xmmxjy.system.entity;

import javax.persistence.*;

@Table(name = "rh_content_attribute")
public class RhContentAttribute {
    @Id
    private Long id;

    /**
     * 内容来源
     */
    private String source;

    /**
     * 来源地址
     */
    @Column(name = "source_url")
    private String sourceUrl;

    /**
     * 字数
     */
    @Column(name = "word_count")
    private Integer wordCount;

    /**
     * 数据JSON
     */
    private String data;

    /**
     * 内容
     */
    private String text;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取内容来源
     *
     * @return source - 内容来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置内容来源
     *
     * @param source 内容来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取来源地址
     *
     * @return source_url - 来源地址
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * 设置来源地址
     *
     * @param sourceUrl 来源地址
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    /**
     * 获取字数
     *
     * @return word_count - 字数
     */
    public Integer getWordCount() {
        return wordCount;
    }

    /**
     * 设置字数
     *
     * @param wordCount 字数
     */
    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * 获取数据JSON
     *
     * @return data - 数据JSON
     */
    public String getData() {
        return data;
    }

    /**
     * 设置数据JSON
     *
     * @param data 数据JSON
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 获取内容
     *
     * @return text - 内容
     */
    public String getText() {
        return text;
    }

    /**
     * 设置内容
     *
     * @param text 内容
     */
    public void setText(String text) {
        this.text = text;
    }
}