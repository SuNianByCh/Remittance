package com.yaer.remittance.bean;

/**
 * @Description:用于网络的事件
 */
public class NetEvent {
    public boolean isNet;

    public NetEvent(boolean isNet) {

        this.isNet = isNet;
    }

    public boolean isNet() {
        return isNet;
    }
}
