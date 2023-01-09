package com.example.stockcart.data.model.stock_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockDetailsResponseFinn{

	@SerializedName("metricType")
	@Expose
	private String metricType;

	@SerializedName("metric")
	@Expose
	private Metric metric;

	public String getMetricType(){
		return metricType;
	}

	public Metric getMetric(){
		return metric;
	}
}