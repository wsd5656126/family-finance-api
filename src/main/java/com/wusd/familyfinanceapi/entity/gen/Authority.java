package com.wusd.familyfinanceapi.entity.gen;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="com.wusd.familyfinanceapi.entity.gen.Authority")
public class Authority {
    @ApiModelProperty(value="id")
    private Integer id;

    @ApiModelProperty(value="username")
    private String username;

    @ApiModelProperty(value="authority")
    private String authority;

    @ApiModelProperty(value="state")
    private Integer state;

    @ApiModelProperty(value="createTime")
    private Date createTime;

    @ApiModelProperty(value="updateTime")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}