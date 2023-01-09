package com.example.stockcart.view.stock_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.stockcart.common.Constants;
import com.example.stockcart.common.resource.Resource;
import com.example.stockcart.data.model.stock_details.StockDetailsResponseAlpha;
import com.example.stockcart.data.model.stock_details.StockDetailsResponseFinn;
import com.example.stockcart.data.network.NetworkHelper;
import com.example.stockcart.repository.StockRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StockDetailsViewModel extends ViewModel {

    private final StockRepository repository;

    private final MutableLiveData<Resource<StockDetailsResponseAlpha>> _stockDetailsAlpha = new MutableLiveData<>();
    public LiveData<Resource<StockDetailsResponseAlpha>> stockDetailsAlpha = _stockDetailsAlpha;

    private final MutableLiveData<Resource<StockDetailsResponseFinn>> _stockDetailsFinn = new MutableLiveData<>();
    public LiveData<Resource<StockDetailsResponseFinn>> stockDetailsFinn = _stockDetailsFinn;

    public StockDetailsViewModel() {
        repository = new StockRepository();
    }

    public void getStockDetailsAlpha(String symbol) {
        _stockDetailsAlpha.postValue(Resource.loading());

        repository.getStockDetailsAlpha(Constants.ALPHA_FUNCTION, symbol, NetworkHelper.API_ACCESS_KEY_ALPHA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (response.isSuccessful() && response.body() != null) {
                                StockDetailsResponseAlpha stockDetails = response.body();
                                _stockDetailsAlpha.postValue(Resource.success(stockDetails, "SUCCESS"));
                            } else {
                                _stockDetailsAlpha.postValue(Resource.error("Error in fetching stock details"));
                            }
                        },
                        error -> _stockDetailsAlpha.postValue(Resource.error("Something went wrong..."))
                );
    }

    public void getStockDetailsFinn(String symbol) {
        _stockDetailsAlpha.postValue(Resource.loading());

        repository.getStockDetailsFinn(Constants.FINN_METRIC, symbol, NetworkHelper.API_ACCESS_KEY_FINN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (response.isSuccessful() && response.body() != null) {
                                StockDetailsResponseFinn stockDetails = response.body();
                                _stockDetailsFinn.postValue(Resource.success(stockDetails, "SUCCESS"));
                            } else {
                                _stockDetailsFinn.postValue(Resource.error("Error in fetching stock details"));
                            }
                        },
                        error -> _stockDetailsFinn.postValue(Resource.error("Something went wrong..."))
                );
    }

}
