package projects.bank;

abstract class Account {
    private String id;
    private String ownerName;
    private double balance;
    private AccountType type;
    
    // constructor
    protected Account(String id, String ownerName, double balance) {
        if (id != null) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("id cannot be null");
        }

        if (ownerName != null) {
            this.ownerName = ownerName;
        } else {
            throw new IllegalArgumentException("ownerName cannot be null");
        }
        
        this.balance = balance;
    }
    
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

    abstract AccountType getType();

    public String toCSV() { 
        return toString(); 
    }

    public static Account make(String inputLine) {
        if (inputLine == null) {
            throw new IllegalArgumentException("inputLine cannot be null");
        }
        String[] tokens = inputLine.split(",");
        if (tokens.length < 4) {
            throw new IllegalArgumentException("Malformed account line: " + inputLine);
        }
        AccountType type = AccountType.valueOf(tokens[0].trim().toUpperCase());
        String id = tokens[1].trim();
        String ownerName = tokens[2].trim();
        double balance = Double.parseDouble(tokens[3].trim());
        if (type == AccountType.CHECKING) {
            return new CheckingAccount(id, ownerName, balance);
        } else {
            return new SavingsAccount(id, ownerName, balance);
        }
    }

    public void credit(double amount) {
        balance = balance + amount;
    }

    public void debit(double amount) {
        balance = balance - amount;
    }
}
