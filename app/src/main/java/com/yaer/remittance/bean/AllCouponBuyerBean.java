package com.yaer.remittance.bean;

public class AllCouponBuyerBean {
    /**
     * cbid : 1
     * cbname : 满减优惠
     * cbmoney : 10
     * cbmaxmoney : 50
     * cbstartime : 1547948119000
     * cbendtime : 1548207319000
     */
    private int cbid;
    private String cbname;
    private String cbmoney;
    private String cbmaxmoney;
    private String cbstartime;
    private String cbendtime;

    public int getCbid() {
        return cbid;
    }

    public void setCbid(int cbid) {
        this.cbid = cbid;
    }

    public String getCbname() {
        return cbname;
    }

    public void setCbname(String cbname) {
        this.cbname = cbname;
    }

    public String getCbmoney() {
        return cbmoney;
    }

    public void setCbmoney(String cbmoney) {
        this.cbmoney = cbmoney;
    }

    public String getCbmaxmoney() {
        return cbmaxmoney;
    }

    public void setCbmaxmoney(String cbmaxmoney) {
        this.cbmaxmoney = cbmaxmoney;
    }

    public String getCbstartime() {
        return cbstartime;
    }

    public void setCbstartime(String cbstartime) {
        this.cbstartime = cbstartime;
    }

    public String getCbendtime() {
        return cbendtime;
    }

    public void setCbendtime(String cbendtime) {
        this.cbendtime = cbendtime;
    }
}
