/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yaer.remittance.callback;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.tencent.mm.opensdk.utils.Log;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.utils.ToastUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * 修订历史：
 * ================================================
 */

/**
 * Created by gcy on 2017/6/14.
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Context context;

    public JsonCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);

    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        Type gytype = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) gytype).getActualTypeArguments()[0];
        if (type != BaseMode.class) {
            T t = (T) gson.fromJson(jsonReader, type);
            Log.i("http-->",gson.toJson(t));
            return t;
        } else {
            BaseMode baseMode = gson.fromJson(jsonReader, type);
            T baseMode1 = (T) baseMode;
            Log.i("http-->",gson.toJson(baseMode));
            return baseMode1;
        }
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        if (response.getException() instanceof Exception) {
            ToastUtils.showShort(context, "对不起，服务器故障，请稍后再试！！！");//请求失败,网络或服务器发生异常数据解析异常，稍后再试！！！
        }
    }
}
