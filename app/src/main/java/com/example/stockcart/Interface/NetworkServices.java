package com.example.stockcart.Interface;

import com.example.stockcart.model.stockDetailsResponse;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkServices {

    @GET("/api/v1/stock/symbol")
    Call<List<stockDetailsResponse>> getStockData(
            @Query("exchange") String exchange,
            @Query("token") String token

    );


}
