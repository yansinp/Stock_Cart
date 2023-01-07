package com.example.stockcart.Interface;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {

    public static final String BASE_URL = "https://finnhub.io/";
    public static final String API_ACCESS_KEY = "cerr1fqad3i7i49822jgcerr1fqad3i7i49822k0";

    public static Retrofit retrofit = null;

    public static NetworkServices get_API_interface() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(NetworkServices.class);

    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(httpLoggingInterceptor);
        okHttpClient.writeTimeout(3, TimeUnit.MINUTES);
        okHttpClient.connectTimeout(3, TimeUnit.MINUTES);
        okHttpClient.readTimeout(3, TimeUnit.MINUTES);
        return okHttpClient.build();
    }



}
