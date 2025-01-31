package org.practice;

import org.practice.datasource.CSVDataSource;
import org.practice.model.BankAccount;
import org.practice.model.User;
import org.practice.service.BankOperationService;
import org.practice.service.UserService;

import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CSVDataSource csvData = new CSVDataSource();
        UserService userService = new UserService(csvData);
        BankOperationService bankOperationService = new BankOperationService();

        System.out.println("Welcome to SBA, your simple banking solutions!");

        User user;
        BankAccount account;

        while (true) {
            //User login
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            System.out.println("Enter password: ");
            String password = sc.nextLine();

            user = new User(username, password);
            account = userService.login(user);

            if (account != null) {
                System.out.println("Login successful!");
                break;
            } else {
                System.out.println("Login failed\nWould you like to create an account? (yes/no)");
                String response = sc.nextLine();

                if (response.equalsIgnoreCase("yes")) {
                    userService.createAccount(user);
                    System.out.println("Account created successfully using the credentials entered earlier.\nPlease  " +
                            "try logging in again.");
                } else {
                    System.out.println("Exiting...");
                    return;
                }
            }
        }

        int option;
        do {
            System.out.println("\nOptions:");
            System.out.println("1. Get Balance");
            System.out.println("2. Make a deposit");
            System.out.println("3. Make a withdraw");
            System.out.println("4. Exit");
            System.out.println("Choose an option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    double balance = bankOperationService.getBalance(account);
                    if (balance == 0) {
                        System.out.println("Current balance: 0");
                    } else {
                        System.out.println("Current balance: $" + String.format("%.2f", balance));
                    }
                    break;
                case 2:
                    System.out.println("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    bankOperationService.deposit(account, depositAmount);
                    System.out.println("Deposited $" + String.format("%.2f", depositAmount));
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    bankOperationService.withdraw(account, withdrawAmount);
                    System.out.println("Withdrew $" + String.format("%.2f", withdrawAmount));
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);
    }
}