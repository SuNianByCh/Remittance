package com.yaer.remittance.bean;

import android.text.TextUtils;

import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class getMyAuctionBean {

    /**
     * gid : 153
     * gname : 漆头诺
     * gisauction : 1
     * gmoney :
     * gdesc : 器某某
     * gcid : 77
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031541-c29f27ac-9493-4bfa-8c03-9c0dd5e1ff2e.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031541-04e9c55a-ccc2-4879-a13b-d1eb698ef6bd.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901031541-54764014-7bf2-4037-a6a4-add37e9f8f3d.png
     * gnumber : 1
     * gvideo :
     * gstartingprice : 0
     * gaddprice : 20
     * gstoptime : 1546566060000
     * gtime : 1546501304365
     * gcollateral : 0
     * sid : 92
     * gauctiontime : 1546393260000
     * gissoldout : 0
     * giscollectionid :
     * gpostage : 10
     * gisguarantee : 0
     * gquickrefund : 0
     * gGGCT : 0
     * gauthentication : 0
     * esid : 0
     * glatestbid : 80.00
     * followStatus : false
     * gstate : 0
     * biddingnumber:0
     * ghot:7470
     */

    private int gid;
    private String gname;
    private int gisauction;
    private String gmoney;
    private String gdesc;
    private int gcid;
    private String gimg;
    private int gnumber;
    private String gvideo;
    private String gstartingprice;
    private String gaddprice;
    private String gstoptime;
    private String gtime;
    private String gcollateral;
    private String sid;
    private String gauctiontime;
    private int gissoldout;
    private String giscollectionid;
    private String gpostage;
    private int gisguarantee;
    private int gquickrefund;
    private int gGGCT;
    private int gauthentication;
    private int esid;
    private String glatestbid;
    private boolean followStatus;
    private int gstate;
    private int biddingnumber;
    private int ghot;

    public int getBiddingnumber() {
        return biddingnumber;
    }

    public void setBiddingnumber(int biddingnumber) {
        this.biddingnumber = biddingnumber;
    }

    public int getGhot() {
        return ghot;
    }

    public void setGhot(int ghot) {
        this.ghot = ghot;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getGisauction() {
        return gisauction;
    }

    public void setGisauction(int gisauction) {
        this.gisauction = gisauction;
    }

    public String getGmoney() {
        return gmoney;
    }

    public void setGmoney(String gmoney) {
        this.gmoney = gmoney;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public int getGcid() {
        return gcid;
    }

    public void setGcid(int gcid) {
        this.gcid = gcid;
    }

    public String getGimg() {
        return gimg;
    }

    public List<String> getImgesPath() {
        if (TextUtils.isEmpty(getGimg())) {
            return null;
        }
        String[] split = getGimg().split(",");
        return Arrays.asList(split);
    }

    ArrayList<ImageItem> list;

    public ArrayList<ImageItem> getImageItemList() {
        List<String> imgesPath = getImgesPath();
        if (imgesPath == null || imgesPath.isEmpty()) {
            return new ArrayList<>();
        } else {
            list = new ArrayList<>();
            for (int i = 0; i < imgesPath.size(); i++) {
                ImageItem imageItem = new ImageItem();
                imageItem.path = imgesPath.get(i);
                list.add(imageItem);
            }
        }
        return list;
    }


    public void setGimg(String gimg) {
        this.gimg = gimg;
    }

    public int getGnumber() {
        return gnumber;
    }

    public void setGnumber(int gnumber) {
        this.gnumber = gnumber;
    }

    public String getGvideo() {
        return gvideo;
    }

    public void setGvideo(String gvideo) {
        this.gvideo = gvideo;
    }

    public String getGstartingprice() {
        return gstartingprice;
    }

    public void setGstartingprice(String gstartingprice) {
        this.gstartingprice = gstartingprice;
    }

    public String getGaddprice() {
        return gaddprice;
    }

    public void setGaddprice(String gaddprice) {
        this.gaddprice = gaddprice;
    }

    public String getGstoptime() {
        return gstoptime;
    }

    public void setGstoptime(String gstoptime) {
        this.gstoptime = gstoptime;
    }

    public String getGtime() {
        return gtime;
    }

    public void setGtime(String gtime) {
        this.gtime = gtime;
    }

    public String getGcollateral() {
        return gcollateral;
    }

    public void setGcollateral(String gcollateral) {
        this.gcollateral = gcollateral;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getGauctiontime() {
        return gauctiontime;
    }

    public void setGauctiontime(String gauctiontime) {
        this.gauctiontime = gauctiontime;
    }

    public int getGissoldout() {
        return gissoldout;
    }

    public void setGissoldout(int gissoldout) {
        this.gissoldout = gissoldout;
    }

    public String getGiscollectionid() {
        return giscollectionid;
    }

    public void setGiscollectionid(String giscollectionid) {
        this.giscollectionid = giscollectionid;
    }

    public String getGpostage() {
        return gpostage;
    }

    public void setGpostage(String gpostage) {
        this.gpostage = gpostage;
    }

    public int getGisguarantee() {
        return gisguarantee;
    }

    public void setGisguarantee(int gisguarantee) {
        this.gisguarantee = gisguarantee;
    }

    public int getGquickrefund() {
        return gquickrefund;
    }

    public void setGquickrefund(int gquickrefund) {
        this.gquickrefund = gquickrefund;
    }

    public int getGGGCT() {
        return gGGCT;
    }

    public void setGGGCT(int gGGCT) {
        this.gGGCT = gGGCT;
    }

    public int getGauthentication() {
        return gauthentication;
    }

    public void setGauthentication(int gauthentication) {
        this.gauthentication = gauthentication;
    }

    public int getEsid() {
        return esid;
    }

    public void setEsid(int esid) {
        this.esid = esid;
    }

    public String getGlatestbid() {
        return glatestbid;
    }

    public void setGlatestbid(String glatestbid) {
        this.glatestbid = glatestbid;
    }

    public boolean isFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(boolean followStatus) {
        this.followStatus = followStatus;
    }

    public int getGstate() {
        return gstate;
    }

    public void setGstate(int gstate) {
        this.gstate = gstate;
    }
}
