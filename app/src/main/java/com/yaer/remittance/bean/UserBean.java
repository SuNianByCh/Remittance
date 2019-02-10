package com.yaer.remittance.bean;


import java.util.HashMap;
import java.util.Map;

public class UserBean {

    private int uid;//用户Id
    private String utoken;//用户token
    private String uphone; //获取手机号
    private String uname;//获取名称
    private String uicon;//获取头像
    private String usex;//获取性别
    private String usignature;//用户个性签名
    private String ubalance;//用户余额
    private String uintegral;//积分
    private String ulevel;//等级
    private String ureturnscale;//用户退货比例
    private String uinfringementscale;//用户违约比例
    private String upaypassword;//用户支付密码
    private String uloginpassword;//用户登录密码
    private String uthirdpartyid;//第三方登录id
    private int uthirdpartytype;//第三方登录type 1QQ 2微信 3微博
    private String uspecialist;//用户专家标识 0(不是专家)1(是专家)2(平台签约专家）
    private int enterprisecertification;//企业认证
    private int expertcertification;//专家认证
    private int personalauthentication;//个人认证
    private String utype;//用户类型
    private String utime;
    private String slabel;
    private String uinvitationcode;//邀请码
    private String ishaveUpaypassword;//1是已经拥有支付密码
    private String ishaveLoginpassword;//1是已经拥有登录密码

    public String getIshaveUpaypassword() {
        return ishaveUpaypassword;
    }

    public void setIshaveUpaypassword(String ishaveUpaypassword) {
        this.ishaveUpaypassword = ishaveUpaypassword;
    }

    public String getIshaveLoginpassword() {
        return ishaveLoginpassword;
    }

    public void setIshaveLoginpassword(String ishaveLoginpassword) {
        this.ishaveLoginpassword = ishaveLoginpassword;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUicon() {
        return uicon;
    }

    public void setUicon(String uicon) {
        this.uicon = uicon;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUsignature() {
        return usignature;
    }

    public void setUsignature(String usignature) {
        this.usignature = usignature;
    }

    public String getUbalance() {
        return ubalance;
    }

    public void setUbalance(String ubalance) {
        this.ubalance = ubalance;
    }

    public String getUintegral() {
        return uintegral;
    }

    public void setUintegral(String uintegral) {
        this.uintegral = uintegral;
    }

    public String getUlevel() {
        return ulevel;
    }

    public void setUlevel(String ulevel) {
        this.ulevel = ulevel;
    }

    public String getUreturnscale() {
        return ureturnscale;
    }

    public void setUreturnscale(String ureturnscale) {
        this.ureturnscale = ureturnscale;
    }

    public String getUinfringementscale() {
        return uinfringementscale;
    }

    public void setUinfringementscale(String uinfringementscale) {
        this.uinfringementscale = uinfringementscale;
    }

    public String getUpaypassword() {
        return upaypassword;
    }

    public void setUpaypassword(String upaypassword) {
        this.upaypassword = upaypassword;
    }

    public String getUloginpassword() {
        return uloginpassword;
    }

    public void setUloginpassword(String uloginpassword) {
        this.uloginpassword = uloginpassword;
    }

    public String getUthirdpartyid() {
        return uthirdpartyid;
    }

    public void setUthirdpartyid(String uthirdpartyid) {
        this.uthirdpartyid = uthirdpartyid;
    }

    public int getUthirdpartytype() {
        return uthirdpartytype;
    }

    public void setUthirdpartytype(int uthirdpartytype) {
        this.uthirdpartytype = uthirdpartytype;
    }

    public String getUspecialist() {
        return uspecialist;
    }

    public void setUspecialist(String uspecialist) {
        this.uspecialist = uspecialist;
    }

    public int getEnterprisecertification() {
        return enterprisecertification;
    }

    public void setEnterprisecertification(int enterprisecertification) {
        this.enterprisecertification = enterprisecertification;
    }

    public int getExpertcertification() {
        return expertcertification;
    }

    public void setExpertcertification(int expertcertification) {
        this.expertcertification = expertcertification;
    }

    public int getPersonalauthentication() {
        return personalauthentication;
    }

    public void setPersonalauthentication(int personalauthentication) {
        this.personalauthentication = personalauthentication;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public String getSlabel() {
        return slabel;
    }

    public void setSlabel(String slabel) {
        this.slabel = slabel;
    }

    public String getUinvitationcode() {
        return uinvitationcode;
    }

    public void setUinvitationcode(String uinvitationcode) {
        this.uinvitationcode = uinvitationcode;
    }
    /* public static String getJsonString(UserBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userBean.getUid());
        map.put("uname", userBean.getUname());
        map.put("uicon", userBean.getUicon());
        map.put("usex", userBean.getUsex());
        map.put("uphone", userBean.getUphone());
        map.put("usignature", userBean.getUsignature());
        map.put("ureturnscale", userBean.getUreturnscale());
        map.put("uinfringementscale", userBean.getUinfringementscale());
        map.put("utype", userBean.getUtype());
        map.put("uspecialist", userBean.getUspecialist());
        map.put("uloginpassword", userBean.getUloginpassword());
        map.put("upaypassword", userBean.getUpaypassword());
        map.put("ubalance", userBean.getUbalance());
        map.put("ulevel", userBean.getUlevel());
        map.put("uintegral", userBean.getUintegral());
        map.put("personalauthentication", userBean.getPersonalauthentication());
        map.put("expertcertification", userBean.getExpertcertification());
        map.put("enterprisecertification", userBean.getEnterprisecertification());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }*/

}
