package com.xmmxjy.system.entity;

import javax.persistence.*;

@Table(name = "rh_channel")
public class RhChannel {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 父栏目ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 栏目名称
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 是否显示
     */
    @Column(name = "is_display")
    private Byte isDisplay;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取父栏目ID
     *
     * @return parent_id - 父栏目ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父栏目ID
     *
     * @param parentId 父栏目ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取栏目名称
     *
     * @return name - 栏目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置栏目名称
     *
     * @param name 栏目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取顺序
     *
     * @return sort - 顺序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置顺序
     *
     * @param sort 顺序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取是否显示
     *
     * @return is_display - 是否显示
     */
    public Byte getIsDisplay() {
        return isDisplay;
    }

    /**
     * 设置是否显示
     *
     * @param isDisplay 是否显示
     */
    public void setIsDisplay(Byte isDisplay) {
        this.isDisplay = isDisplay;
    }
}