package com.yaer.remittance.bean;

public class ReportFormBean {

    /**
     * refundmoney : 0.0
     * receivables : 0.0
     * unshipped : 34
     * unconfirm : 1
     * returngoods : 4
     */

    private String refundmoney;
    private String receivables;
    private int unshipped;
    private int unconfirm;
    private int returngoods;

    public String getRefundmoney() {
        return refundmoney;
    }

    public void setRefundmoney(String refundmoney) {
        this.refundmoney = refundmoney;
    }

    public String getReceivables() {
        return receivables;
    }

    public void setReceivables(String receivables) {
        this.receivables = receivables;
    }

    public int getUnshipped() {
        return unshipped;
    }

    public void setUnshipped(int unshipped) {
        this.unshipped = unshipped;
    }

    public int getUnconfirm() {
        return unconfirm;
    }

    public void setUnconfirm(int unconfirm) {
        this.unconfirm = unconfirm;
    }

    public int getReturngoods() {
        return returngoods;
    }

    public void setReturngoods(int returngoods) {
        this.returngoods = returngoods;
    }
}
