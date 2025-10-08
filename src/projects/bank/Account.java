package projects.bank;

public class Account {
    private String id;
    private String ownerName;
    private double balance;
    private String accountName;

    public Account(String tempId, double tempBalance, String tempAccountName) {
        id = tempId;
        balance = tempBalance;
        accountName = tempAccountName;
    }

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }
}
