package com.yaer.remittance.utils;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.PortUnreachableException;

/**
 * Created by ymy on 2018/3/23.
 */

public class StringUtils {
    public StringUtils() {
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() <= 0;
    }

    public static String convertObjectToString(Object o) {
        return o != null ? (o instanceof String ? ((String) o).toString() : (o instanceof Integer ? "" + ((Integer) o).intValue() : (o instanceof Long ? "" + ((Long) o).longValue() : (o instanceof Double ? "" + ((Double) o).doubleValue() : (o instanceof Float ? "" + ((Float) o).floatValue() : (o instanceof Short ? "" + ((Short) o).shortValue() : (o instanceof Byte ? "" + ((Byte) o).byteValue() : (o instanceof Boolean ? ((Boolean) o).toString() : (o instanceof Character ? ((Character) o).toString() : o.toString()))))))))) : "";
    }

    public static int hashCode(String value) {
        int h = 0;
        if (h == 0 && value.length() > 0) {
            char[] val = value.toCharArray();

            for (int i = 0; i < val.length; ++i) {
                h = 31 * h + val[i];
            }
        }

        return h;
    }

    public static TextView setTextTimeDefault(TextView textView, long time, String dafult) {
        if (textView == null) {
            return textView;
        }
        String text = SystemUtil.timeLongToString(time, SystemUtil.STR_FORMAT_YYYY_MM_DD_HH_MM);
        if (isEmpty(text)) {
            text = dafult;
        }
        textView.setText(text);
        return textView;
    }

    public static TextView setTextTimeDefault(TextView textView, String time, String dafult) {
        if (textView == null) {
            return textView;
        }

        String text = SystemUtil.timeLongToString(time, SystemUtil.STR_FORMAT_YYYY_MM_DD_HH_MM);
        if (isEmpty(text)) {
            text = dafult;
        }
        textView.setText(text);
        return textView;
    }

    public static TextView setTextTimeDefault(TextView textView, long time) {
        if (textView == null) {
            return textView;
        }
        textView.setText(SystemUtil.timeLongToString(time, SystemUtil.STR_FORMAT_YYYY_MM_DD_HH_MM));
        return textView;
    }

    public static TextView setTextTimeDefault(TextView textView, String time) {
        if (textView == null) {
            return textView;
        }
        textView.setText(SystemUtil.timeLongToString(time, SystemUtil.STR_FORMAT_YYYY_MM_DD_HH_MM));
        return textView;
    }

    public static TextView setTextTime(TextView textView, long time, String format) {
        if (textView == null || format == null) {
            return textView;
        }
        textView.setText(SystemUtil.timeLongToString(time, format));
        return textView;
    }

    public static TextView setTextTime(TextView textView, String time, String format) {
        if (textView == null || format == null) {
            return textView;
        }
        textView.setText(SystemUtil.timeLongToString(time, format));
        return textView;
    }

    public static TextView setText(TextView text, CharSequence sequence) {
        return setText(text, sequence, "");
    }

    public static TextView setText(TextView text, CharSequence sequence, CharSequence defaultStr) {
        if (text == null) {
            return text;
        }
        if (defaultStr == null) {
            defaultStr = "";
        }
        if (sequence == null || StringUtils.isEmpty(sequence.toString())) {
            sequence = defaultStr;
        }
        text.setText(sequence);
        return text;
    }

    public static TextView setTextDefultBefore(TextView textView, String content, String beforeContent) {
        if (textView == null) {
            return null;
        }

        if (StringUtils.isEmpty(content)) {
            content = "";
        }
        textView.setText(beforeContent);
        textView.append(content);


        return textView;
    }
}
