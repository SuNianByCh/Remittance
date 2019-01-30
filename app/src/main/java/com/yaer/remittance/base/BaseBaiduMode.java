package com.yaer.remittance.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/13.
 */
public class BaseBaiduMode<T> implements Serializable {
    private String error_code;
    private String reason;
    private Result result;
    private String ordersign;

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_code() {
        return error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setOrdersign(String ordersign) {
        this.ordersign = ordersign;
    }

    public String getOrdersign() {
        return ordersign;
    }

    @Override
    public String toString() {
        return "BaseBaiduMode{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", ordersign='" + ordersign + '\'' +
                '}';
    }

    public class Information {

        private String bankname;
        private String banknum;
        private String cardprefixnum;
        private String cardname;
        private String cardtype;
        private int cardprefixlength;
        private boolean isLuhn;
        private int iscreditcard;
        private int cardlength;
        private String bankurl;
        private String enbankname;
        private String abbreviation;
        private String bankimage;
        private String servicephone;

        @Override
        public String toString() {
            return "Information{" +
                    "bankname='" + bankname + '\'' +
                    ", banknum='" + banknum + '\'' +
                    ", cardprefixnum='" + cardprefixnum + '\'' +
                    ", cardname='" + cardname + '\'' +
                    ", cardtype='" + cardtype + '\'' +
                    ", cardprefixlength=" + cardprefixlength +
                    ", isLuhn=" + isLuhn +
                    ", iscreditcard=" + iscreditcard +
                    ", cardlength=" + cardlength +
                    ", bankurl='" + bankurl + '\'' +
                    ", enbankname='" + enbankname + '\'' +
                    ", abbreviation='" + abbreviation + '\'' +
                    ", bankimage='" + bankimage + '\'' +
                    ", servicephone='" + servicephone + '\'' +
                    '}';
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBanknum(String banknum) {
            this.banknum = banknum;
        }

        public String getBanknum() {
            return banknum;
        }

        public void setCardprefixnum(String cardprefixnum) {
            this.cardprefixnum = cardprefixnum;
        }

        public String getCardprefixnum() {
            return cardprefixnum;
        }

        public void setCardname(String cardname) {
            this.cardname = cardname;
        }

        public String getCardname() {
            return cardname;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getCardtype() {
            return cardtype;
        }

        public void setCardprefixlength(int cardprefixlength) {
            this.cardprefixlength = cardprefixlength;
        }

        public int getCardprefixlength() {
            return cardprefixlength;
        }

        public void setIsLuhn(boolean isLuhn) {
            this.isLuhn = isLuhn;
        }

        public boolean getIsLuhn() {
            return isLuhn;
        }

        public void setIscreditcard(int iscreditcard) {
            this.iscreditcard = iscreditcard;
        }

        public int getIscreditcard() {
            return iscreditcard;
        }

        public void setCardlength(int cardlength) {
            this.cardlength = cardlength;
        }

        public int getCardlength() {
            return cardlength;
        }

        public void setBankurl(String bankurl) {
            this.bankurl = bankurl;
        }

        public String getBankurl() {
            return bankurl;
        }

        public void setEnbankname(String enbankname) {
            this.enbankname = enbankname;
        }

        public String getEnbankname() {
            return enbankname;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setBankimage(String bankimage) {
            this.bankimage = bankimage;
        }

        public String getBankimage() {
            return bankimage;
        }

        public void setServicephone(String servicephone) {
            this.servicephone = servicephone;
        }

        public String getServicephone() {
            return servicephone;
        }
    }

    public class Result {
        private String bankcard;
        private String realName;
        private Information information;

        public void setBankcard(String bankcard) {
            this.bankcard = bankcard;
        }

        public String getBankcard() {
            return bankcard;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRealName() {
            return realName;
        }

        public void setInformation(Information information) {
            this.information = information;
        }

        public Information getInformation() {
            return information;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "bankcard='" + bankcard + '\'' +
                    ", realName='" + realName + '\'' +
                    ", information=" + information +
                    '}';
        }
    }
}
