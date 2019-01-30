package com.yaer.remittance.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SelectCompanyAuthenticationInfoBean {
    //企业认证id
    private String ecid;
    //账号id
    private String uid;
    //认证企业名称
    private String ecname;
    //认证机构代码证
    private String ecnumber;
    //企业营业执照
    private String ecimg;
    //法人姓名
    private String eclegalpersonname;
    //认证身份证
    private String eclegalpersonnamecardnum;
    /*正面身份证图片*/
    private String eclegalpersoncardimg;
    /*反面面身份证图片*/
    private String eclegalpersoncardimgback;
    //认证类型
    private String ecstatues;

    public static String getJsonString(SelectCompanyAuthenticationInfoBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("ecid", userBean.getEcid());
        map.put("uid", userBean.getUid());
        map.put("ecname", userBean.getEcname());
        map.put("ecnumber", userBean.getEcnumber());
        map.put("ecimg", userBean.getEcimg());
        map.put("eclegalpersonname", userBean.getEclegalpersonname());
        map.put("eclegalpersonnamecardnum", userBean.getEclegalpersonnamecardnum());
        map.put("eclegalpersoncardimg", userBean.getEclegalpersoncardimg());
        map.put("eclegalpersoncardimgback", userBean.getEclegalpersoncardimgback());
        map.put("ecstatues", userBean.getEcstatues());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }

    public String getEcid() {
        return ecid;
    }

    public void setEcid(String ecid) {
        this.ecid = ecid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEcname() {
        return ecname;
    }

    public void setEcname(String ecname) {
        this.ecname = ecname;
    }

    public String getEcnumber() {
        return ecnumber;
    }

    public void setEcnumber(String ecnumber) {
        this.ecnumber = ecnumber;
    }

    public String getEcimg() {
        return ecimg;
    }

    public void setEcimg(String ecimg) {
        this.ecimg = ecimg;
    }

    public String getEclegalpersonname() {
        return eclegalpersonname;
    }

    public void setEclegalpersonname(String eclegalpersonname) {
        this.eclegalpersonname = eclegalpersonname;
    }

    public String getEclegalpersonnamecardnum() {
        return eclegalpersonnamecardnum;
    }

    public void setEclegalpersonnamecardnum(String eclegalpersonnamecardnum) {
        this.eclegalpersonnamecardnum = eclegalpersonnamecardnum;
    }

    public String getEclegalpersoncardimg() {
        return eclegalpersoncardimg;
    }

    public void setEclegalpersoncardimg(String eclegalpersoncardimg) {
        this.eclegalpersoncardimg = eclegalpersoncardimg;
    }

    public String getEclegalpersoncardimgback() {
        return eclegalpersoncardimgback;
    }

    public void setEclegalpersoncardimgback(String eclegalpersoncardimgback) {
        this.eclegalpersoncardimgback = eclegalpersoncardimgback;
    }

    public String getEcstatues() {
        return ecstatues;
    }

    public void setEcstatues(String ecstatues) {
        this.ecstatues = ecstatues;
    }
}
