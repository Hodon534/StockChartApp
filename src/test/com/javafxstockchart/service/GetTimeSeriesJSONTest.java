package com.javafxstockchart.service;

import com.javafxstockchart.model.Pojo.TimeSeries.PojoTimeSeries;
import com.javafxstockchart.repository.ParseJsonToPOJO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetTimeSeriesJSONTest {

    @Test
    void requestData() throws IOException {
        //given
        String interval = "1month";
        String symbol = "ADBE";
        //when
        PojoTimeSeries pojo = GetTimeSeriesJSON.requestData(interval, symbol);
        //then
        ;
        assertAll(
                () -> assertNotNull(pojo),
                () -> assertEquals(symbol, pojo.getMetaData().getSymbol()),
                () -> assertEquals("ok", pojo.getStatus())
        );
    }
}