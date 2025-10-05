package BankProject;

public class Bank {
    private Account[] accounts;

    public Bank() {
        accounts = new Account[10];
    }
    
    public void add(Account account) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                break;
            }
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        Account account1 = new Account("001", 1000.0, "Savings");
        Account account2 = new Account("002", 2000.0, "Checking");
        
        bank.add(account1);
        if(!bank.accounts[0].equals(account1)) {
            System.out.println("Test failed: account1 was not added correctly.");
        }
        bank.add(account2);
        if(!bank.accounts[1].equals(account2)) {
            System.out.println("Test failed: account2 was not added correctly.");
        }
        System.out.println("Accounts added to the bank.");
    }
}
