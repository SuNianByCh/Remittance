package com.yaer.remittance.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.util.SimpleArrayMap;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/12.
 */
public class AppUtile {
    private static long lastClickTime;

    //两次点击时间相隔小于900ms
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 900) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public String formatLongToTimeStr(Long date) {
        long day = date / (60 * 60 * 24);
        long hour = (date / (60 * 60) - day * 24);
        long min = ((date / 60) - day * 24 * 60 - hour * 60);
        long s = (date - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String strtime = "剩余：" + day + "天" + hour + "小时" + min + "分" + s + "秒";
        return strtime;
    }

    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕的宽度和高度
     *
     * @param context
     * @return
     */
    public static int[] getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return new int[]{width, height};
    }

    /**
     * 保留两位小说点
     *
     * @return
     */
    public static String priceNum(double b) {
        return String.format("%.2f", b);
    }

    /**
     * 处理数字
     *
     * @param s
     * @return
     */
    public static String removeTailZero(String s) {
        if (s != null && s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    //验证座机号
    public static boolean isZuoJiHao(String str) {
        String regExp = "^[[0-9]-{1,20}]*$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //验证手机号
    public static boolean isPhone(String str) {
        String regExp = "^(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]+$";

    //{6,20}
    //验证psw
    public static boolean isPsw(String str) {
        String regExp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    //验证密码
    public static boolean isPassword(String str) {
        Pattern p = Pattern.compile(REGEX_PASSWORD);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //验证银行卡号
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    //从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 方法描述 隐藏银行卡号中间的字符串（使用*号），显示前四后四
     *
     * @param cardNo
     * @return
     * @author yaomy
     * @date 2018年4月3日 上午10:37:00
     */
    public static String hideCardNo(String cardNo) {
        int length = cardNo.length();
        int beforeLength = 4;
        int afterLength = 4;
        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (i < beforeLength || i >= (length - afterLength)) {
                sb.append(cardNo.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }

        return sb.toString();
    }

    /**
     * 方法描述 隐藏手机号中间位置字符，显示前三后三个字符
     *
     * @param phoneNo
     * @return
     * @author yaomy
     * @date 2018年4月3日 上午10:38:51
     */
    public static String hidePhoneNo(String phoneNo) {
        int length = phoneNo.length();
        int beforeLength = 3;
        int afterLength = 3;
        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (i < beforeLength || i >= (length - afterLength)) {
                sb.append(phoneNo.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }

        return sb.toString();
    }

    /**
     * 获取token
     *
     * @param context
     * @return
     */
    public static String getTicket(Context context) {
        return (String) SharedPreferencesUtils.getData(context, "uToken", "");
    }

    /**
     * 获取用户id
     *
     * @param context
     * @return
     */
    public static int getUid(Context context) {
        return (int) SharedPreferencesUtils.getData(context, "uid", 0);
    }

    /**
     * 获取店铺id
     *
     * @param context
     * @return
     */
    public static int getShopid(Context context) {
        return (int) SharedPreferencesUtils.getData(context, "shopid", 0);
    }

    /**
     * 获取店铺id
     *
     * @param context
     * @return
     */
    public static String getScid(Context context) {
        return (String) SharedPreferencesUtils.getData(context, "Scid", "");
    }


    /**
     * 获取用户手机号
     *
     * @param context
     * @return
     */
    public static String getPhone(Context context) {
        return (String) SharedPreferencesUtils.getData(context, "uphone", "");
    }

    public static String string2MD5(String inStr) {

        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(inStr.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toLowerCase();
    }
/*
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
*/

    /**
     * EditText 显示明文和密码
     *
     * @param ed         EditText
     * @param eyeOpen    boolean
     * @param img        imageView
     * @param resourceID 图片资源ID
     * @return
     */
    public static boolean changEditTextPsw(EditText ed, boolean eyeOpen, ImageView img, int resourceID, int resource) {
        if (eyeOpen) {
            //密码 TYPE_CLASS_TEXT 和 TYPE_TEXT_VARIATION_PASSWORD 必须一起使用
            ed.setTransformationMethod(PasswordTransformationMethod.getInstance());
            img.setImageResource(resourceID);
            return false;
        } else {
            //明文
            ed.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            img.setImageResource(resource);
            return true;
        }
    }

    /**
     * 获取ID
     *
     * @param context
     * @return
     */
    public static String getUserID(Context context) {
        return (String) SharedPreferencesUtils.getData(context, "id_user", "");
    }

    /**
     * 通过点击改变imageView 状态
     *
     * @param b      判断值
     * @param img    控件
     * @param sree   选中的img
     * @param unsree 未选中的img
     * @return
     */
    public static void isChangImage(boolean b, ImageView img, int unsree, int sree) {
        if (b) {
            img.setImageResource(unsree);
        } else {
            img.setImageResource(sree);
        }
    }

    public static boolean isEmptyNull(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SimpleArrayMap && ((SimpleArrayMap) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        if (obj instanceof LongSparseArray && ((LongSparseArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (obj instanceof android.util.LongSparseArray
                    && ((android.util.LongSparseArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断文本是否为空
     *
     * @param textView
     * @return
     */
    public static boolean isEmpty(TextView textView) {
        if (!textView.getText().toString().trim().equals("")
                && textView.getText().toString().trim() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断输入框是否为空
     *
     * @param ed
     * @return
     */
    public static boolean
    isEditText(EditText ed) {
        if (ed.getText().toString() != null && !ed.getText().toString().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串不能为""null
     *
     * @param string
     * @return
     */
    public static boolean isNull(CharSequence string) {
        return null == string || "".equals(string.toString().trim()) || "null".equals(string.toString().trim());
    }
    /**
     * 把文版信息复制到系统剪贴板
     *
     * @param string  内容
     * @param context 引用
     */
    public static void copy(String string, Context context) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setText(string);
    }

    /**
     * 时间转化
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    public static String[] split(String string) {
        return string.split(":");
    }

    public static String[] splitYTH(String string) {
        String[] str = string.split("【");
        String[] str2 = str[1].split("】");
        return new String[]{str[0], str2[1]};
    }

    /**
     * 点击键盘以外的地方的关闭键盘
     *
     * @param context
     * @param view1   获取焦点
     * @param view2   失去焦点
     */
    public static void setViewOnTouch(final Context context, View view1, final View view2) {
        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view2.getWindowToken(), 0);
                return false;
            }
        });

    }


    /**
     * 拦截h5 url
     *
     * @param string
     * @return
     */
    public static String lanjieURL(String string) {
        String str = string.substring(7, string.length() - 1);
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
        }
        return str;
    }

    /**
     * 截取单位方法 sss/ss
     *
     * @param unite
     * @return
     */
    public static String[] jieUnite(String unite) {
        String[] strUnite = unite.split("/");
        return strUnite;
    }

    /**
     * 截取单位方法 sss/ss
     *
     * @param unite
     * @return
     */
    public static String[] jiePrice(String unite) {
        String[] strUnite = unite.split("\\|");
        return strUnite;
    }

    /**
     * 判断字符串第一位是否为0 是0则截取否则返回
     *
     * @param unite
     * @return
     */
    public static String isStrNum(String unite) {
        if (!unite.equals("")) {
            String strIn = unite.substring(0, 1);
            if (strIn.equals("0")) {
                return unite.substring(1, unite.length());
            } else {
                return unite;
            }
        }
        return "";
    }

    /**
     * 把参数转化成String
     *
     * @param string
     * @return
     */
    public static String convertToMapJson(String string) {
        Map<String, Object> map = new HashMap<>();
        if (string.equals("") || string == null) {

        } else {
            String[] strings = string.split(",");
            for (int i = 0; i < strings.length; i++) {
                String[] pv = strings[i].split("=", 2);
                if (pv.length >= 1) {
                    map.put(pv[0].toLowerCase(), pv[1]);
                }
            }
        }
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }

    /**
     * 截取扫码 获取的字符串
     *
     * @param str
     * @return
     */
    public static String getDataInfo(String str) {
        if (str == null && str.equals("")) {
            return "";
        } else {
            if (str.contains("fafaapp.com")) {
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    String[] swSplit = str.split(".jhtml?");
                    String swCanShuo = str.substring(str.lastIndexOf("?") + 1);
                    String[] str2 = swSplit[0].split("fafaapp.com");
                    String str3 = swCanShuo.replaceAll("&", ",");
                    if (str2[1].contains("/g/d")) {
                        stringBuffer.append("native-patternDetail-");
                        stringBuffer.append(str3);
                        return stringBuffer.toString();
                    } else {
                        return str;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

}
