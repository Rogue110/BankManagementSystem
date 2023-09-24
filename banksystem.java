import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Bank {
    private Map<Integer, Account> accounts;
    private int accountIdCounter;

    public Bank() {
        this.accounts = new HashMap<>();
        this.accountIdCounter = 1000; // Starting account ID
    }

    public void createAccount(String name, double initialBalance) {
        int accountId = accountIdCounter++;
        Account account = new Account(accountId, name, initialBalance);
        accounts.put(accountId, account);
        System.out.println("Account created successfully. Account ID: " + accountId);
    }

    public void deposit(int accountId, double amount) {
        if (accounts.containsKey(accountId)) {
            Account account = accounts.get(accountId);
            account.deposit(amount);
            System.out.println("Deposited $" + amount + " into Account ID: " + accountId);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(int accountId, double amount) {
        if (accounts.containsKey(accountId)) {
            Account account = accounts.get(accountId);
            if (account.withdraw(amount)) {
                System.out.println("Withdrawn $" + amount + " from Account ID: " + accountId);
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void checkBalance(int accountId) {
        if (accounts.containsKey(accountId)) {
            Account account = accounts.get(accountId);
            System.out.println("Account ID: " + accountId + ", Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        if (accounts.containsKey(fromAccountId) && accounts.containsKey(toAccountId)) {
            Account fromAccount = accounts.get(fromAccountId);
            Account toAccount = accounts.get(toAccountId);
            
            if (fromAccount.withdraw(amount)) {
                toAccount.deposit(amount);
                System.out.println("Transferred $" + amount + " from Account ID: " + fromAccountId
                        + " to Account ID: " + toAccountId);
            } else {
                System.out.println("Insufficient balance in the source account.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }
}

class Account {
    private int accountId;
    private String accountHolderName;
    private double balance;

    public Account(int accountId, String accountHolderName, double initialBalance) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class BankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Money");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.next();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    bank.createAccount(name, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter account ID: ");
                    int accountIdDeposit = scanner.nextInt();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    bank.deposit(accountIdDeposit, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter account ID: ");
                    int accountIdWithdraw = scanner.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdraw(accountIdWithdraw, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter account ID: ");
                    int accountIdCheckBalance = scanner.nextInt();
                    bank.checkBalance(accountIdCheckBalance);
                    break;
                case 5:
                    System.out.print("Enter source account ID: ");
                    int fromAccountId = scanner.nextInt();
                    System.out.print("Enter destination account ID: ");
                    int toAccountId = scanner.nextInt();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    bank.transfer(fromAccountId, toAccountId, transferAmount);
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
