package com.javafxstockchart.model.Pojo.TimeSeries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PojoTimeSeries {
    @JsonProperty("meta")
    private MetaData metaData;
    @JsonProperty("values")
    private Value[] values;
    @JsonProperty("status")
    private String status;

}
