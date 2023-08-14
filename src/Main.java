class ATMException extends Exception {
    public ATMException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends ATMException {
    public InsufficientBalanceException() {
        super("Insufficient balance for cash withdrawal.");
    }
}

class ATM {
    private double balance;

    public ATM(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        }
    }

    public void withdraw(double amount) throws ATMException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException();
        }

        balance -= amount;
        System.out.println("Withdrawal successful. New balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(1000.0); // Initial balance

        try {
            atm.deposit(500.0);
            atm.withdraw(300.0);
            atm.withdraw(800.0); // Will throw InsufficientBalanceException
            atm.deposit(-100.0); // Will be ignored

            System.out.println("Current balance: " + atm.getBalance());
        } catch (ATMException atmException) {
            System.out.println("Error: " + atmException.getMessage());
        }
    }
}
