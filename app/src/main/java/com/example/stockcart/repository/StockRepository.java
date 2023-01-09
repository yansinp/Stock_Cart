package com.example.stockcart.repository;

import com.example.stockcart.data.model.stock_details.StockDetailsResponseAlpha;
import com.example.stockcart.data.model.stock_details.StockDetailsResponseFinn;
import com.example.stockcart.data.model.stock_list.StocksDataResponse;
import com.example.stockcart.data.network.NetworkHelper;
import com.example.stockcart.data.network.NetworkServices;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Response;

public class StockRepository {

    private final NetworkServices serviceFinn;
    private final NetworkServices serviceAlpha;

    public StockRepository() {
        this.serviceFinn = NetworkHelper.getNetworkServicesForFinn();
        this.serviceAlpha = NetworkHelper.getNetworkServicesForAlpha();
    }

    public Observable<Response<List<StocksDataResponse>>> getStocksList(String token, String exchange) {
        return serviceFinn.getStocksList(token, exchange);
    }

    public Observable<Response<StockDetailsResponseAlpha>> getStockDetailsAlpha(String function, String symbol, String apikey) {
        return serviceAlpha.getStockDetailsAlpha(function, symbol, apikey);
    }

    public Observable<Response<StockDetailsResponseFinn>> getStockDetailsFinn(String metric, String symbol, String token) {
        return serviceFinn.getStockDetailsFinn(metric, symbol, token);
    }

}
