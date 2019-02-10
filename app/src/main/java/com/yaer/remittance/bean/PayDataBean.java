package com.yaer.remittance.bean;


public class PayDataBean {
    private int uid;
    private String onumber;
    private double paymoney;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOnumber() {
        return onumber;
    }

    public void setOnumber(String onumber) {
        this.onumber = onumber;
    }

    public double getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(double paymoney) {
        this.paymoney = paymoney;
    }
}
