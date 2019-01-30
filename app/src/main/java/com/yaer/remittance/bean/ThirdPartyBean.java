package com.yaer.remittance.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThirdPartyBean {
    public String utoken;
    public int uid;
    public int isPhone;//是否绑定手机号

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIsPhone() {
        return isPhone;
    }

    public void setIsPhone(int isPhone) {
        this.isPhone = isPhone;
    }

    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }

    @Override
    public String toString() {
        return "ThirdPartyBean{" +
                "utoken='" + utoken + '\'' +
                ", uid=" + uid +
                ", isPhone=" + isPhone +
                '}';
    }

   /* public static String getJsonString(ThirdPartyBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userBean.getUid());
        map.put("utoken", userBean.getUtoken());
        map.put("isPhone", userBean.getIsPhone());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }*/
}
