package com.yaer.remittance.bean;

/**
 * 新品
 */
public class NewGoodsBean {
    /**
     * gid : 304
     * gimg : http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-1a79dd05-6da8-4b9a-a463-81d4af382585.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-e901e772-d5e5-489c-836b-af6f71a7eea6.png,http://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/goods/201901201146-729fb710-800e-453f-a89f-ad7f217a92a7.png
     * gname : 啦陆地饿哦青浦额u如
     * gmoney : 100
     * gnumber : 1
     * uname : 粒粒皆辛苦
     * uicon : https://paipinhui.oss-cn-qingdao.aliyuncs.com/auction/user/201901111410-73abbcab-414b-4de5-88ec-0e3653bc750d.png
     * gtime : 1547955981152
     * gstoptime : 1548308760000
     * glatestbid : 300
     * followStatus : false
     * ghot : 990
     */

    private int gid;
    private String gimg;
    private String gname;
    private String gmoney;
    private int gnumber;
    private String uname;
    private String uicon;
    private String gtime;
    private String gstoptime;
    private String glatestbid;
    private boolean followStatus;
    private String ghot;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGmoney() {
        return gmoney;
    }

    public void setGmoney(String gmoney) {
        this.gmoney = gmoney;
    }

    public int getGnumber() {
        return gnumber;
    }

    public void setGnumber(int gnumber) {
        this.gnumber = gnumber;
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

    public String getGtime() {
        return gtime;
    }

    public void setGtime(String gtime) {
        this.gtime = gtime;
    }

    public String getGstoptime() {
        return gstoptime;
    }

    public void setGstoptime(String gstoptime) {
        this.gstoptime = gstoptime;
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

    public String getGhot() {
        return ghot;
    }

    public void setGhot(String ghot) {
        this.ghot = ghot;
    }



   /* //商品id
    public String gid;
    //商品图片
    public String gimg;
    //商品名称
    public String gname;
    //价格
    public String gmoney;
    //数量
    private String gnumber;
    //店铺图片
    private String uicon;
    //结束时间
    private String gstoptime;
    //商家名称
    public String uname;
    //出价金额
    private String glatestbid;
    //时间
    public String gtime;
    //店铺关注状态
    private boolean followStatus;
    //火力值
    public String ghot;

    public String getUicon() {
        return uicon;
    }

    public void setUicon(String uicon) {
        this.uicon = uicon;
    }

    public String getGstoptime() {
        return gstoptime;
    }

    public void setGstoptime(String gstoptime) {
        this.gstoptime = gstoptime;
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

    public String getGhot() {
        return ghot;
    }

    public void setGhot(String ghot) {
        this.ghot = ghot;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGmoney() {
        return gmoney;
    }

    public void setGmoney(String gmoney) {
        this.gmoney = gmoney;
    }

    public String getGnumber() {
        return gnumber;
    }

    public void setGnumber(String gnumber) {
        this.gnumber = gnumber;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getGtime() {
        return gtime;
    }

    public void setGtime(String gtime) {
        this.gtime = gtime;
    }

    public static String getJsonString(NewGoodsBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("gid", userBean.getGid());
        map.put("gimg", userBean.getGimg());
        map.put("gname", userBean.getGname());
        map.put("gmoney", userBean.getGmoney());
        map.put("gnumber", userBean.getGnumber());
        map.put("uname", userBean.getUname());
        map.put("gtime", userBean.getGtime());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }
*/
}
