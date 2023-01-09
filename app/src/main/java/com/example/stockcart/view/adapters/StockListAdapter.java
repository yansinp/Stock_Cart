package com.example.stockcart.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockcart.data.model.stock_list.StocksDataResponse;
import com.example.stockcart.databinding.StockListItemBinding;

import java.util.List;

public class StockListAdapter extends RecyclerView.Adapter<StockListAdapter.MyViewHolder> {

    OnClickListener listener;
    Context context;
    List<StocksDataResponse> stockDataList;

    public interface OnClickListener {
        void onItemClicked(StocksDataResponse item);
    }

    public StockListAdapter(Context context, List<StocksDataResponse> stockDataList, OnClickListener listener) {
        this.context = context;
        this.stockDataList = stockDataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StockListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StockListItemBinding binding = StockListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StockListAdapter.MyViewHolder holder, int position) {
        holder.binding.tvStockName.setText(stockDataList.get(position).getDescription());
        holder.binding.tvStockSymbol.setText(stockDataList.get(position).getDisplaySymbol());

        holder.binding.getRoot().setOnClickListener(v -> {
            listener.onItemClicked(stockDataList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return stockDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        StockListItemBinding binding;

        public MyViewHolder(@NonNull StockListItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
