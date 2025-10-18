package projects.bank;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;

public class Bank {
    private Account[] accounts;

    public Bank() {
        accounts = new Account[100];
    }

    // methods
    // add an account to the accounts array
    public boolean add(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("account must not be null.");
        }
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                return true;
            }
        }
        return false;
    }

    // find an account in the accounts array by its ID, return its index or -1 if not found
    public int findAccount (String findId) {
         if (findId == null) {
            throw new IllegalArgumentException("accountID must not be null.");
        }
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getID().equals(findId)) {
                return i;
            }
        }
        return -1;
    }

    public int getNumberOfAccounts() {
        int count = 0;
        for (Account account : accounts) {
            if (account != null) {
                count++;
            }
        }
        return count;
    }

    public void loadAccounts(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("filename must not be null.");
        }

        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8)
            String line;
        }        
    }

    public void saveAccounts(String filename) {
        // not implemented
    }

}
