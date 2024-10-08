import java.util.HashMap;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal.");
        }
    }
}

class Bank {
    private HashMap<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        if (!accounts.containsKey(accountNumber)) {
            Account newAccount = new Account(accountNumber, accountHolderName, initialBalance);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account with this number already exists.");
        }
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void displayAccountDetails(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String accountHolderName = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double initialBalance = scanner.nextDouble();
                    bank.createAccount(accountNumber, accountHolderName, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    Account accountDeposit = bank.getAccount(accountNumber);
                    if (accountDeposit != null) {
                        System.out.print("Enter Deposit Amount: ");
                        double depositAmount = scanner.nextDouble();
                        accountDeposit.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    Account accountWithdraw = bank.getAccount(accountNumber);
                    if (accountWithdraw != null) {
                        System.out.print("Enter Withdrawal Amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        accountWithdraw.withdraw(withdrawalAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    bank.displayAccountDetails(accountNumber);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting Banking System...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
