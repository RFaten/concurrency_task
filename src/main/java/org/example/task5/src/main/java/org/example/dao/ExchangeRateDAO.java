package org.example.dao;

import org.apache.commons.lang3.tuple.Pair;
import org.example.model.Currency;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateDAO {
    private static final Map<Pair<Currency, Currency>, Double> exchangeRates = new HashMap<>() {{
        put(Pair.of(Currency.USD, Currency.EGP), 50.50);
        put(Pair.of(Currency.USD, Currency.PLN), 4.04);
        put(Pair.of(Currency.USD, Currency.EURO), 0.95);
        put(Pair.of(Currency.EGP, Currency.USD), 0.02);
        put(Pair.of(Currency.EGP, Currency.EURO), 0.019);
        put(Pair.of(Currency.EGP, Currency.PLN), 0.08);
        put(Pair.of(Currency.EURO, Currency.USD), 1.06);
        put(Pair.of(Currency.EURO, Currency.EGP), 53.29);
        put(Pair.of(Currency.EURO, Currency.PLN), 4.26);
        put(Pair.of(Currency.PLN, Currency.USD), 0.25);
        put(Pair.of(Currency.PLN, Currency.EGP), 12.52);
        put(Pair.of(Currency.PLN, Currency.EURO), 0.23);
    }};
    public static Map<Pair<Currency, Currency>, Double> getExchangeRates() {
        return exchangeRates;
    }
}
