package com.javafxstockchart.service;

import com.javafxstockchart.model.Pojo.TimeSeries.MetaData;
import com.javafxstockchart.model.Pojo.TimeSeries.PojoTimeSeries;
import com.javafxstockchart.model.Pojo.TimeSeries.Value;
import com.javafxstockchart.repository.ParseJsonToPOJO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParseJsonToPOJOTest {
    /**
     * example of JSON from Twelve API
     * parameters:
     * -> symbol: MSFT
     * -> interval: 1month
     * -> output size: 10
     * -> api key: as in model -> api -> ApiKey (enum)
     */
    String stringToParse;
    /**
     * initialization of string to parse as POJO
     */
    @BeforeEach()
    void start(){
        stringToParse = "{\"meta\":" +
                "{\"symbol\":" + "\"MSFT\"," +
                "\"interval\":\"1month\"," +
                "\"currency\":\"USD\"," +
                "\"exchange_timezone\":\"America/New_York\"," +
                "\"exchange\":\"NASDAQ\"," +
                "\"mic_code\":\"XNGS\"," +
                "\"type\":\"Common Stock\"}," +
                "\"values\":" +
                "[" +

                "{\"datetime\":\"2023-03-01\"," +
                "\"open\":\"250.75999\"," +
                "\"high\":\"289.26999\"," +
                "\"low\":\"245.61000\"," +
                "\"close\":\"288.29999\"," +
                "\"volume\":\"744993296\"}," +

                "{\"datetime\":\"2023-02-01\"," +
                "\"open\":\"248.00000\"," +
                "\"high\":\"276.76001\"," +
                "\"low\":\"245.47000\"," +
                "\"close\":\"249.42000\"," +
                "\"volume\":\"608632448\"}," +

                "{\"datetime\":\"2023-01-01\"," +
                "\"open\":\"243.08000\"," +
                "\"high\":\"249.83000\"," +
                "\"low\":\"219.35001\"," +
                "\"close\":\"247.81000\"," +
                "\"volume\":\"657402170\"}," +

                "{\"datetime\":\"2022-12-01\"," +
                "\"open\":\"253.87000\"," +
                "\"high\":\"263.91501\"," +
                "\"low\":\"233.87000\"," +
                "\"close\":\"239.82001\"," +
                "\"volume\":\"589232831\"}," +

                "{\"datetime\":\"2022-11-01\"," +
                "\"open\":\"234.60001\"," +
                "\"high\":\"255.33000\"," +
                "\"low\":\"213.43100\"," +
                "\"close\":\"255.14000\"," +
                "\"volume\":\"615328224\"}," +

                "{\"datetime\":\"2022-10-01\"," +
                "\"open\":\"235.41000\"," +
                "\"high\":\"251.03999\"," +
                "\"low\":\"219.13000\"," +
                "\"close\":\"232.13000\"," +
                "\"volume\":\"671366798\"}," +

                "{\"datetime\":\"2022-09-01\"," +
                "\"open\":\"258.87000\"," +
                "\"high\":\"267.45001\"," +
                "\"low\":\"232.73000\"," +
                "\"close\":\"232.89999\"," +
                "\"volume\":\"575595198\"}," +

                "{\"datetime\":\"2022-08-01\"," +
                "\"open\":\"277.82001\"," +
                "\"high\":\"294.17999\"," +
                "\"low\":\"260.66000\"," +
                "\"close\":\"261.47000\"," +
                "\"volume\":\"477176207\"}," +

                "{\"datetime\":\"2022-07-01\"," +
                "\"open\":\"256.39001\"," +
                "\"high\":\"282.00000\"," +
                "\"low\":\"245.94000\"," +
                "\"close\":\"280.73999\"," +
                "\"volume\":\"534986152\"}," +

                "{\"datetime\":\"2022-06-01\"," +
                "\"open\":\"275.19501\"," +
                "\"high\":\"277.69000\"," +
                "\"low\":\"241.50999\"," +
                "\"close\":\"256.82999\"," +
                "\"volume\":\"621501472\"}" +

                "]," +
                "\"status\":\"ok\"}";
    }

    @Test
    void parseJsonToObject() throws IOException {
        //given
        PojoTimeSeries pojo = ParseJsonToPOJO.parseJsonToObject(stringToParse);
        MetaData metaData = pojo.getMetaData();
        Value firstValuePojo = pojo.getValues()[0];
        Value secondValuePojo = pojo.getValues()[1];
        //when
        // -> META DATA
        String symbol = "MSFT";
        String interval = "1month";
        String currency = "USD";
        String exchangeTimezone = "America/New_York";
        String exchange = "NASDAQ";
        String micCode = "XNGS";
        String type = "Common Stock";
        // -> VALUES (Time Queries)
        // -> First Value
        String fistValueDate = "2023-03-01";
        double firstValueOpen = 250.75999;
        double firstValueHigh = 289.26999;
        double firstValueLow = 245.61000;
        double firstValueClose = 288.29999;
        long firstValueVolume = 744993296;
        // -> Second Value
        String secondValueDate = "2023-02-01";
        double secondValueOpen = 248.00000;
        double secondValueHigh = 276.76001;
        double secondValueLow = 245.47000;
        double secondValueClose = 249.42000;
        long secondValueVolume = 608632448;
        // -> status
        String status = "ok";
        //then
        // -> metaData testing
        assertAll(
                () -> assertEquals(symbol, metaData.getSymbol()),
                () -> assertEquals(interval, metaData.getInterval()),
                () -> assertEquals(currency, metaData.getCurrency()),
                () -> assertEquals(exchangeTimezone, metaData.getExchangeTimezone()),
                () -> assertEquals(exchange, metaData.getExchange()),
                () -> assertEquals(micCode, metaData.getMicCode()),
                () -> assertEquals(type, metaData.getType())
        );
        // -> firstValuePojo testing
        assertAll(
                () -> assertEquals(fistValueDate, firstValuePojo.getDateTime()),
                () -> assertEquals(firstValueOpen, firstValuePojo.getOpen()),
                () -> assertEquals(firstValueHigh, firstValuePojo.getHigh()),
                () -> assertEquals(firstValueLow, firstValuePojo.getLow()),
                () -> assertEquals(firstValueClose, firstValuePojo.getClose()),
                () -> assertEquals(firstValueVolume, firstValuePojo.getVolume())
        );
        // -> secondValuePojo testing
        assertAll(
                () -> assertEquals(secondValueDate, secondValuePojo.getDateTime()),
                () -> assertEquals(secondValueOpen, secondValuePojo.getOpen()),
                () -> assertEquals(secondValueHigh, secondValuePojo.getHigh()),
                () -> assertEquals(secondValueLow, secondValuePojo.getLow()),
                () -> assertEquals(secondValueClose, secondValuePojo.getClose()),
                () -> assertEquals(secondValueVolume, secondValuePojo.getVolume())
        );
        // -> status testing
        assertEquals(status, pojo.getStatus());
    }
}