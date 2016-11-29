package com.xmmxjy.system.entity;

import javax.persistence.*;

@Table(name = "rh_dict_type")
public class DictTypeEntity {
    @Id
    private String id;

    private String name;

    private String code;

    private String description;

    /**
     * 排序，由小到大
     */
    private Integer seq;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取排序，由小到大
     *
     * @return seq - 排序，由小到大
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置排序，由小到大
     *
     * @param seq 排序，由小到大
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}