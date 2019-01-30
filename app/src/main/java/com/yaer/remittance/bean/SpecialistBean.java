package com.yaer.remittance.bean;

public class SpecialistBean {
    /**
     * esid : 2id
     * esname : 321专家名称
     * esdesc : 312专家描述
     * esimg : 123专家图片
     */

    private int esid;
    private String esname;
    private String esdesc;
    private String esimg;

    public int getEsid() {
        return esid;
    }

    public void setEsid(int esid) {
        this.esid = esid;
    }

    public String getEsname() {
        return esname;
    }

    public void setEsname(String esname) {
        this.esname = esname;
    }

    public String getEsdesc() {
        return esdesc;
    }

    public void setEsdesc(String esdesc) {
        this.esdesc = esdesc;
    }

    public String getEsimg() {
        return esimg;
    }

    public void setEsimg(String esimg) {
        this.esimg = esimg;
    }


    /*//获取用户信息
    public String uid;
    //名称
    public String uname;
    //签名
    public String usignature;
    //专家
    public String uspecialist;
    //标签
    private String slabel;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsignature() {
        return usignature;
    }

    public void setUsignature(String usignature) {
        this.usignature = usignature;
    }

    public String getUspecialist() {
        return uspecialist;
    }

    public void setUspecialist(String uspecialist) {
        this.uspecialist = uspecialist;
    }

    public String getSlabel() {
        return slabel;
    }

    public void setSlabel(String slabel) {
        this.slabel = slabel;
    }
    public static String getJsonString(SpecialistBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userBean.getUid());
        map.put("uname", userBean.getUname());
        map.put("usignature", userBean.getUsignature());
        map.put("uspecialist", userBean.getUspecialist());
        map.put("slabel", userBean.getSlabel());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }*/

}
