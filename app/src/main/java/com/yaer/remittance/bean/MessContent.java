package com.yaer.remittance.bean;

import java.util.List;

public class MessContent {

    /**
     * LogisticCode : M00035484779
     * ShipperCode : YTO
     * Traces : [{"AcceptStation":"【江苏省常州市潞城区公司】 取件人: 郭浩 已收件","AcceptTime":"2018-11-28 16:44:26"},{"AcceptStation":"【江苏省常州市公司】 已收件","AcceptTime":"2018-11-28 18:29:36"},{"AcceptStation":"【江苏省常州市公司】 已打包","AcceptTime":"2018-11-28 18:49:46"},{"AcceptStation":"【江苏省常州市公司】 已发出 下一站 【无锡转运中心】","AcceptTime":"2018-11-28 18:57:47"},{"AcceptStation":"【无锡转运中心】 已收入","AcceptTime":"2018-11-28 23:03:52"},{"AcceptStation":"【无锡转运中心】 已发出 下一站 【郑州转运中心】","AcceptTime":"2018-11-29 01:37:02"},{"AcceptStation":"【郑州转运中心】 已收入","AcceptTime":"2018-11-30 12:06:30"},{"AcceptStation":"【郑州转运中心】 已收入","AcceptTime":"2018-11-30 14:16:01"},{"AcceptStation":"【郑州转运中心】 已收入","AcceptTime":"2018-11-30 14:18:01"},{"AcceptStation":"【郑州转运中心】 已发出 下一站 【河南省郑州市东建材公司】","AcceptTime":"2018-11-30 14:20:26"},{"AcceptStation":"【河南省郑州市东建材公司】 已收入","AcceptTime":"2018-11-30 20:16:53"},{"AcceptStation":"【河南省郑州市东建材公司】 派件人: 赵光亮 派件中 派件员电话18538593990","AcceptTime":"2018-12-01 06:50:22"},{"AcceptStation":"快件已由郑州市滨河绿苑一号楼物业旁菜鸟驿站代收，请及时取件，如有疑问请联系15538316421","AcceptTime":"2018-12-01 09:42:35"},{"AcceptStation":"客户 签收人: 已签收，签收人凭取货码签收 已签收 感谢使用圆通速递，期待再次为您服务","AcceptTime":"2018-12-01 11:43:11"}]
     * State : 3
     * EBusinessID : 1398400
     * Success : true
     */

    private String LogisticCode;
    private String ShipperCode;
    private String State;
    private String EBusinessID;
    private boolean Success;
    private String Reason;

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    private List<TracesBean> Traces;

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String LogisticCode) {
        this.LogisticCode = LogisticCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String ShipperCode) {
        this.ShipperCode = ShipperCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public List<TracesBean> getTraces() {
        return Traces;
    }

    public void setTraces(List<TracesBean> Traces) {
        this.Traces = Traces;
    }

    public static class TracesBean {
        /**
         * AcceptStation : 【江苏省常州市潞城区公司】 取件人: 郭浩 已收件
         * AcceptTime : 2018-11-28 16:44:26
         */

        private String AcceptStation;
        private String AcceptTime;

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }
    }
}
