package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bank {
    private Account[] accounts;
    private final int newAccountsIncrement = 100;

    public Bank() {
        accounts = new Account[100];
    }

    // methods
    // add an account to the accounts array
    public boolean add(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("account must not be null.");
        }
        // don't add duplicates
        if (findAccount(account.getID()) != -1) {
            return false;
        }

        // find first null slot
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                return true;
            }
        }

        // no space -> expand array
        Account[] larger = new Account[accounts.length + newAccountsIncrement];
        System.arraycopy(accounts, 0, larger, 0, accounts.length);
        larger[accounts.length] = account;
        accounts = larger;
        return true;
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

    // load accounts from a CSV file, return true on success
    public boolean loadAccounts(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("filename must not be null.");
        }
        File inputFile = new File(filename);
        try (Scanner scan = new Scanner(inputFile)) {
            while (scan.hasNextLine()) {
                String csvString = scan.nextLine();
                if (csvString == null || csvString.trim().isEmpty()) {
                    continue;
                }
                Account account = Account.make(csvString);
                add(account);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean writeAccounts(String filename) {
        File file = new File(filename);
        try (FileWriter writer = new FileWriter(file)) {
            for (int idx = 0; idx < accounts.length; idx++) {
                Account account = accounts[idx];
                if (account == null) continue;
                String accountCsv = account.toCSV();
                writer.write(accountCsv + System.lineSeparator());
            }
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int processTransactions(String filename) {
        File inputFile = new File(filename);
        List<Transaction> list = new ArrayList<>();
        try (Scanner scan = new Scanner(inputFile)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line == null || line.trim().isEmpty()) continue;
                list.add(Transaction.make(line));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
        Transaction[] transactions = list.toArray(new Transaction[0]);
        return transactions.length;
    }
}
