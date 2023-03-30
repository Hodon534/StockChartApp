package com.javafxstockchart.model.Pojo.TimeSeries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MetaData {
    private String symbol;
    private String interval;
    private String currency;
    @JsonProperty("exchange_timezone")
    private String exchangeTimezone;
    private String exchange;
    @JsonProperty("mic_code")
    private String micCode;
    private String type;
}
