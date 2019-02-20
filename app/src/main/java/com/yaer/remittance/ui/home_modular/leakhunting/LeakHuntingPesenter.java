package com.yaer.remittance.ui.home_modular.leakhunting;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.BaseApplication;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class LeakHuntingPesenter {
    ILeakHuntingView mILeakHuntingView;
    public LeakHuntingPesenter(ILeakHuntingView mILeakHuntingView) {
        this.mILeakHuntingView = mILeakHuntingView;
    }
    public void getLeakData() {
        OkGo.<String>get(AppApi.GET_LEAK_COLLECTION).tag(this)
                .execute(new AbsCallback<String>() {

                    @Override
                    public String convertResponse(okhttp3.Response response) throws Throwable {
                        return response.body().string();
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        if(mILeakHuntingView != null)
                            mILeakHuntingView.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String body = response.body();
                            if (body == null || mILeakHuntingView == null) {
                                return;
                            }
                            JSONObject orginObj = new JSONObject(body);
                            String result = orginObj.getString("result");
                            if (!Constant.SUEECECODE.equals(orginObj.getString("code")) || StringUtils.isEmpty(result)) {
                                return;
                            }
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray names = jsonObject.names();
                            if(names == null || names.length() == 0){
                                return;
                            }

                            ArrayList<String> namesLiset = new ArrayList<>();
                            LinkedHashMap<String, List<LeakHuntingBean>> leakHuntingList = new LinkedHashMap<String, List<LeakHuntingBean>>();
                            String key;
                            Type type = new TypeToken<List<LeakHuntingBean>>() {
                            }.getType();
                            Gson gson = new Gson();
                            for (int i = 0; i < names.length(); i++) {
                                key = names.getString(i);
                                namesLiset.add(key);
                                leakHuntingList.put(key, (List<LeakHuntingBean>) gson.fromJson(jsonObject.getString(key),type));
                            }
                            mILeakHuntingView.resultRealListDate(leakHuntingList,namesLiset);

                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        if(mILeakHuntingView != null){
                            mILeakHuntingView.showMian();
                        }
                    }
                });

    }

    void destory() {
        mILeakHuntingView = null;
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(), this);
    }
}
