package com.example.stockcart.view.stock_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.core.splashscreen.SplashScreen;


import com.example.stockcart.data.model.stock_list.StocksDataResponse;
import com.example.stockcart.databinding.ActivityStockListBinding;
import com.example.stockcart.view.adapters.StockListAdapter;
import com.example.stockcart.view.stock_details.StockDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class StockListActivity extends AppCompatActivity implements StockListAdapter.OnClickListener {

    private ActivityStockListBinding binding;
    private StockListViewModel viewModel;

    private StockListAdapter adapter;
    private List<StocksDataResponse> stocksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        binding = ActivityStockListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(StockListViewModel.class);

        init();
        observeResponses();
    }

    private void init() {
        stocksList = new ArrayList<>();
        viewModel.getStocksList();
    }

    private void observeResponses() {
        viewModel.stockList.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader(true);
                    break;
                case SUCCESS:
                    showLoader(false);
                    stocksList = response.data;
                    updateRecycler();
                    break;
                case ERROR:
                    showLoader(false);
                    Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void showLoader(boolean flag) {
        if (flag)
            binding.progress.setVisibility(View.VISIBLE);
        else
            binding.progress.setVisibility(View.GONE);
    }

    private void updateRecycler() {
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        binding.recycler.setHasFixedSize(true);
        adapter = new StockListAdapter(this, stocksList, this);
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(StocksDataResponse item) {
        Intent i = new Intent(this, StockDetailsActivity.class);
        i.putExtra("name", item.getDescription());
        i.putExtra("symbol", item.getDisplaySymbol());
        startActivity(i);
    }
}
