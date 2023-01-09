package com.example.stockcart.data.model.stock_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StocksDataResponse {

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplaySymbol() {
        return displaySymbol;
    }

    public void setDisplaySymbol(String displaySymbol) {
        this.displaySymbol = displaySymbol;
    }

    public String getFigi() {
        return figi;
    }

    public void setFigi(String figi) {
        this.figi = figi;
    }

    public Object getIsin() {
        return isin;
    }

    public void setIsin(Object isin) {
        this.isin = isin;
    }

    public String getMic() {
        return mic;
    }

    public void setMic(String mic) {
        this.mic = mic;
    }

    public String getShareClassFIGI() {
        return shareClassFIGI;
    }

    public void setShareClassFIGI(String shareClassFIGI) {
        this.shareClassFIGI = shareClassFIGI;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol2() {
        return symbol2;
    }

    public void setSymbol2(String symbol2) {
        this.symbol2 = symbol2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


