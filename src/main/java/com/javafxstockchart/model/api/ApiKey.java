package com.javafxstockchart.model.api;

/**
 * Private free API key from Twelve Data
 * 9b8064a064mshec503363570c096p1b5dabjsnf78dd4d0ba37
 * 8196f48354mshc6d8c52844773b8p1592f4jsnfac1cfb6d183
 * 0f23bd092f644011889de648fde28d90
 * 9103a0d6677c44e4baae36080e2d192f
 */
public enum ApiKey implements ApiConstructor{
    API_KEY("9103a0d6677c44e4baae36080e2d192f");

    public final String apiKey;

    ApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getValue() {
        return apiKey;
    }

    @Override
    public String getKey() {
        return "&apikey";
    }
}
