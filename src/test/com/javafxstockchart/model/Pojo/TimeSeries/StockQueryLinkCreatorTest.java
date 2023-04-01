package com.javafxstockchart.model.Pojo.TimeSeries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockQueryLinkCreatorTest {

    @Test
    void createLink() {
        //given
        StockQueryLinkCreator linkCreator = new StockQueryLinkCreator();
        //where
        // -> first example
        String firstInterval = "1day";
        String firstSymbol = "AAPL";
        String firstLink = "https://api.twelvedata.com/time_series?symbol=AAPL&interval=1day&outputsize=5000&apikey=9103a0d6677c44e4baae36080e2d192f";
        // -> second example
        String secondInterval = "1month";
        String secondSymbol = "MSFT";
        String secondLink = "https://api.twelvedata.com/time_series?symbol=MSFT&interval=1month&outputsize=5000&apikey=9103a0d6677c44e4baae36080e2d192f";
        // -> third example
        String thirdInterval = "1week";
        String thirdSymbol = "AMZN";
        String thirdLink = "https://api.twelvedata.com/time_series?symbol=AMZN&interval=1week&outputsize=5000&apikey=9103a0d6677c44e4baae36080e2d192f";
        //there
        assertAll(
                () -> assertEquals(firstLink, linkCreator.createLink(firstInterval, firstSymbol)),
                () -> assertEquals(secondLink, linkCreator.createLink(secondInterval, secondSymbol)),
                () -> assertEquals(thirdLink, linkCreator.createLink(thirdInterval, thirdSymbol))
        );
    }
}