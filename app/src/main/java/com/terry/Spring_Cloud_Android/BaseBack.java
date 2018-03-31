package com.terry.Spring_Cloud_Android;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * *
 * name     BaseBack
 * Creater  Terry
 * time     2017/6/21
 * *
 **/

public abstract class BaseBack<T> implements Callback<BaseModel<T>> {

    public BaseBack() {
    }

    protected abstract void onSuccess(T t);

    protected void onFailed(int code, String msg) {
    }

    @Override
    public void onResponse(Call<BaseModel<T>> call, Response<BaseModel<T>> response) {
        BaseModel<T> baseModel = response.body();
        Log.d("aaaa", new GsonBuilder().setPrettyPrinting()
                .disableHtmlEscaping().create().toJson(baseModel));
        if (response.isSuccessful() && baseModel != null) {
            if (baseModel.code == 0) {
                onSuccess(baseModel.data);
            } else {
                onFailed(baseModel.code, baseModel.msg);
            }
        } else {
            onFailed(response.code(), response.message());
        }
    }

    @Override
    public void onFailure(Call<BaseModel<T>> call, Throwable t) {
        if (t instanceof ConnectException) {
            //网络连接失败
            onFailed(403, t.getMessage());
        } else if (t instanceof HttpException) {
            HttpException ex = (HttpException) t;
            onFailed(ex.code(), ex.message());
        } else {
            onFailed(405, t.getMessage());
        }
    }
}
