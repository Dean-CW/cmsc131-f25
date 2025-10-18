package projects.bank;

public class Account {
    private String id;
    private String ownerName;
    private double balance;
    private AccountType type;
    
    // constructor
    public Account(String tempId, String tempOwnerName, double tempBalance,  AccountType tempType) {
        if (tempId == null) {
            throw new IllegalArgumentException("id cannot be null");
        } else {
            id = tempId;
        }
        if (tempOwnerName == null) {
            throw new IllegalArgumentException("ownerName cannot be null");
        } else {
            ownerName = tempOwnerName;
        }
        if (tempType == null) {
            throw new IllegalArgumentException("type cannot be null");
        } else {
            type = tempType;
        }
        
        balance = tempBalance;
    }
    // methods
    public String getID() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    // TODO static factory method
    // TODO toCSV method
}
