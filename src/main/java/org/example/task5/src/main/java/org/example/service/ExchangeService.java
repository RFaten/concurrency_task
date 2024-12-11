package org.example.service;

import org.apache.commons.lang3.tuple.Pair;
import org.example.dao.AccountDAO;
import org.example.dao.ExchangeRateDAO;
import org.example.exception.AccountNotFoundException;
import org.example.exception.CustomerAccountCurrencyException;
import org.example.exception.ExchangeRateNotFoundException;
import org.example.exception.InsufficientBalanceException;
import org.example.model.Account;
import org.example.model.Currency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ExchangeService implements Callable<Void> {
    private static final Logger logger = Logger.getLogger(ExchangeService.class.getName());
    private final Account account;
    private final Currency fromCurrency;
    private final Currency toCurrency;

    private final BigDecimal amount;
    private final Map<Pair<Currency, Currency>, Double> exchangeRates = ExchangeRateDAO.getExchangeRates();

    public ExchangeService(Account account, Currency fromCurrency, Currency toCurrency, BigDecimal amount) {
        this.account = account;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
    }

    @Override
    public Void call()
            throws
            ExchangeRateNotFoundException,
            AccountNotFoundException,
            CustomerAccountCurrencyException,
            InsufficientBalanceException,
            InterruptedException {
        double rate = getExchangeRate();
        synchronized (account) {
            Account fetchedAccount = AccountDAO.getAccount(account.getId());
            BigDecimal fromCurrentAmount = fetchedAccount.getAmountPerCurrency(fromCurrency);
            BigDecimal toCurrentAmount = fetchedAccount.getAmountPerCurrency(toCurrency);
            if (fromCurrentAmount == null || toCurrentAmount == null) {
                throw new CustomerAccountCurrencyException(
                        "Customer for account with id " + fetchedAccount.getId() + " doesn't have accounts for either or both of these currencies: "
                                + fromCurrentAmount + ", " + toCurrentAmount);
            }
            if (fromCurrentAmount.compareTo(amount) < 0) {
                throw new InsufficientBalanceException("Account with id " + fetchedAccount.getId() + " " +
                        "doesn't have sufficient balance to complete the transaction of " + amount + " " + fromCurrency);
            }

            BigDecimal toNewAmount = amount.multiply(BigDecimal.valueOf(rate)).add(toCurrentAmount);
            BigDecimal fromNewAmount = fromCurrentAmount.subtract(amount);
            fetchedAccount.setAmountPerCurrency(fromCurrency, fromNewAmount);
            fetchedAccount.setAmountPerCurrency(toCurrency, toNewAmount);
            logger.info(Thread.currentThread().getName() +
                    " exchanged an amount of " + amount + " from " + fromCurrency + " to " + toCurrency +
                    " for account " + account.getId());
            Thread.sleep(2000); // Simulate transaction time
            AccountDAO.saveAccount(fetchedAccount);
        }
        return null;
    }

    private double getExchangeRate() throws ExchangeRateNotFoundException {
        Double rate = exchangeRates.get(Pair.of(fromCurrency, toCurrency));
        if (rate == null) {
            throw new ExchangeRateNotFoundException("The exchange rate from " + fromCurrency + " to " + toCurrency + " is not available");
        }
        return rate;
    }
}
