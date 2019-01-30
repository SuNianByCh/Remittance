package com.yaer.remittance.ui.user_modular.user_seller.help;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yaer.remittance.R;

import java.util.List;

/**
 *
 */
public class ParentEntity extends AbstractExpandableItem<ParentEntity.HelpCenterInfoModelsBean> implements MultiItemEntity {
    /**
     * hcid : 4
     * hcname : 认证中心
     * hctype : 2
     * helpCenterInfoModels : [{"hctid":4,"hctdesc":"切换卖家中心①个人认证：需要填写您的真实姓名、身份证号，上传您个人手持身份证正面照片以及您身份证的反面照片。提交信息后，平台将在1~5个工作日内进行审核。②企业认证：需要您填写企业名称、机构代码，上传企业营业执照的照片；并填写法人的姓名、身份证号，上传法人手持身份证正面照及法人身份证反面照。提交信息后，平台将在1~5个工作日进行审核。","hctname":"如何申请认证？","hcid":4},{"hctid":5,"hctdesc":"①若您填写的姓名、身份证号与您上传的身份证信息不符，审核将不予通过。②若您撒很难过传的身份证照片不符合规则要求（照片非身份证照片、身份证照片没有个人手持等情况），或您的身份证已过期，审核都不予通过。③若您填写的企业名称、机构代码与您上传的企业营业执照内信息不符，审核将不予通过。","hctname":"为什么我的认证不通过？","hcid":4},{"hctid":6,"hctdesc":"认证平台审核未通过，可修改信息进行重新认证。","hctname":"认证不通过怎么办？","hcid":4},{"hctid":7,"hctdesc":"认证过后就不可再修改信息，您的认证信息与您提现时绑定的银行卡信息需一致，请确认您的信息后进行认证。","hctname":"认证后还能修改信息吗？","hcid":4}]
     */

    private int hcid;
    private String hcname;
    private int hctype;
    private List<HelpCenterInfoModelsBean> helpCenterInfoModels;

    public int getHcid() {
        return hcid;
    }

    public void setHcid(int hcid) {
        this.hcid = hcid;
    }

    public String getHcname() {
        return hcname;
    }

    public void setHcname(String hcname) {
        this.hcname = hcname;
    }

    public int getHctype() {
        return hctype;
    }

    public void setHctype(int hctype) {
        this.hctype = hctype;
    }

    public List<HelpCenterInfoModelsBean> getHelpCenterInfoModels() {
        return helpCenterInfoModels;
    }

    public void setHelpCenterInfoModels(List<HelpCenterInfoModelsBean> helpCenterInfoModels) {
        this.helpCenterInfoModels = helpCenterInfoModels;


    }

    @Override
    public int getItemType() {
        return R.layout.item_help_parent;
    }

    @Override
    public int getLevel() {
        return R.layout.item_help_parent;
    }

    public void addSubItemAll() {
        if (helpCenterInfoModels == null)
            return;
        for (HelpCenterInfoModelsBean he : helpCenterInfoModels
                ) {
            addSubItem(he);
        }
    }

    public static class HelpCenterInfoModelsBean implements MultiItemEntity, Parcelable {
        /**
         * hctid : 4
         * hctdesc : 切换卖家中心①个人认证：需要填写您的真实姓名、身份证号，上传您个人手持身份证正面照片以及您身份证的反面照片。提交信息后，平台将在1~5个工作日内进行审核。②企业认证：需要您填写企业名称、机构代码，上传企业营业执照的照片；并填写法人的姓名、身份证号，上传法人手持身份证正面照及法人身份证反面照。提交信息后，平台将在1~5个工作日进行审核。
         * hctname : 如何申请认证？
         * hcid : 4
         */

        private int hctid;
        private String hctdesc;
        private String hctname;
        private int hcid;

        protected HelpCenterInfoModelsBean(Parcel in) {
            hctid = in.readInt();
            hctdesc = in.readString();
            hctname = in.readString();
            hcid = in.readInt();
        }

        public static final Creator<HelpCenterInfoModelsBean> CREATOR = new Creator<HelpCenterInfoModelsBean>() {
            @Override
            public HelpCenterInfoModelsBean createFromParcel(Parcel in) {
                return new HelpCenterInfoModelsBean(in);
            }

            @Override
            public HelpCenterInfoModelsBean[] newArray(int size) {
                return new HelpCenterInfoModelsBean[size];
            }
        };

        public int getHctid() {
            return hctid;
        }

        public void setHctid(int hctid) {
            this.hctid = hctid;
        }

        public String getHctdesc() {
            return hctdesc;
        }

        public void setHctdesc(String hctdesc) {
            this.hctdesc = hctdesc;
        }

        public String getHctname() {
            return hctname;
        }

        public void setHctname(String hctname) {
            this.hctname = hctname;
        }

        public int getHcid() {
            return hcid;
        }

        public void setHcid(int hcid) {
            this.hcid = hcid;
        }

        @Override
        public int getItemType() {
            return R.layout.item_help_child;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(hctid);
            dest.writeString(hctdesc);
            dest.writeString(hctname);
            dest.writeInt(hcid);
        }
    }
}
