package com.example.stockcart.Interface;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_utilities {

    public static final String BASE_URL="";
    public static final String API_ACCESS_KEY="";

    public static Retrofit retrofit=null;


    public static API_INTER get_API_interface(){
        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
       return retrofit.create(API_INTER.class);

    };

}
