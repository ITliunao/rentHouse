package com.xmmxjy.system.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "rh_dict_data")
public class RhDictData {
    @Id
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 对应的值
     */
    private String value;

    /**
     * 排序，由小到大
     */
    private Integer seq;

    /**
     * 创建人id
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建人
     */
    @Column(name = "create_name")
    private String createName;

    /**
     * 修改人id
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改人
     */
    @Column(name = "update_name")
    private String updateName;

    /**
     * 字典类型ID
     */
    @Column(name = "dict_type_id")
    private Integer dictTypeId;

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
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取对应的值
     *
     * @return value - 对应的值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置对应的值
     *
     * @param value 对应的值
     */
    public void setValue(String value) {
        this.value = value;
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

    /**
     * 获取创建人id
     *
     * @return create_by - 创建人id
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人id
     *
     * @param createBy 创建人id
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建人
     *
     * @return create_name - 创建人
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 设置创建人
     *
     * @param createName 创建人
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 获取修改人id
     *
     * @return update_by - 修改人id
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置修改人id
     *
     * @param updateBy 修改人id
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改人
     *
     * @return update_name - 修改人
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 设置修改人
     *
     * @param updateName 修改人
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    /**
     * 获取字典类型ID
     *
     * @return dict_type_id - 字典类型ID
     */
    public Integer getDictTypeId() {
        return dictTypeId;
    }

    /**
     * 设置字典类型ID
     *
     * @param dictTypeId 字典类型ID
     */
    public void setDictTypeId(Integer dictTypeId) {
        this.dictTypeId = dictTypeId;
    }
}