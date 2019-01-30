package com.yaer.remittance.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/13.
 */
public class BaseMode<T> implements Serializable {
    public String code;
    public String msg;
    public T result;

    @Override
    public String toString() {
        return "BaseMode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    public String getCode() {
        if (code == null) {
            return null;
        } else
            return code.trim();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
