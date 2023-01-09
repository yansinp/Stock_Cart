package com.example.stockcart.data.network;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {

    public static Retrofit retrofitFinn = null;
    public static Retrofit retrofitAlpha = null;

    public static final String BASE_URL_FINN = "https://finnhub.io/";
    public static final String BASE_URL_ALPHA = "https://www.alphavantage.co/";

    public static final String API_ACCESS_KEY_FINN = "cerr1fqad3i7i49822jgcerr1fqad3i7i49822k0";
    public static final String API_ACCESS_KEY_ALPHA = "3KWFU7L4URC7UV7E";

    public static NetworkServices getNetworkServicesForFinn() {
        if (retrofitFinn == null) {
            retrofitFinn = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(BASE_URL_FINN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofitFinn.create(NetworkServices.class);
    }

    public static NetworkServices getNetworkServicesForAlpha() {
        if (retrofitAlpha == null) {
            retrofitAlpha = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(BASE_URL_ALPHA)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofitAlpha.create(NetworkServices.class);
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(httpLoggingInterceptor);
        okHttpClient.writeTimeout(1, TimeUnit.MINUTES);
        okHttpClient.connectTimeout(1, TimeUnit.MINUTES);
        okHttpClient.readTimeout(1, TimeUnit.MINUTES);
        return okHttpClient.build();
    }
}
