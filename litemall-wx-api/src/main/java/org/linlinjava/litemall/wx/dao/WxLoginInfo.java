package org.linlinjava.litemall.wx.dao;

public class WxLoginInfo {
    private String code;
    private UserInfo userInfo;
    private Integer invite_user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getInvite_user() {
        return invite_user;
    }

    public void setInvite_user(Integer invite_user) {
        this.invite_user = invite_user;
    }
}
