package com.javafxstockchart.model;

import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
@Getter
public class OracleFromYamaha {
    /**
     * true = buy
     * false = sell
     */
    private boolean decisionWhetherBuyOrSell;
    private final static Random random = new Random();

    public OracleFromYamaha(){
        decisionWhetherBuyOrSell = random.nextBoolean();
    }

    public void setDecisionWhetherBuyOrSell() {
        this.decisionWhetherBuyOrSell = random.nextBoolean();
    }

    public String toString(){
        return decisionWhetherBuyOrSell ? "BUY" : "SELL";
    }
}
