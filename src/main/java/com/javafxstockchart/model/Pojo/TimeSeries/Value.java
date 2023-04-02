package com.javafxstockchart.model.Pojo.TimeSeries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * POJO of single Query (Daily, Weekly, Monthly)
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Value {
    @JsonProperty("datetime")
    private String dateTime;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
}
