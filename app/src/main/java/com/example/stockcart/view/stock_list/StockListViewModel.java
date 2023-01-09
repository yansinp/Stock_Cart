package com.example.stockcart.view.stock_list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.stockcart.common.Constants;
import com.example.stockcart.common.resource.Resource;
import com.example.stockcart.data.model.stock_list.StocksDataResponse;
import com.example.stockcart.data.network.NetworkHelper;
import com.example.stockcart.repository.StockRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StockListViewModel extends ViewModel {

    private final StockRepository repository;

    private final MutableLiveData<Resource<List<StocksDataResponse>>> _stocksList = new MutableLiveData<>();
    public LiveData<Resource<List<StocksDataResponse>>> stockList = _stocksList;

    public StockListViewModel() {
        repository = new StockRepository();
    }

    public void getStocksList() {
        _stocksList.postValue(Resource.loading());

        repository.getStocksList(NetworkHelper.API_ACCESS_KEY_FINN, Constants.EXCHANGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (response.isSuccessful() && response.body() != null) {
                                List<StocksDataResponse> stocksListData = response.body();
                                _stocksList.postValue(Resource.success(stocksListData, "SUCCESS"));
                            } else {
                                _stocksList.postValue(Resource.error("Error in fetching stocks list"));
                            }
                        },
                        error -> _stocksList.postValue(Resource.error("Something went wrong..."))
                );
    }
}
