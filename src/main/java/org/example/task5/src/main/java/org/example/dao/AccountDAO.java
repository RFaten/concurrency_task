package org.example.dao;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.example.exception.AccountNotFoundException;
import org.example.model.Account;

import java.io.File;
import java.io.IOException;

public class AccountDAO {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String directory = "src/main/java/org/example/task5/src/main/resources";

    public static void saveAccount(Account account) {
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

        try {
            mapper.writeValue(
                    new File(directory, "Account" + account.getId() + ".json"),
                    account
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Account getAccount(int accountId) throws AccountNotFoundException {
        try {
            return mapper.readValue(new File(directory, "Account" + accountId + ".json"), Account.class);
        } catch (IOException e) {
            throw new AccountNotFoundException("No Account Found with id of " + accountId);
        }
    }
}
