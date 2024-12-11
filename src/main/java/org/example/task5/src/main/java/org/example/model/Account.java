package org.example.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class Account  {
    private int id;
    private String accountNum;
    private Map<Currency, BigDecimal> currencyAmount;

    public int getId() {
        return id;
    }

    public Account() {
    }

    public Account(int id, String accountNum, Map<Currency, BigDecimal> currencyAmount) {
        this.id = id;
        this.accountNum = accountNum;
        this.currencyAmount = currencyAmount;
    }

    public BigDecimal getAmountPerCurrency(Currency currency) {
        return currencyAmount.get(currency);
    }

    public void setAmountPerCurrency(Currency currency, BigDecimal amountPerCurrency) {
        currencyAmount.put(currency, amountPerCurrency);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNum='" + accountNum + '\'' +
                ", currencyAmount=" + currencyAmount +
                '}';
    }
}
