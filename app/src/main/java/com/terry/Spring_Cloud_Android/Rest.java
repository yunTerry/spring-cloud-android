package com.terry.Spring_Cloud_Android;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * *
 * name     Rest
 * Creater  Terry
 * time     2017/6/21
 * *
 **/

public class Rest {

    private static RestAPI restAPI;

    public static RestAPI getRestApi() {
        if (restAPI == null) {
            restAPI = new Retrofit.Builder()
                    .baseUrl("http://192.168.199.119:4000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RestAPI.class);
        }
        return restAPI;

    }

}
