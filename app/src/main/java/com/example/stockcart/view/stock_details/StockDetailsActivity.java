package com.example.stockcart.view.stock_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.stockcart.R;
import com.example.stockcart.data.model.stock_details.StockDetailsResponseAlpha;
import com.example.stockcart.data.model.stock_details.StockDetailsResponseFinn;
import com.example.stockcart.databinding.ActivityStockDetailsBinding;

public class StockDetailsActivity extends AppCompatActivity {

    private ActivityStockDetailsBinding binding;
    private StockDetailsViewModel viewModel;

    private String stockName, stockSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStockDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(StockDetailsViewModel.class);

        stockName = getIntent().getStringExtra("name");
        stockSymbol = getIntent().getStringExtra("symbol");

        init();
        setListeners();
        observeResponses();
    }

    private void init() {
        binding.tvStockName.setText(stockName);
        binding.tvStockSymbol.setText(stockSymbol);

        viewModel.getStockDetailsFinn(stockSymbol);
    }

    private void setListeners() {
        binding.tvKnowMore.setOnClickListener(v -> {
            if (!binding.tvStockDescription.getText().toString().isEmpty()) {
                binding.tvKnowMore.setVisibility(View.GONE);
                binding.cardStockDescription.setVisibility(View.VISIBLE);
            } else
                Toast.makeText(this, "No description found!", Toast.LENGTH_SHORT).show();
        });
    }

    private void observeResponses() {
        // Details from FinnHub
        viewModel.stockDetailsFinn.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader(true);
                    break;
                case SUCCESS:
                    showLoader(false);
                    if (response.data != null && response.data.getMetric() != null && response.data.getMetric().isValid())
                        setStockDataFromFinn(response.data);
                    else
                        Toast.makeText(this, "No data found!", Toast.LENGTH_SHORT).show();
                    // Calling next API here, to get more details
                    viewModel.getStockDetailsAlpha(stockSymbol);
                    break;
                case ERROR:
                    showLoader(false);
                    Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        // Details from AlphaVantage
        viewModel.stockDetailsAlpha.observe(this, response -> {
            switch (response.status) {
                case LOADING:
                    showLoader(true);
                    break;
                case SUCCESS:
                    showLoader(false);
                    if (response.data != null)
                        setStockDataFromAlpha(response.data);
                    else
                        Toast.makeText(this, "No data found!", Toast.LENGTH_SHORT).show();
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

    private void setStockDataFromFinn(StockDetailsResponseFinn stockData) {
        if (stockData.getMetric().getTotalDebtTotalEquityAnnual() != null
                && !stockData.getMetric().getTotalDebtTotalEquityAnnual().isEmpty())
            binding.tvDebtToEquity.setText(stockData.getMetric().getTotalDebtTotalEquityAnnual());
        if (stockData.getMetric().getEpsNormalizedAnnual() != null
                && !stockData.getMetric().getEpsNormalizedAnnual().isEmpty())
            binding.tvEps.setText(stockData.getMetric().getEpsNormalizedAnnual());
        if (stockData.getMetric().getMarketCapitalization() != null
                && !stockData.getMetric().getMarketCapitalization().isEmpty())
            binding.tvMarketCap.setText(stockData.getMetric().getMarketCapitalization());
        if (stockData.getMetric().getPeNormalizedAnnual() != null
                && !stockData.getMetric().getPeNormalizedAnnual().isEmpty())
            binding.tvPeRatio.setText(stockData.getMetric().getPeNormalizedAnnual());
        if (stockData.getMetric().getRoeTTM() != null
                && !stockData.getMetric().getRoeTTM().isEmpty())
            binding.tvRoe.setText(stockData.getMetric().getRoeTTM());
        if (stockData.getMetric().getDividendYieldIndicatedAnnual() != null
                && !stockData.getMetric().getDividendYieldIndicatedAnnual().isEmpty())
            binding.tvDividendYield.setText(stockData.getMetric().getDividendYieldIndicatedAnnual());
    }

    private void setStockDataFromAlpha(StockDetailsResponseAlpha stockData) {
        binding.tvStockDescription.setText(stockData.getDescription());
        if (binding.tvPeRatio.getText().toString().equals("--")) {
            if (stockData.getPERatio() != null
                    && !stockData.getPERatio().isEmpty())
                binding.tvPeRatio.setText(stockData.getPERatio());
        }
        if (binding.tvMarketCap.getText().toString().equals("--")) {
            if (stockData.getMarketCapitalization() != null
                    && !stockData.getMarketCapitalization().isEmpty())
                binding.tvMarketCap.setText(stockData.getMarketCapitalization());
        }
        if (binding.tvDividendYield.getText().toString().equals("--")) {
            if (stockData.getDividendYield() != null
                    && !stockData.getDividendYield().isEmpty())
                binding.tvDividendYield.setText(stockData.getDividendYield());
        }
        if (binding.tvRoe.getText().toString().equals("--")) {
            if (stockData.getReturnOnEquityTTM() != null
                    && !stockData.getReturnOnEquityTTM().isEmpty())
                binding.tvRoe.setText(stockData.getReturnOnEquityTTM());
        }
    }

}