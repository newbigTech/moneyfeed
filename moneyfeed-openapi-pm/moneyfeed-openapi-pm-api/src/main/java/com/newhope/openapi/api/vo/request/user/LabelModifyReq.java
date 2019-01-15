package com.newhope.openapi.api.vo.request.user;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Create by yyq on 2018/12/25
 */
public class LabelModifyReq implements Serializable {

    private static final long serialVersionUID = -510201069804806987L;
    @ApiModelProperty(name="id", notes="主键id")
    @NotNull
    private Long id;
    @ApiModelProperty(name="targetType", notes="标签目标类别", required=false)
    private String targetType;
    @ApiModelProperty(name="name", notes="标签名称", required=false)
    private String name;
    @ApiModelProperty(name="createType", notes="生成方式", required=false)
    private String createType;
    @ApiModelProperty(name="comment", notes="备注信息", required=false)
    private String comment;
    @ApiModelProperty(name="enable", notes="是否可用", required=false)
    private Boolean enable;

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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
