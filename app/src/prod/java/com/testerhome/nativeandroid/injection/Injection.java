package com.testerhome.nativeandroid.injection;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.testerhome.nativeandroid.data.THRepository;
import com.testerhome.nativeandroid.data.THRepositoryImpl;
import com.testerhome.nativeandroid.data.remote.THRestService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bin Li on 2016/12/18.
 */

public class Injection {

    private static final String BASE_URL = "https://testerhome.com/api/v3/";
    private static OkHttpClient okHttpClient;
    private static THRestService thRestService;
    private static Retrofit retrofitInstance;

    public static THRepository provideThRepo() {
        return new THRepositoryImpl(provideThRestService());
    }

    static THRestService provideThRestService() {
        if (thRestService == null) {
            thRestService = getRetrofitInstance().create(THRestService.class);
        }

        return thRestService;
    }

    static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder().build();
        }

        return okHttpClient;
    }

    static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder()
                    .client(Injection.getOkHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofitInstance = retrofit.build();

        }
        return retrofitInstance;
    }
}
