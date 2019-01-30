package com.yaer.remittance.bean;

import com.google.gson.annotations.SerializedName;

public class PayWXBean {

    /**
     * appid : wxb5b23d525521f6c4
     * partnerid : 1517792681
     * package : Sign=WXPay
     * noncestr : fnzcJhWbpHF5Ic6e
     * timestamp : 1544171242
     * prepayid : wx07162722234329af0480e8d23043559967
     * sign : 95643DD60382D208189C93DAEAFC9097
     * code_url : null
     */

    private String appid;
    private String partnerid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private String timestamp;
    private String prepayid;
    private String sign;
    private String code_url;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}
