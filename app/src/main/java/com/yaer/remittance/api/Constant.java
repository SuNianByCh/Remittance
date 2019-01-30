package com.yaer.remittance.api;

/**
 * Created by Administrator on 2017/6/14.
 */
public class Constant {
    public final static String pushMessage = "你推送功能尚未开启，会影响您的正常使用，请开启推送功能";
    public final static String CONTENT = "CONTENT";//已杀死进程
    public static final String SUEECECODE = "200";//成功请求码
    public static final String TWOZEROTWO = "202";//错误请求码
    public static final String REGUISTER_FAIL = "201";//注册失败，用户不存在
    public static final String YZM_INVALID = "202";//验证码无效
    public static final String IDENTITY_VALIDATION = "203";//身份效验失败
    public static final String IDENTITY_VALIDATIONNEWLOGIN = "506";//身份效验失败重新登录
    public static final String YZM_ERROR = "204";//验证码错误
    public static final String IDENTITY_TYPE = "1";//身份类型
    /*   public static final String YZM_TYPE = "1";//验证码类型*/
    public static final String CONFIRM_SIGN = "2";//确认登录
    public static final String ABNORMAL_SIGN = "3";//异常登录
    public static final String USER_REGISTER = "4";//用户注册
    public static final String FODIFY_PWD = "5";//修改密码
    public static final String INFORMATION_CHANGE = "6";//信息变更

    public static final String BANKCODESUCCESS = "0";//认证通过
    public static final String THISTYPEOFBANKCARDVERIFIVATION = "80011";//不支持此类银行卡校验
    public static final String INVALIDCARDNUMBER = "90020";//无效卡号
    public static final String PLEASECONTACTTHEISSUINGPARTY = "90021";//此卡被没收，请于发卡方联系
    public static final String FAILUREOFCARDHOLDERAUTHENTICATION = "90022";//持卡人认证失败
    public static final String THECARDISNOTINITIALIZEDORSLEEPINGCARD = "90024";//该卡未初始化或睡眠卡
    public static final String CHEATINGCARDSWALLOWINGCARD = "90025";//作弊卡，吞卡
    public static final String THISCARDHASBEENLOST = "90027";//此卡已经挂失
    public static final String THECARDWASCONFISCATED = "90028";//此卡被没收
    public static final String THECARDHASEXPIRED = "90029";//该卡已过期
    public static final String THEISSUINGPARTYDOESNOTALLOWTHISTRANSACTION = "90030";//发卡方不允许此交易
    public static final String RESTRICTEDCARDS = "90031";//受限制的卡
    public static final String PASSWORDERRORNUMBEROVERRUN = "90032";//密码错误次数超限
    public static final String INVALIDIDCARDNUMBER = "90033";//无效身份证号码
    public static final String USERUNOPENEDAUTHENTICATEDPAYMENT = "90039";//用户未开通认证支付
    public static final String FAILUREOFCERTIFICATION = "90099";//认证不通过
    public static final int UPLOAD_SUCCESS = 0;//上传成功
    public static final int TASK_PHOTO_SUCCESS = 1;//任务成功
    public static final int UPLOAD_LOCAL_ERROR = 2;//上传地址错误
    public static final int UPLOAD_SERVER_ERROR = 3;//上传服务器错误
    public static final int UPLOAD_PERSION_SUCCESS = 1;//修改成功
    public static final int ADD_PERSION_SUCCESS = 0;//添加成功
    //需要修改跟应用版本一样的
    public final static String api = "1.0";//手机api
}
