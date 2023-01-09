package com.example.stockcart.data.network;

import com.example.stockcart.data.model.stock_details.StockDetailsResponseAlpha;
import com.example.stockcart.data.model.stock_details.StockDetailsResponseFinn;
import com.example.stockcart.data.model.stock_list.StocksDataResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkServices {

    @GET("api/v1/stock/symbol")
    Observable<Response<List<StocksDataResponse>>> getStocksList(
            @Query("token") String token,
            @Query("exchange") String exchange
    );

    @GET("query")
    Observable<Response<StockDetailsResponseAlpha>> getStockDetailsAlpha(
            @Query("function") String function,
            @Query("symbol") String symbol,
            @Query("apikey") String apikey
    );

    @GET("api/v1/stock/metric")
    Observable<Response<StockDetailsResponseFinn>> getStockDetailsFinn(
            @Query("metric") String metric,
            @Query("symbol") String symbol,
            @Query("token") String token
    );

}
