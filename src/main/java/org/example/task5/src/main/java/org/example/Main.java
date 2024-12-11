package org.example;

import org.example.dao.AccountDAO;
import org.example.model.Account;
import org.example.model.Currency;
import org.example.service.ExchangeService;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(ExchangeService.class.getName());
    private static final int NUM_OF_ACCOUNTS = 3;

    public static void main(String[] args) {
        List<Account> accounts = createTestAccounts();

        try (ExecutorService service = Executors.newFixedThreadPool(NUM_OF_ACCOUNTS)) {
            List<Callable<Void>> callables = new ArrayList<>();
            for (Account account : accounts) {
                callables.add(new ExchangeService(account, Currency.PLN, Currency.EURO, new BigDecimal(300)));
                callables.add(new ExchangeService(account, Currency.USD, Currency.PLN, new BigDecimal(129)));
                callables.add(new ExchangeService(account, Currency.EGP, Currency.USD, new BigDecimal(2500)));
                callables.add(new ExchangeService(account, Currency.EURO, Currency.EGP, new BigDecimal(450)));
                callables.add(new ExchangeService(account, Currency.PLN, Currency.PLN, new BigDecimal(321)));
            }
            // Shuffle the list to ensures a better distribution of tasks from different accounts across the available threads.
            Collections.shuffle(callables, new Random());

            // Test exceptions throwing
            List<Future<Void>> futures = service.invokeAll(callables);
            logger.info("Total tasks executed: " + futures.size());
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<Account> createTestAccounts() {
        List<Account> accounts = new ArrayList<>();
        Map<Currency, BigDecimal> currencyAmount = new HashMap<>() {
            {
                put(Currency.USD, new BigDecimal(5000));
                put(Currency.EURO, new BigDecimal(5000));
                put(Currency.PLN, new BigDecimal(5000));
                put(Currency.EGP, new BigDecimal(5000));
            }
        };
        for (int i = 1; i <= NUM_OF_ACCOUNTS; i++) {
            Account account = new Account(i, "acc" + i, currencyAmount);
            accounts.add(account);
            AccountDAO.saveAccount(account);
        }
        return accounts;
    }
}