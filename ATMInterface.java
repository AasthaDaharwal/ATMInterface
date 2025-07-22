package ATMInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMInterface {
    private static double balance = 1000.0;
    private static List<String> transactionHistory = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static final String USER_PIN = "1234";
    public static void main(String[] args) {
         System.out.println("Welcome to CodSoft ATM Interface");

        if (!authenticateUser()) {
            System.out.println("Too many failed attempts. Exiting...");
            return;
        }

        int choice;
        do {
            showMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> checkBalance();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> showTransactionHistory();
                case 5 -> System.out.println("Thank you for using the ATM. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    public static boolean authenticateUser() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter your 4-digit PIN: ");
            String inputPin = sc.next();
            if (inputPin.equals(USER_PIN)) {
                System.out.println("Login successful!\n");
                return true;
            } else {
                System.out.println("Incorrect PIN.");
                attempts++;
            }
        }
        return false;
    }

    public static void showMenu() {
        System.out.println("\n===== ATM MENU =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transaction History");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    public static void checkBalance() {
        System.out.printf("Your current balance is ₹%.2f%n", balance);
    }

    public static void deposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = sc.nextDouble();
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public static void withdraw() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = sc.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public static void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : transactionHistory) {
                System.out.println(t);
            }
        }
    }
    
}
