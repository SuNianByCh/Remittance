package com.yaer.remittance.utils;

import android.os.Bundle;

/**
 * Created by Administrator on 2017/6/30.
 */
public class EventMessage {
    public Bundle bundle;
    public int tag;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public static class Type {
        public final static int PERSIONIMAGE = 66;//个人认证正面图像信息
        public final static int PERSIONOTHERIMAGE = 67;//个人认证反面图像信息
        public final static int COMPANYIMAGE = 71;//企业认证营业执照图像信息
        public final static int COMPANYPERSIONJUSTIMAGE = 72;//企业认证法人身份证正面图像信息
        public final static int COMPANYPERSIONBACKIMAGE = 73;//企业认证法人身份证反面图像信息
        public final static int MORETHANONEPICTURE = 68;//上传多张图像信息
        public final static int DPTOUXIANG = 69;//店铺头像图片信息
        public final static int DPBEIJING = 70;//店铺背景图片信息

    }
}
