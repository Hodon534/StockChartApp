package com.javafxstockchart.model.Pojo.TimeSeries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Value {
    @JsonProperty("datetime")
    private String dateTime;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
}
