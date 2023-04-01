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
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
}
