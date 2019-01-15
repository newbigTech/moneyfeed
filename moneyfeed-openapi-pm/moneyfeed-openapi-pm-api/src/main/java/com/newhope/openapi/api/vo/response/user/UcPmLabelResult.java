package com.newhope.openapi.api.vo.response.user;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by yyq on 2018/12/25
 */
public class UcPmLabelResult implements Serializable {

    private static final long serialVersionUID = 7439307920124898332L;
    @ApiModelProperty(name="id", notes="主键id")
    private Long id;
    @ApiModelProperty(name="targetType", notes="标签目标类别")
    private String targetType;
    @ApiModelProperty(name="name", notes="标签名称")
    private String name;
    @ApiModelProperty(name="createType", notes="生成方式")
    private String createType;
    @ApiModelProperty(name="comment", notes="备注信息")
    private String comment;
    @ApiModelProperty(name="gmtCreated", notes="创建时间")
    private Date gmtCreated;
    @ApiModelProperty(name="count", notes="客户数")
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
