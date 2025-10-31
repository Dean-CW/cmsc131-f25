package projects.bank;

abstract class Transaction {
    private final String accountID;
    public String getAccountID() { return accountID; }
    
    private final double amount;
    public double getAmount() { return amount; }
    
    abstract void execute(Account account);

    abstract boolean validate(Account account);

    protected Transaction(String accountID, double amount) {
        if (accountID == null) {
            throw new IllegalArgumentException(
                "Parameter accountID cannot be null."
            );
        }
        else if (amount <= 0) {
            throw new IllegalArgumentException(
                "Parameter amount must be positive."
            );
        } else {
            this.accountID = accountID;
            this.amount = amount;
        } 
    }

     protected static Transaction make(String inputLine) {
        if (inputLine == null) {
            throw new IllegalArgumentException(
                "Parameter inputLine cannot be null."
            ); // tested by testMakeThrowsOnNullInput
        }
        String[] tokens = inputLine.split(",");
        TransactionType type = TransactionType.valueOf(
            tokens[0].toUpperCase()
        );
        String id = tokens[1];
        double amount = (double) Double.valueOf(tokens[2]);
        if (type == TransactionType.DEPOSIT) {
            return new Deposit(id, amount);
            // tested by testMakePreservesData
        } else {
            return new Withdrawal(id, amount);
            // tested by testMakePreservesData
        }
    }
}
