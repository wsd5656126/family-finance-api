package com.wusd.familyfinanceapi.entity.gen;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(value="com.wusd.familyfinanceapi.entity.gen.User")
public class User {
    @ApiModelProperty(value="userId用户id")
    private Integer userId;

    @ApiModelProperty(value="username登录名")
    private String username;

    @ApiModelProperty(value="sex性别:1:男,2:女")
    private Byte sex;

    @ApiModelProperty(value="realname真实姓名")
    private String realname;

    @ApiModelProperty(value="phone电话号码")
    private String phone;

    @ApiModelProperty(value="vicePhone副电话号码")
    private String vicePhone;

    @ApiModelProperty(value="password密码(加密后)")
    private String password;

    @ApiModelProperty(value="state可用状态")
    private Integer state;

    @ApiModelProperty(value="createTime写入库时间")
    private Date createTime;

    @ApiModelProperty(value="updateTime更新时间")
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getVicePhone() {
        return vicePhone;
    }

    public void setVicePhone(String vicePhone) {
        this.vicePhone = vicePhone == null ? null : vicePhone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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