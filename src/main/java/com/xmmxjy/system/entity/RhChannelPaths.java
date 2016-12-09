package com.xmmxjy.system.entity;

import javax.persistence.*;

@Table(name = "rh_channel_paths")
public class RhChannelPaths {
    /**
     * 上级ID
     */
    @Id
    private Integer ancestor;

    /**
     * 下级ID
     */
    @Id
    private Integer descendant;

    /**
     * 路径长度
     */
    @Column(name = "path_length")
    private Integer pathLength;

    /**
     * 获取上级ID
     *
     * @return ancestor - 上级ID
     */
    public Integer getAncestor() {
        return ancestor;
    }

    /**
     * 设置上级ID
     *
     * @param ancestor 上级ID
     */
    public void setAncestor(Integer ancestor) {
        this.ancestor = ancestor;
    }

    /**
     * 获取下级ID
     *
     * @return descendant - 下级ID
     */
    public Integer getDescendant() {
        return descendant;
    }

    /**
     * 设置下级ID
     *
     * @param descendant 下级ID
     */
    public void setDescendant(Integer descendant) {
        this.descendant = descendant;
    }

    /**
     * 获取路径长度
     *
     * @return path_length - 路径长度
     */
    public Integer getPathLength() {
        return pathLength;
    }

    /**
     * 设置路径长度
     *
     * @param pathLength 路径长度
     */
    public void setPathLength(Integer pathLength) {
        this.pathLength = pathLength;
    }
}