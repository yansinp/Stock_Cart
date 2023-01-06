package com.example.stockcart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class stockDetailsResponse {


    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("displaySymbol")
    @Expose
    public String displaySymbol;
    @SerializedName("figi")
    @Expose
    public String figi;
    @SerializedName("isin")
    @Expose
    public Object isin;
    @SerializedName("mic")
    @Expose
    public String mic;
    @SerializedName("shareClassFIGI")
    @Expose
    public String shareClassFIGI;
    @SerializedName("symbol")
    @Expose
    public String symbol;
    @SerializedName("symbol2")
    @Expose
    public String symbol2;
    @SerializedName("type")
    @Expose
    public String type;

}


