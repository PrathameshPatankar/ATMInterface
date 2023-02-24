import java.util.ArrayList;
import java.util.Scanner;

public class ATMInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Transaction> transactionHistory = new ArrayList<>();
    private static double balance = 100000.0;

    public static void main(String[] args) {
        System.out.println("******************WELCOME TO THE ATM*****************");
        boolean quit = false;
        while (!quit) {
          //  System.out.println("\nPlease select an option:");
            System.out.println("1. View transaction history");
            System.out.println("2. Withdraw money");
            System.out.println("3. Deposit money");
            System.out.println("4. Transfer money");
            System.out.println("5. Quit");
            System.out.println("\nPlease select an ATM operation:");

            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    viewTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    quit = true;
                    System.out.println(".......Thank you for using the ATM.......");
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }

    private static void viewTransactionHistory() {
        System.out.println("\n-------Transaction history:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    private static void withdraw() {
        System.out.print("\n---------Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount <= balance) {
            balance -= amount;
            Transaction transaction = new Transaction("Withdrawal", -amount);
            transactionHistory.add(transaction);
            System.out.printf("$%.2f has been withdrawn from your account\n", amount);
            System.out.printf("Your new balance is: $%.2f\n", balance);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    private static void deposit() {
        System.out.print("\n-------Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        balance += amount;
        Transaction transaction = new Transaction("Deposit", amount);
        transactionHistory.add(transaction);
        System.out.printf("$%.2f has been deposited into your account\n", amount);
        System.out.printf("Your new balance is: $%.2f\n", balance);
    }

    private static void transfer() {
        System.out.print("\n--------Enter the amount to transfer: $");
        double amount = scanner.nextDouble();
        if (amount <= balance) {
            System.out.print("Enter the recipient's account number: ");
            int accountNumber = scanner.nextInt();
            Transaction transaction = new Transaction("Transfer to account " + accountNumber, -amount);
            transactionHistory.add(transaction);
            balance -= amount;
            System.out.printf("$%.2f has been transferred to account %d\n", amount, accountNumber);
            System.out.printf("Your new balance is: $%.2f\n", balance);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    private static class Transaction {
        private String str;
        private double amount;

        public Transaction(String type, double amount) {
            this.str = type;
            this.amount = amount;
        }

        public String toString() {
            return String.format("%s: $%.2f", str, amount);
        }
    }
}