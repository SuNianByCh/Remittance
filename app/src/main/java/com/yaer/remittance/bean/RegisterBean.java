package com.yaer.remittance.bean;

public class RegisterBean {
    public String utoken;
    public int isLogInPwd;//0没有设置密码1有密码
    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }

    public int getIsLogInPwd() {
        return isLogInPwd;
    }

    public void setIsLogInPwd(int isLogInPwd) {
        this.isLogInPwd = isLogInPwd;
    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "utoken='" + utoken + '\'' +
                ", isLogInPwd=" + isLogInPwd +
                '}';
    }
}
