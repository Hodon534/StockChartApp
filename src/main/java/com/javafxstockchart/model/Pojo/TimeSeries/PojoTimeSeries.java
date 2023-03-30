package com.javafxstockchart.model.Pojo.TimeSeries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PojoTimeSeries {
    @JsonProperty("meta")
    private MetaData metaData;
    @JsonProperty("values")
    private Value[] values;
    @JsonProperty("status")
    private String status;

}
