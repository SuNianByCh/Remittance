package com.yaer.remittance.ui.home_modular.leakhunting;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public interface ILeakHuntingView {
    void resultRealListDate(LinkedHashMap<String,List<LeakHuntingBean>> hashMap,ArrayList<String> headerNameList);
    void showLoading();
    void showMian();
}
